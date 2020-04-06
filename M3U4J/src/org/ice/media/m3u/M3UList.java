package org.ice.media.m3u;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ice.media.m3u.util.RandomUtil;

public class M3UList implements MediaList {
	
	private Map<Integer, MediaFile> mediaFiles;
	private String type;
	
	public M3UList() {
		mediaFiles = new HashMap<Integer, MediaFile>();
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
		
		int max = mediaFiles.size();
		List<MediaFile> randomFiles =  new ArrayList<MediaFile>();
		int number = 0;
		for (int i = 1; i <= max; i++) {
			number = RandomUtil.getNumber(max, 1);
			randomFiles.add(mediaFiles.get(number));
		}
		
		return randomFiles;
		
	}

	public Map<Integer, MediaFile> getMediaFilesMap() {
		
		return mediaFiles;
		
	}

}
