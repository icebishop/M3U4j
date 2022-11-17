package org.ice.media.m3u.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.util.DirectoryMediaReader;

public class M3UCreator {

	public void create(M3UCreatorData data) throws IOException {

		List<MediaFile> mediaFiles = null;

		List<M3UList> m3uLists = null;

		if (data.isAbsolute())
			data.setRelativePath(null);

		List<String> listStrings = DirectoryMediaReader.readDirectory(data.getDirectory(), data.getFilters());

		Collections.sort(listStrings);

		M3UList m3uList = generate(data.getRelativePath(), listStrings);
		m3uList.setName(data.getPlayListName());

		if (data.isRandom())
			m3uList.setMediaFiles(m3uList.randomize());

		if (data.getSplit() > 0) {
			m3uLists = m3uList.split(data.getSplit());
		} else {
			m3uLists = new ArrayList<>();
			m3uLists.add(m3uList);
		}

		for (Iterator<M3UList> iterator = m3uLists.iterator(); iterator.hasNext();) {
			M3UList list = iterator.next();

			mediaFiles = list.getMediaFiles();

			M3UWriter.writeList(data.isUnicode(), data.isExtended(), data.getRelativePath(), list.getName(), mediaFiles);
		}

	}


	private String getFileName(String line) {
		String ext = line.substring(line.lastIndexOf("."));
		return line.substring(line.lastIndexOf(File.separator) + 1, line.lastIndexOf(ext));
	}

	private String getPath(String line, String relativePath) {

		if (relativePath != null)
			return line.substring(line.lastIndexOf(relativePath) + relativePath.length() + 1);
		else
			return line;
	}

	public M3UList generate(String relativePath, List<String> filesList) {

		M3UList m3uList = new M3UList();
		MediaFile mediaFile = null;

		int position = 1;
		for (Iterator<String> iterator = filesList.iterator(); iterator.hasNext();) {
			String string = iterator.next();

			mediaFile = new MediaFile();
			mediaFile.setName(getFileName(string));
			mediaFile.setPosition(position);
			mediaFile.setUrl(getPath(string, relativePath));

			m3uList.getMediaFilesMap().put(position, mediaFile);

			position++;
		}

		return m3uList;
	}

}
