package hw9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Anagrams {
	public static ArrayList<ArrayList<String>> groupAnagrams(String[] input) {
		Hashtable <String, ArrayList<String>> h = new Hashtable <String, ArrayList<String>>();
		ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
		for (int c = 0; c < input.length; c++) {
			char[] temp = input[c].toCharArray();
			Arrays.sort(temp);
			String s = String.valueOf(temp);
			if (h.containsKey(s)) {
				h.get(s).add(input[c]);
			}
			else {
				ArrayList<String> t2 = new ArrayList<String>();
				t2.add(input[c]);
				h.put(s, t2);
			}
 		}
		Object[] kl = h.keySet().toArray();
		for (int c0 = 0; c0 < h.keySet().size(); c0++) {
			a.add(h.get(kl[c0]));
		}
		return a;
	}

	public static void main(String[] args) {
		String[] input = {"eat", "tea", "tan", "ata", "nat", "bat"};
		ArrayList<ArrayList<String>> results = groupAnagrams(input);
		System.out.println(results);
	}
}