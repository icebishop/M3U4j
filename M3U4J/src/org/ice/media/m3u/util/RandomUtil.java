package org.ice.media.m3u.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
	
	private static Random r = new Random();
	public static Set<Integer> generated = new HashSet<Integer>();
	
	
	public static int getNumber(int max, int min) {
		int number =0;
		do {
			number = r.nextInt( (max - min) + 1) + min;
		} while (generated.contains(number));
		
		generated.add(number);
		
		return number;
	}

}
