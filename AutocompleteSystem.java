package Design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 642. Design Search Autocomplete System
 * https://leetcode.com/problems/design-search-autocomplete-system/description/
 * >Medium
 * Facebook, Microsoft
 */

public class AutocompleteSystem {

	 class Node {
		 String sentence;
	     int times;
	     
	     Node(String st, int t) {
	            sentence = st;
	            times = t;
	     }
	 }
	    
	 class Trie {
	     int times;
	     Trie[] branches = new Trie[27];
	 }
	 
	 public int int_(char c) {
		 System.out.println("c: "+c+" c - 'a': "+(c - 'a'));
	     return c == ' ' ? 26 : c - 'a';
	 }
	    
	 public void insert(Trie t, String s, int times) {
	     System.out.println("t: "+t+" s: "+s+" times: "+times);
		 
		 for(int i=0; i<s.length(); i++) {
			 System.out.println("i: "+i+" s.charAt(i): "+s.charAt(i)+" int_(s.charAt(i)): "+int_(s.charAt(i)));
			 	
			 if(t.branches[int_(s.charAt(i))] == null) {
	            t.branches[int_(s.charAt(i))] = new Trie();
	    	 }
	         t = t.branches[int_(s.charAt(i))];
	     }
		 System.out.println("t.times: "+t.times+" times: "+times);
		 
	     t.times = t.times + times;
	     System.out.println("t.times: "+t.times);
	 }
	 
	 public List<Node> lookup(Trie t, String s) {
	    List<Node> list = new ArrayList<>();
	    System.out.println("t: "+t+" s: "+s);
	    
	    for(int i=0; i<s.length(); i++) {
	    	System.out.println("i: "+i+" s.charAt(i): "+s.charAt(i)+" int_(s.charAt(i)): "+int_(s.charAt(i)));
	    	
	        if(t.branches[int_(s.charAt(i))] == null) {
	            return new ArrayList<Node>();
	        }
	        
	        t = t.branches[int_(s.charAt(i))];
	    }
	    
	    System.out.println("s: "+s+" t: "+t+" list: "+list);
	    traverse(s, t, list);
	    
	    return list;
	 }
	 
	 public void traverse(String s, Trie t, List<Node> list) {
		 System.out.println("s: "+s+" list: "+list+" t.times: "+t.times+" t: "+t);
		 
	      if(t.times > 0) {
	            list.add(new Node(s, t.times));
	      }
	        
	      for(char i='a'; i<='z'; i++) {
	    	  	System.out.println("i: "+i+" (i-'a'): "+(i - 'a')+" t.branches[i - 'a']: "+t.branches[i - 'a']);
	            
	    	  	if(t.branches[i - 'a'] != null) {
	                traverse(s + i, t.branches[i - 'a'], list);
	            }
	      }
	      
	      System.out.println("t.branches[26]: "+t.branches[26]);
	        
	      if(t.branches[26] != null) {
	          traverse(s + ' ', t.branches[26], list);
	      }
	 }

	 Trie root;
	 public AutocompleteSystem(String[] sentences, int[] times) {
	        root = new Trie();
	        
	        System.out.println("sentences: "+Arrays.toString(sentences)+" times: "+Arrays.toString(times));
	        
	        for(int i=0; i<sentences.length; i++) {
	            insert(root, sentences[i], times[i]);
	        }
	 }
	 
	 String cur_sent = "";
	 public List<String> input(char c) {
		 List<String> res = new ArrayList<>();
		 System.out.println("c: "+c);
		 
		 if(c == '#') { 
	        insert(root, cur_sent, 1);
	        cur_sent = "";
	     } 
		 else {
	        
			 cur_sent = cur_sent + c;
			 System.out.println("cur_sent: "+cur_sent);
	         List<Node> list = lookup(root, cur_sent);
	         
	         System.out.println("list: "+list);
	         Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
	         
	         for(int i=0; i<Math.min(3, list.size()); i++) { 
	        	 System.out.println("i: "+i+" list.get(i): "+list.get(i)+" list.get(i).sentence: "+list.get(i).sentence);
	        	 res.add(list.get(i).sentence);
	         }
	         
	         System.out.println("res: "+res);
	     }
	    return res;
	 }
	
	public static void main(String[] args) {
		String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
		int[] times = {5,3,2,2};
		
		/*
		 	"i love you" : 5 times 
			"island" : 3 times 
			"ironman" : 2 times 
			"i love leetcode" : 2 times 
		 */
		
		AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
		char inputChar = 'i';
		List<String> result = obj.input(inputChar);
		
		System.out.println("result: "+result);
	}
}