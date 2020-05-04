package org.ice.media.m3u.util;

public class MediaFileFilter2 {

	private String[] filters;

	public MediaFileFilter2(String[] filters) {
		this.filters = filters;
	}

	public boolean accept(String pathname) {

		String lower = pathname.toLowerCase();
		
		for (int i = 0; i < filters.length; i++) {
			if (lower.toLowerCase().endsWith(filters[i]))
				return true;
		}
		return false;
	}

}
