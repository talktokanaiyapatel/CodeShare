package org.practice.stream;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Trie {

	static class TrieNode {
		TrieNode[] arr;
		boolean isEnd;

		// Initialize your data structure here.
		public TrieNode() {
			this.arr = new TrieNode[26];
		}

	}

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode p = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				TrieNode temp = new TrieNode();
				p.arr[index] = temp;
				p = temp;
			} else {
				p = p.arr[index];
			}
		}
		p.isEnd = true;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode p = searchNode(word);
		if (p == null) {
			return false;
		} else {
			if (p.isEnd)			*******
				return true;
		}

		return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode p = searchNode(prefix);
		if (p == null) {
			return false;
		} else {
			return true;
		}
	}
	// returns last node in prefix.
	public TrieNode searchNode(String s) {
		TrieNode p = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (p.arr[index] != null) {
				p = p.arr[index];
			} else {
				return null;
			}
		}
		// can be avoided if we make empty check.
		if (p == root)
			return null;

		return p;
	}

	// Find all words with prefix..
	public List<String> findAllWords(String s) {
		TrieNode node = searchNode(s);
		if (node == null) {
			return null;
		}
		// result
		List<String> result = new ArrayList<String>();
		Queue<String> queueResult = new ArrayDeque<>();
		queueResult.add("");
		Queue<TrieNode> pathNodes = new ArrayDeque<>();
		pathNodes.add(node);
		while (!pathNodes.isEmpty()) {
			TrieNode interNode = pathNodes.poll();
			String builder = queueResult.poll();
			if (interNode.isEnd) {
				//add prefix...
				result.add(s + builder.toString());
			}
			if (interNode.arr != null) {
				for (int i = 0; i < interNode.arr.length; i++) {
					TrieNode item = interNode.arr[i];
					if (item != null) {
						char ch = (char) ((char) i + 'a');
						queueResult.add(builder + ch);
						pathNodes.add(item);
					}
				}

			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie = new Trie();
		trie.insert("abc");
		trie.insert("abcde");
		trie.insert("abctyyy");
		trie.insert("abcduiuyu");
		trie.insert("abctyyyuiuiui");
		trie.insert("abctopopo");
		System.out.println(trie.findAllWords("abc"));
	}

}
