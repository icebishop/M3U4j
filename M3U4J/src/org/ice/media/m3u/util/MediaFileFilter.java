package org.ice.media.m3u.util;

import java.io.File;
import java.io.FileFilter;

public class MediaFileFilter implements FileFilter {
	
	private String[] filters;
	
	public MediaFileFilter(String[] filters) {
		this.filters = filters;
	}

	@Override
	public boolean accept(File pathname) {
		
		String lower = pathname.getAbsolutePath().toLowerCase();
		
		for (int i = 0; i < filters.length; i++) {
			if( lower.endsWith(filters[i]))
				return true;
		}
		return false;
	}
	
}
