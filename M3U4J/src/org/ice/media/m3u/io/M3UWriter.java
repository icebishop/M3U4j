package org.ice.media.m3u.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.MediaFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M3UWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private static String mode = "UNIX";
	public static final char CR = (char) 0x0D;
	public static final char LF = (char) 0x0A;

	private M3UWriter() {
		super();
	}

	public static void writeList(boolean isUnicode, boolean isExtended, String relativePath, String playListName,
			List<MediaFile> mediaFiles) throws IOException {
		BufferedWriter writer = null;

		try {

			if (!mediaFiles.isEmpty()) {

				writer = createWriter(isUnicode, playListName);

				if (isExtended) {
					writer.write("#EXTM3U");
					writer.write(newline());
				}

				for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
					MediaFile mediaFile = iterator.next();
					writeMediaFile(mediaFile, isExtended, writer, relativePath);

				}

			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null)
				writer.close();
		}

	}

	private static BufferedWriter createWriter(boolean isUnicode, String playListName) throws FileNotFoundException {
		if (isUnicode) {
			return new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(playListName.concat(".M3U8")), StandardCharsets.UTF_8));
		} else {
			return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(playListName.concat(".M3U"))));
		}
	}

	private static void writeMediaFile(MediaFile mediaFile, boolean isExtended, BufferedWriter writer,
			String relativePath) throws IOException {
		if (mediaFile != null) {
			if (isExtended) {
				writeExtended(writer, mediaFile, relativePath);
			} else {
				writeSimple(writer, mediaFile, relativePath);
			}
		}
		else {
			logger.info("Media File null");
		}
	}

	private static void writeExtended(BufferedWriter writer, MediaFile mediaFile, String relativePath)
			throws IOException {

		writer.write(String.format("#EXTINF:%s,%s", "-1", mediaFile.getName()));
		writer.write(newline());
		if (relativePath.contentEquals(File.separator))
			writer.write(getURL(mediaFile.getUrl()));
		else if (mediaFile.getUrl().indexOf(relativePath) == 0)
			writer.write(getURL(mediaFile.getUrl().substring(relativePath.length() + 1)));
		else
			writer.write(getURL(mediaFile.getUrl().substring(mediaFile.getUrl().indexOf(relativePath))));
		writer.write(newline());

	}

	private static void writeSimple(BufferedWriter writer, MediaFile mediaFile, String relativePath)
			throws IOException {

		if (mediaFile.getUrl().indexOf(relativePath) == 0)
			writer.write(getURL(mediaFile.getUrl().substring(relativePath.length() + 1)));
		else
			writer.write(getURL(mediaFile.getUrl().substring(mediaFile.getUrl().indexOf(relativePath))));
		writer.write(newline());

	}

	private static String getURL(String url) {
		if (mode.equals("WINDOWS")) {
			return "\\".concat(url.replace('/', '\\'));
		} else
			return url;

	}

	private static String newline() {
		if (mode.equals("WINDOWS")) {
			return "" + CR + LF;
		} else
			return "" + LF;
	}

	public static String getMode() {
		return mode;
	}

	public static void setMode(String mode) {
		M3UWriter.mode = mode;
	}

}
