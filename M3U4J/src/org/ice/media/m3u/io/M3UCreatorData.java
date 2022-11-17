package org.ice.media.m3u.io;

public class M3UCreatorData {

	private String directory;
	private String playListName;
	private boolean isUnicode;
	private boolean isExtended;
	private boolean isAbsolute;
	private String relativePath;
	private String[] filters;
	private boolean isRandom;
	private int split;
	
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getPlayListName() {
		return playListName;
	}
	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}
	public boolean isUnicode() {
		return isUnicode;
	}
	public void setUnicode(boolean isUnicode) {
		this.isUnicode = isUnicode;
	}
	public boolean isExtended() {
		return isExtended;
	}
	public void setExtended(boolean isExtended) {
		this.isExtended = isExtended;
	}
	public boolean isAbsolute() {
		return isAbsolute;
	}
	public void setAbsolute(boolean isAbsolute) {
		this.isAbsolute = isAbsolute;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String[] getFilters() {
		return filters;
	}
	public void setFilters(String[] filters) {
		this.filters = filters;
	}
	public boolean isRandom() {
		return isRandom;
	}
	public void setRandom(boolean isRandom) {
		this.isRandom = isRandom;
	}
	public int getSplit() {
		return split;
	}
	public void setSplit(int split) {
		this.split = split;
	}

}
