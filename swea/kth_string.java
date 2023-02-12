import java.io.*;
import java.util.*;


class Solution {

	static	List<Character> list;
	static	int depth, K;

	static class Trie
	{
		static int ALPHABET_LENGTH = 26;
		Node root;

		Trie() {
			this.root = new Node();
		}

		public void find(Node index) {
			Node node = index;
			if (depth == K) return;
			for (int i = 0; i < ALPHABET_LENGTH; i++) {
				if (node.children[i] != null) {
					depth++;
					list.add((char) (i + 'a'));
					find(node.children[i]);
					if (depth == K) return;
					list.remove(list.size() - 1);
				}
			}
		}

		static class Node {
			Node[] children = new Node[ALPHABET_LENGTH];
			boolean isEnd = false;
			int children_num = 0;
		}

		public void insert(Node root, String string, int i, int end) {
			Node temp = root;
			if (i == end) {
				temp.isEnd = true;
				return;
			}
			if (temp.children[string.charAt(i) - 'a'] == null)
				temp.children[string.charAt(i) - 'a'] = new Node();
			temp.children_num++;
			insert(temp.children[string.charAt(i) - 'a'], string, i + 1, end);
		}


	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test, T;
		String string;
		Trie trie;

		T = Integer.parseInt(br.readLine());
		for (test = 1; test <= T; test++) {
			K = Integer.parseInt(br.readLine());
			depth = 0;
			string = br.readLine();
			trie = new Trie();

			int length = string.length();
			for (int i = 0; i < length; i++) {
				if (trie.root.children[string.charAt(i) - 'a'] == null)
					trie.root.children[string.charAt(i) - 'a'] = new Trie.Node();
				trie.root.children_num++;
				trie.insert(trie.root.children[string.charAt(i) - 'a'], string, i + 1, length);
			}
			list = new LinkedList<>();
			trie.find(trie.root);

			String answer = "";
			for (Character c : list)
				answer += c;
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
}

