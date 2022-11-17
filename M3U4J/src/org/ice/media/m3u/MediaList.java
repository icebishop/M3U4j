package org.ice.media.m3u;

import java.util.List;

public interface MediaList {
	
	public List<MediaFile> getMediaFiles();
	public void setMediaFiles(List<MediaFile> mediaFiles );
	public String getType();
	public List<MediaFile> randomize();

}
