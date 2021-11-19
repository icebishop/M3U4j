package org.ice.media.m3u.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.exception.M3UReaderException;

/**
 * @author ice Read a M3U playlist simple or extended
 */
public class M3UReader {

	private static final String EXT_PATTERN = "(#EXTINF:)(\\d+)(,)([\\s\\S]+)";
	private Pattern pattern;

	public M3UReader() {
		pattern = Pattern.compile(EXT_PATTERN);

	}

	public M3UList readList(String pathList) throws M3UReaderException, IOException {
		M3UList list = new M3UList();
		BufferedReader br = null;
		try {

			list.setName(pathList.substring(pathList.lastIndexOf(File.separator) + 1));
			if (pathList.toLowerCase().endsWith("m3u8"))
				br = new BufferedReader(new InputStreamReader(new FileInputStream(pathList), StandardCharsets.UTF_8));
			else
				br = new BufferedReader(new FileReader(pathList));

			String line = br.readLine();
			if (line.startsWith("#EXTM3U")) {
				list = readExtended(br, list);
			} else {
				br.close();
				br = new BufferedReader(new FileReader(pathList));
				list = read(br, list);
			}

		} catch (IOException ex) {
			throw ex;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				throw e;
			}
		}

		return list;
	}

	private M3UList read(BufferedReader reader, M3UList list) throws IOException, M3UReaderException {
		String line = null;
		int position = 1;
		while ((line = reader.readLine()) != null) {
			list.getMediaFilesMap().put(position, (getData(null, line, position)));
			position++;
		}
		return list;
	}

	private M3UList readExtended(BufferedReader reader, M3UList list) throws IOException, M3UReaderException {
		String extended = null;
		String line = null;
		int position = 1;
		while ((extended = reader.readLine()) != null) {
			if (!extended.startsWith("#PLAYLIST")) {
				line = reader.readLine();
				list.getMediaFilesMap().put(position, (getData(extended, line, position)));
				position++;
			}
		}
		return list;

	}

	private MediaFile getData(String extendedData, String line, int position) throws M3UReaderException {
		MediaFile mediaFile = new MediaFile();

		try {
			if (extendedData != null) {
				if (extendedData.startsWith("#EXTINF:")) {
					Matcher m = pattern.matcher(extendedData);
					if (m.matches()) {
						mediaFile.setDuration(Long.valueOf(m.group(2)));
					} else {
						mediaFile.setDuration(-1L);
					}
				} else {
					throw new M3UReaderException("Malformed playlist");
				}

			} else {
				mediaFile.setDuration(-1L);
			}

			String ext = line.substring(line.lastIndexOf(".")).toLowerCase();
			mediaFile.setType(ext);
			mediaFile.setName(line.substring(line.lastIndexOf(File.separator) + 1, line.toLowerCase().indexOf(ext)));

			mediaFile.setUrl(line);
			mediaFile.setPosition(position);
		} catch (Exception e) {
			throw new M3UReaderException(e.getCause().getMessage());
		}
		return mediaFile;
	}

}
