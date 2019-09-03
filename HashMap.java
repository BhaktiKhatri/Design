package Design;

 class ListNode {
    int key;
	int val;
    ListNode next;

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

public class HashMap {
	
    static ListNode[] nodes = new ListNode[10000];

    public static void put(int key, int value) {
    	System.out.println("key: "+key+" value: "+value);
    	
        int i = getIndex(key);
        System.out.println("i: "+i+" nodes[i]: "+nodes[i]);
        
        if(nodes[i] == null) {
            nodes[i] = new ListNode(-1, -1);
        }
        
        ListNode prev = find(nodes[i], key);
        System.out.println("prev: "+prev);
        
        if(prev.next == null) {
            prev.next = new ListNode(key, value);
        }
        else { 
        	prev.next.val = value;
        }
    }

    public static int get(int key) {
    	System.out.println("key: "+key);
    	
        int i = getIndex(key);
        System.out.println("i: "+i+" nodes[i]: "+nodes[i]);
        
        if(nodes[i] == null) {
            return -1;
        }
        
        ListNode node = find(nodes[i], key);
        System.out.println("node: "+node);
        
        return node.next == null ? -1 : node.next.val;
    }

    public static void remove(int key) {
        int i = getIndex(key);
        System.out.println("i: "+i+" nodes[i]: "+nodes[i]+" key: "+key);
        
        if(nodes[i] == null) {
        	return;
        }
        
        ListNode prev = find(nodes[i], key);
        System.out.println("prev: "+prev);
        
        if(prev.next == null) { 
        	return;
        }
        
        prev.next = prev.next.next;
    }

    public static int getIndex(int key) { 
    	System.out.println("key: "+key+" Integer.hashCode(key): "+Integer.hashCode(key)+" nodes.length: "+nodes.length+" Integer.hashCode(key) % nodes.length: "+Integer.hashCode(key) % nodes.length);
    	
    	return Integer.hashCode(key) % nodes.length;
    }

    public static ListNode find(ListNode bucket, int key) {
        ListNode node = bucket;
        ListNode prev = null;
        
        System.out.println("bucket: "+bucket+" key: "+key);
        
        while(node != null && node.key != key) {
        	System.out.println("node.key: "+node.key);
        	
            prev = node;
            node = node.next;
        }
        System.out.println("prev: "+prev.key);
        
        return prev;
    }
    
	
	public static void main(String[] args) {
		HashMap hashMap = new HashMap();
		
		HashMap.put(1, 1);          
		HashMap.put(2, 2);         
		System.out.println(HashMap.get(1));             // returns 1
		System.out.println(HashMap.get(3));             // returns -1 (not found)
		HashMap.put(2, 1);          					// update the existing value
		System.out.println(HashMap.get(2));             // returns 1 
		HashMap.remove(2);          					// remove the mapping for 2
		HashMap.get(2);            						// returns -1 (not found) 

	}

}
