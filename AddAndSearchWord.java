package Design;

import java.util.Arrays;

/*
 * 211. Add and Search Word - Data structure design
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * Note: You may assume that all words are consist of lowercase letters a-z.
 * Code from: @Lnic https://leetcode.com/problems/add-and-search-word-data-structure-design/discuss/59554/My-simple-and-clean-Java-code
 * Time Complexity: addWord() - O(n), n = length of the new word; search() - Worst case: O(m), m = the total number of characters in the Trie
 */

public class AddAndSearchWord {
	
    public static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String item = "";
    }
    
    private static TrieNode root = new TrieNode();

    public static void addWord(String word) {
    	System.out.println("word: "+word);
        TrieNode node = root;
        
        for(char c: word.toCharArray()) {
            System.out.println("c: "+c+" c - 'a': "+(c - 'a')+" node.children[c - 'a']: "+node.children[c - 'a']);
        	
            if(node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.item = word;
    }

    public static boolean search(String word) {
    	System.out.println("word: "+word);
        return match(word.toCharArray(), 0, root);
    }
    
    private static boolean match(char[] chs, int k, TrieNode node) {
        System.out.println("chs: "+Arrays.toString(chs)+" k: "+k+" node: "+node);
    	
        if(k == chs.length) 
        	return !node.item.equals("");   
        
        System.out.println("chs[k]: "+chs[k]);
        
        if(chs[k] != '.') {
        	System.out.println("chs[k]: "+chs[k]+" chs[k] - 'a': "+(chs[k] - 'a')+" node.children[chs[k] - 'a']: "+node.children[chs[k] - 'a']);
            return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
        } 
        else {
            for(int i=0; i<node.children.length; i++) {
            	System.out.println("i: "+i+" node.children[i]: "+node.children[i]);
                if (node.children[i] != null) {
                    if (match(chs, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		addWord("bad");
		addWord("dad");
		addWord("mad");
		System.out.println(search("pad")); //-> false
		System.out.println(search("bad"));  //-> true
		System.out.println(search(".ad"));	//-> true
		System.out.println(search("b.."));  //-> true
	}
}