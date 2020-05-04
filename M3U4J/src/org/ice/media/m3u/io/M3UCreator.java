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

	public void create(String directory, String playListName, boolean isUnicode, boolean isExtended, boolean isAbsolute,
			String relativePath, String[] filters, boolean isRandom, int split) throws Exception {

		List<MediaFile> mediaFiles = null;

		List<M3UList> m3uLists = null;

		if (isAbsolute)
			relativePath = null;

		List<String> listStrings = DirectoryMediaReader.readDirectory(directory, filters);

		Collections.sort(listStrings);

		M3UList m3uList = generate(relativePath, listStrings);
		m3uList.setName(playListName);

		if (isRandom)
			m3uList.setMediaFiles(m3uList.randomize());

		if (split > 0) {
			m3uLists = m3uList.split(split);
		} else {
			m3uLists = new ArrayList<M3UList>();
			m3uLists.add(m3uList);
		}

		for (Iterator<M3UList> iterator = m3uLists.iterator(); iterator.hasNext();) {
			M3UList list = iterator.next();

			mediaFiles = list.getMediaFiles();

			M3UWriter.writeList(isUnicode, isExtended, relativePath, list.getName(), mediaFiles);
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

	public M3UList generate(String relativePath, List<String> filesList) throws IOException {

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
