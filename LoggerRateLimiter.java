package Design;

import java.util.Map;
import java.util.HashMap;

/*
 * https://leetcode.com/problems/logger-rate-limiter/submissions/
 * 359. Logger Rate Limiter
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed
 * in the last 10 seconds.
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 * Explanation and Code from: @Stefan https://leetcode.com/problems/logger-rate-limiter/discuss/83273/Short-C%2B%2BJavaPython-bit-different
 */

public class LoggerRateLimiter {
	
    private static Map<String, Integer> ok;

	/** Initialize your data structure here. */
    public LoggerRateLimiter() {
        ok = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public static boolean shouldPrintMessage(int timestamp, String message) {
    	System.out.println("timestamp: "+timestamp+" message: "+message+" ok: "+ok);
    	
        if(timestamp < ok.getOrDefault(message, 0)) {
            return false;
        }
        
        ok.put(message, timestamp + 10);
        System.out.println("timestamp: "+timestamp+" message: "+message+" ok: "+ok);
        
        return true;
    }
	
	public static void main(String[] args) {
		LoggerRateLimiter logg = new LoggerRateLimiter();
		
		// logging string "foo" at timestamp 1
		System.out.println(shouldPrintMessage(1, "foo")); //returns true; 

		// logging string "bar" at timestamp 2
		System.out.println(shouldPrintMessage(2,"bar")); //returns true;

		// logging string "foo" at timestamp 3
		System.out.println(shouldPrintMessage(3,"foo")); //returns false;

		// logging string "bar" at timestamp 8
		System.out.println(shouldPrintMessage(8,"bar")); //returns false;

		// logging string "foo" at timestamp 10
		System.out.println(shouldPrintMessage(10,"foo")); //returns false;

		// logging string "foo" at timestamp 11
		System.out.println(shouldPrintMessage(11,"foo")); //returns true;
	}
}