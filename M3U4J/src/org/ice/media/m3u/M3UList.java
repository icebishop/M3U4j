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
		mediaFiles = new TreeMap<>();
		type = "M3U";
	}

	private M3UList(SortedMap<Integer, MediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
		type = "M3U";
	}

	@Override
	public List<MediaFile> getMediaFiles() {

		return new ArrayList<>(mediaFiles.values());
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

		RandomUtil.clear();

		int min = mediaFiles.firstKey();
		int max = mediaFiles.lastKey();
		List<MediaFile> randomFiles = new ArrayList<>();
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
		List<M3UList> m3uLists = new ArrayList<>();
		SortedMap<Integer, MediaFile> sorted = this.mediaFiles;
		int min = 0;
		int max = 0;

		if (sorted.size() > split)
			max = split;
		else
			max = sorted.size();

		String padFormat = getPadFormat(String.valueOf(this.getMediaFiles().size()).length());

		boolean done = true;
		do {
			if (max >= sorted.size() )
				done = false;
			M3UList list = new M3UList(sorted.subMap(min, max));
			list.setName(String.format(padFormat, this.name, min+1, max ));
			m3uLists.add(list);
			min = max ;
			max = max + split;
			if (max > sorted.size())
				max = sorted.size();
		} while (done);

		return m3uLists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String getPadFormat(int numberPad) {
		return "%s(%0".concat(String.valueOf(numberPad).concat("d-%0".concat(String.valueOf(numberPad).concat("d)"))));
	}

}
