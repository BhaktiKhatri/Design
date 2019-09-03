package Design;

import java.util.Map;
import java.util.HashMap;

/*
 * 208. Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * Code from: @mokuteno https://leetcode.com/problems/implement-trie-prefix-tree/discuss/58832/AC-JAVA-solution-simple-using-single-array
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/suffixprefix/Trie.java
 * Explanation from: https://www.youtube.com/watch?v=AXjmTQ8LEoI
 * https://www.youtube.com/watch?v=-urNrIAQnNo
 * 
 * https://leetcode.com/articles/implement-trie-prefix-tree/
 * 
 * Insert: Time complexity : O(m), where m is the key length.
 * In each iteration of the algorithm, we either examine or create a node in the trie till we reach the end of the key. This takes only m operations.
 * Space complexity : O(m)
 * In the worst case newly inserted key doesn't share a prefix with the the keys already inserted in the trie. 
 * We have to add m new nodes, which takes us O(m) space.
 * 
 * Search and StartsWith: Time complexity : O(m) In each step of the algorithm we search for the next key character. 
 * In the worst case the algorithm performs m operations.
 * Space complexity : O(1)
 * 
 * 
 */

class Trie {

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        
        TrieNode() {
            children = new HashMap<>();
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
    	TrieNode current = root;
    	
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            
            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes endOfWord as true
        current.isWord = true;    
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = root;
        
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);

            //if node does not exist for given char then return false
            if(node == null) {
                return false;
            }
            current = node;
        }
        //return true of current's endOfWord is true else return false.
        return current.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        
        for(int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.children.get(ch);

            //if node does not exist for given char then return false
            if(node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	Trie trie = new Trie();
    	trie.insert("apple");
    	trie.search("apple");   // returns true
    	System.out.println(trie.search("app"));     // returns false
    	trie.startsWith("app"); // returns true
    	trie.insert("app");   
    	trie.search("app");     // returns true

    }
}