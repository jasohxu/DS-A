package hw9;

import java.io.*;
import java.util.*;

public class HashTable_Quadratic {
	private String[] hashArray;
	private int arraySize;

	public HashTable_Quadratic(int size)
	{
		arraySize = size;
		hashArray = new String[arraySize];
	}

	public void displayTable() {
		System.out.print("Table: ");
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				System.out.print(hashArray[j] + " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}

	@Override
	public String toString() {
		String result = "";
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				result+=hashArray[j] + " ";
			else
				result+="**";
		}
		return result;
	}

	public int hashFunc(String key) {
		int ki = 0;
		for (int c = 0; c < key.length(); c++) {
			ki += (int)key.charAt(c) * (int)(Math.pow(26, key.length()-c-1));
		}
		int pt = ki%arraySize;
		if (hashArray[pt] == null) {
			return pt;
		}
		else {
			int q = 0;
			while (hashArray[pt] != null) {
				q++;
				ki = (q*q);
				
				pt += ki;
				pt = ki%arraySize;
			}
			return pt;
		}
	}

	public void insert(String item)
	{
		int k = hashFunc(item);
		hashArray[k] = item;
	}
	
	public String delete(String key) 
	{
		for (int c = 0; c < hashArray.length; c++) {
			if (hashArray[c] == key) {
				hashArray[c] = null;
				return key;
			}
		}
		return null;
	}

	public String find(String key)
	{
		for (int c = 0; c < hashArray.length; c++) {
			if (hashArray[c] == key) {
				return key;
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		int size;

		Scanner console = new Scanner(System.in);
		System.out.print("Enter size of hash table: ");
		size = console.nextInt();
		console.close();

		HashTable_Quadratic table = new HashTable_Quadratic(size);
		table.insert("apple");
		table.insert("book");
		table.insert("car");
		table.insert("dog");
		table.insert("egg");
		table.insert("fish");
		table.insert("giraff");
		table.insert("horse");
		table.insert("ice");
		table.insert("jungle");

		table.displayTable();
	}
}