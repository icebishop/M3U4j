package org.ice.media.m3u.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ice.media.m3u.exception.M3UReaderException;

public class DirectoryMediaWriter {

	public static void makeDirectory(String dirPath) throws IOException {

		dirPath = "\"".concat(dirPath.concat("\""));
		Path path = Paths.get(dirPath);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				throw e;
			}
		}

	}

	public static String calculatePath(String path, String urlMediaFile, String rootPath) throws M3UReaderException {
		String newPath = null;

		if (rootPath.startsWith(File.separator))
			rootPath = rootPath.substring(1);
		if (path.endsWith(File.separator))
			path = path.substring(0, path.length() - 1);
		if (urlMediaFile.startsWith(File.separator))
			urlMediaFile = urlMediaFile.substring(1);

		int index = urlMediaFile.indexOf(rootPath);

		if (index >= 0) {
			newPath = urlMediaFile.substring(index);
			newPath = path.concat(File.separator.concat(newPath));
		}else {
			throw new M3UReaderException(String.format("Not found %s in %s", rootPath, urlMediaFile));
		}
		return newPath;
	}

}
