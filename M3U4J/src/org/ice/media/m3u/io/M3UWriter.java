package org.ice.media.m3u.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.MediaFile;

public class M3UWriter {

	public static String mode = "UNIX";
	public final static char CR = (char) 0x0D;
	public final static char LF = (char) 0x0A;

	public static void writeList(boolean isUnicode, boolean isExtended, String relativePath, String playListName,
			List<MediaFile> mediaFiles) throws Exception {
		BufferedWriter writer = null;

		try {

			if (mediaFiles.size() > 0) {
				if (isUnicode) {
					writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(playListName.concat(".M3U8")), StandardCharsets.UTF_8));
				} else {
					writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(playListName.concat(".M3U"))));
				}

				if (isExtended) {
					writer.write("#EXTM3U");
					writer.write(newline());
				}

				for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
					MediaFile mediaFile = iterator.next();
					if (mediaFile != null)
						if (isExtended) {
							writeExtended(writer, mediaFile, relativePath);
						} else {
							writeSimple(writer, mediaFile, relativePath);
						}
					else
						System.out.println("Media File null");
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				throw e;
			}
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
			return  "\\".concat( url.replace('/', '\\'));
		} else
			return url;

	}

	private static String newline() {
		if (mode.equals("WINDOWS")) {
			return ""+CR+LF;
		} else
			return ""+LF;
	}

}
