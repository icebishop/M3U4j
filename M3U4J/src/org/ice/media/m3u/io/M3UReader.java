package org.ice.media.m3u.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.exception.M3UReaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ice Read a M3U playlist simple or extended
 */
public class M3UReader {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private static final String EXT_PATTERN = "(#EXTINF:)(\\d+)(,)([\\s\\S]+)";
	private Pattern pattern;

	public M3UReader() {
		pattern = Pattern.compile(EXT_PATTERN);

	}

	private BufferedReader createBufferedReader(String pathList) {
		if (pathList.toLowerCase().endsWith("m3u8")) {
			try (BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(pathList), StandardCharsets.UTF_8))) {
				return br;
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		} else {
			try (BufferedReader br = new BufferedReader(new FileReader(pathList))) {
				return br;
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return null;
	}

	public M3UList readList(String pathList) throws M3UReaderException, IOException {
		M3UList list = new M3UList();

		BufferedReader br = createBufferedReader(pathList);
		if (br != null) {
			String line = br.readLine();
			if (line.startsWith("#EXTM3U")) {
				list = readExtended(br);
			} else {
				br.close();
				br = createBufferedReader(pathList);
				if (br != null) {
					list = read(br);
				}
			}

			list.setName(pathList.substring(pathList.lastIndexOf(File.separator) + 1));
		}

		return list;
	}

	private M3UList read(BufferedReader reader) throws IOException, M3UReaderException {
		String line = null;
		int position = 1;
		M3UList list = new M3UList();
		while ((line = reader.readLine()) != null) {
			list.getMediaFilesMap().put(position, (getData(null, line, position)));
			position++;
		}
		return list;
	}

	private M3UList readExtended(BufferedReader reader) throws IOException, M3UReaderException {
		String extended = null;
		String line = null;
		M3UList list = new M3UList();
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
