package Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * 355. Design Twitter
 * https://leetcode.com/problems/design-twitter/description/
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's
 * news feed. Your design should support the following methods:
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user
 * followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * Explanation and Code from: @UpTheHell https://leetcode.com/problems/design-twitter/discuss/82825/Java-OO-Design-with-most-efficient-function-getNewsFeed
 * Amazon, Twitter
 * Medium
 */

public class Twitter {
	    private static int timeStamp=0;
	    
	    // easy to find if user exist
	    private Map<Integer, User> userMap;
	    
	    // Tweet link to next Tweet so that we can save a lot of time
	    // when we execute getNewsFeed(userId)
	    private class Tweet {
	        public int id;
	        public int time;
	        public Tweet next;
	        
	        public Tweet(int id) {
	        	System.out.println("tweet id: "+id+" timeStamp: "+timeStamp);
	            this.id = id;
	            time = timeStamp++;
	            next = null;
	        }
	    }
	    
	    // OO design so User can follow, unfollow and post itself
	    public class User {

	    	public int id;
	        public Set<Integer> followed;
	        public Tweet tweet_head;
	        
	        public User(int id) {
	        	System.out.println("user id: "+id);
	            this.id = id;
	            followed = new HashSet<>();
	            follow(id); 					// first follow itself
	            tweet_head = null;
	        }
	        
	        public void follow(int id) {
	        	System.out.println("follow id: "+id);
	            followed.add(id);
	        }
	        
	        public void unfollow(int id) {
	        	System.out.println("unfollow id: "+id);
	            followed.remove(id);
	        }
	        
	        // everytime user post a new tweet, add it to the head of tweet list.
	        public void post(int id) {
	        	System.out.println("post id: "+id+" tweet_head: "+tweet_head);
	            
	        	Tweet t = new Tweet(id);
	            t.next = tweet_head;
	            tweet_head = t;
	        }
	    }

	    /** Initialize your data structure here. */
	    public Twitter() {
	        userMap = new HashMap<Integer, User>();
	    }
	    
	    /** Compose a new tweet. */
	    public void postTweet(int userId, int tweetId) {
	    	System.out.println("userMap: "+userMap+" userId: "+userId+" tweetId: "+tweetId);
	        
	    	if(!userMap.containsKey(userId)) { 
	            User u = new User(userId);
	            userMap.put(userId, u);
	        }
	    	
	    	System.out.println("userMap: "+userMap+" userId: "+userId+" userMap.get(userId): "+userMap.get(userId));
	        userMap.get(userId).post(tweetId);
	    }
	    
	    // Best part of this.
	    // first get all tweets lists from one user including itself and all people it followed.
	    // Second add all heads into a max heap. Every time we poll a tweet with 
	    // largest time stamp from the heap, then we add its next tweet into the heap.
	    // So after adding all heads we only need to add 9 tweets at most into this 
	    // heap before we get the 10 most recent tweet.
	    public List<Integer> getNewsFeed(int userId) {
	        List<Integer> res = new LinkedList<>();

	        System.out.println("userMap: "+userMap+" userId: "+userId+" userMap.get(userId): "+userMap.get(userId)+" userMap.get(userId).followed: "+userMap.get(userId).followed);
	        
	        if(!userMap.containsKey(userId))   
	        	return res;
	        
	        Set<Integer> users = userMap.get(userId).followed;
	        System.out.println("users: "+users);
	        
	        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
	        for(int user: users) {
	        	System.out.println("user: "+user);
	            
	        	Tweet t = userMap.get(user).tweet_head;
	            
	        	// very imporant! If we add null to the head we are screwed.
	            if(t != null) {
	                q.add(t);
	            }
	        }
	        
	        System.out.println("q: "+q);
	        
	        int n = 0;
	        while(!q.isEmpty() && n<10) {
	          Tweet t = q.poll();
	          res.add(t.id);
	          n++;
	          
	          if(t.next != null)
	            q.add(t.next);
	        }
	        
	        System.out.println("res: "+res);
	        
	        return res;
	    }
	    
	    /** Follower follows a followee. If the operation is invalid, it should be a no-op */
	    public void follow(int followerId, int followeeId) {
	        System.out.println("followerId: "+followerId+" followeeId: "+followeeId+" userMap: "+userMap);
	    	
	    	if(!userMap.containsKey(followerId)) {
	            User u = new User(followerId);
	            userMap.put(followerId, u);
	        }
	    	
	        if(!userMap.containsKey(followeeId)) {
	            User u = new User(followeeId);
	            userMap.put(followeeId, u);
	        }
	        userMap.get(followerId).follow(followeeId);
	        
	        System.out.println("userMap: "+userMap);
	    }
	    
	    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	    public void unfollow(int followerId, int followeeId) {
	    	System.out.println("followerId: "+followerId+" followeeId: "+followeeId+" userMap: "+userMap);
	    	
	        if(!userMap.containsKey(followerId) || followerId == followeeId)
	            return;
	        userMap.get(followerId).unfollow(followeeId);
	    }

	/**
	 * Your Twitter object will be instantiated and called as such:
	 * Twitter obj = new Twitter();
	 * obj.postTweet(userId,tweetId);
	 * List<Integer> param_2 = obj.getNewsFeed(userId);
	 * obj.follow(followerId,followeeId);
	 * obj.unfollow(followerId,followeeId);
	 */
	
	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		
		// User 1 posts a new tweet (id = 5).
		twitter.postTweet(1, 5);

		// User 1's news feed should return a list with 1 tweet id -> [5].
		twitter.getNewsFeed(1);

		// User 1 follows user 2.
		twitter.follow(1, 2);

		// User 2 posts a new tweet (id = 6).
		twitter.postTweet(2, 6);

		// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
		// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
		twitter.getNewsFeed(1);

		// User 1 unfollows user 2.
		twitter.unfollow(1, 2);

		// User 1's news feed should return a list with 1 tweet id -> [5],
		// since user 1 is no longer following user 2.
		twitter.getNewsFeed(1);

		
	}

}
