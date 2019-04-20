package com.sarthak;
import java.util.*;
import java.io.*;

public class test {

	public static void main(String[] args) {
		
		HashSet<Integer> s = new HashSet<>(4);
		HashMap<Integer,Integer> m = new HashMap<>();
		
		m.put(7, 2);
		m.put(6, 3);
		m.put(7, 10);
		m.put(6, 5);
		
		System.out.println(m.get(6));
	}

}
