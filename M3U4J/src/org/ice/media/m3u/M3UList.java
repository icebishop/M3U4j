package org.ice.media.m3u;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.ice.media.m3u.util.RandomUtil;

public class M3UList implements MediaList {
	
	private SortedMap<Integer, MediaFile> mediaFiles;
	private String type;
	private String name;
	
	public M3UList() {
		mediaFiles = new TreeMap<Integer, MediaFile>();
		type = "M3U";
	}
	
	private M3UList(SortedMap<Integer, MediaFile> mediaFiles ) {
		this.mediaFiles = mediaFiles;
		type = "M3U";
	}

	@Override
	public List<MediaFile> getMediaFiles() {

		return new ArrayList<MediaFile>(mediaFiles.values());
	}

	@Override
	public void setMediaFiles(List<MediaFile> mediaFiles) {

		this.mediaFiles.clear();
		
		for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
			MediaFile mediaFile = iterator.next();
			this.mediaFiles.put(mediaFile.getPosition(), mediaFile);
		}
	}
	
	@Override
	public String getType() {
		
		return type;
	}

	@Override
	public List<MediaFile> randomize() {
		
		RandomUtil.generated.clear();
		
		int min = mediaFiles.firstKey();
		int max = mediaFiles.lastKey();
		List<MediaFile> randomFiles =  new ArrayList<MediaFile>();
		int number = 0;
		for (int i = 0; i < mediaFiles.size(); i++) {
			number = RandomUtil.getNumber(max, min);		
			randomFiles.add(mediaFiles.get(number));
		}
		
		for (int i = 0; i < mediaFiles.size(); i++) {
			randomFiles.get(i).setPosition(i);
		}
		
		return randomFiles;
		
	}

	public Map<Integer, MediaFile> getMediaFilesMap() {
		
		return mediaFiles;
		
	}

	public List<M3UList> split(int split) {
		List<M3UList> m3uLists = new ArrayList<M3UList>();
		//TreeMap<Integer, MediaFile> sorted = new TreeMap<Integer, MediaFile>(this.mediaFiles);
		SortedMap<Integer, MediaFile> sorted = this.mediaFiles;
		int min = 0;
		int max = 0;
		
		if(sorted.size()> split)
			max = split+1;
		else 
			max = sorted.size();
		
		boolean done = true; 
		do {
			if(max == sorted.size()+1)
				done = false;
			M3UList list = new M3UList(sorted.subMap(min, max));
			list.setName(String.format("%s(%s-%s)",this.name,min,max-1));
			m3uLists.add(list);
			min = max;
			max = max+split;
			if(max > sorted.size())
				max = sorted.size()+1;			
		} while (done);
		
		return m3uLists;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
