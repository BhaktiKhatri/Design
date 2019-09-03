package Design;

import java.util.HashMap;
import java.util.Map;

/*
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/description/
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Follow up: Could you do both operations in O(1) time complexity?
 * >Medium 
 * Explanation: https://www.youtube.com/watch?v=S6IfqDXWa10
 * 
 * Time complexity : O(1) both for put and get.
 * Space complexity : O(capacity) since the space is used only for a hashmap and double linked list with at most capacity + 1 elements.
 */

class Node{
    int key;
    int val;
    Node prev;
    Node next;
    
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
	
    public Node() {
		// TODO Auto-generated constructor stub
	}
}

class LRUCache {
 
	private int capacity = 0;
    Map<Integer, Node> map = null;
    Node head = null;
    Node tail = null;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    //this key is being recently used so place it just before tail by using moveToFront(); thus this is most recently used cache and it will be placed
    //on the right side and the least recenly used cache is placed on the left side just before the head 
    public int get(int key) {			
    	System.out.println("map: "+map+" key: "+key);
        if(!map.containsKey(key)) {
            return -1;
        }
        
        moveToFront(map.get(key));
        return map.get(key).val;
    }
    
    public void put(int key, int value) {
    	System.out.println("key: "+key+" value: "+value+" capacity: "+capacity);
        if(capacity == 0) {
            return;
        }
        
        System.out.println("map: "+map);
        
        if(map.containsKey(key)) {
            map.get(key).val = value;
            moveToFront(map.get(key));
        }
        else{
        	System.out.println("map: "+map);
            freeSpace();
            Node n = new Node(key,value);
            map.put(key,n);
            System.out.println("map: "+map);
            addToFront(n);
        }        
    }
    
    private void freeSpace() {
    	System.out.println("map: "+map+" capacity: "+capacity+" head: "+head.key);
        
    	if(map.size() == capacity) {
            Node toRemove = head.next;
            System.out.println("toRemove: "+toRemove.val);
            
            map.remove(toRemove.key);
            System.out.println("map: "+map);
            
            Node next = toRemove.next;
            System.out.println("next: "+next.val);
            
            head.next = next;
            next.prev = head;   
        }
    }
    
    private void moveToFront(Node newNode) {
    	System.out.println("newNode: "+newNode.val);
        
    	Node prev = newNode.prev;
        System.out.println("prev: "+prev.val);
    	
    	Node next = newNode.next;
    	System.out.println("next: "+next.val);
    	
        prev.next = next;
        next.prev = prev;
        addToFront(newNode);
    }
    
    private void addToFront(Node newNode) {
    	System.out.println("newNode: "+newNode.val);
    	
    	Node prev = tail.prev;
        System.out.println("prev: "+prev.val+" tail: "+tail.val);
    	
    	prev.next = newNode;
        newNode.prev = prev;
        tail.prev = newNode;
        newNode.next = tail;
    }
    
    public static void main(String[] args) {
    	LRUCache cache = new LRUCache(2/* capacity */);
    	
    	cache.put(1, 1);
    	cache.put(2, 2);
    	cache.get(1);       // returns 1
    	cache.put(3, 3);    // evicts key 2
    	cache.get(2);       // returns -1 (not found)
    	cache.put(1, 4);    // evicts key 1
    	cache.get(1);       // returns -1 (not found)
    	cache.get(3);       // returns 3
    	cache.get(4);       // returns 4
    }
    
}