package org.ice.media.m3u.util;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryMediaWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	private DirectoryMediaWriter() {
		super();
	}

	public static void makeDirectory(String dirPath) throws IOException {

		dirPath = "\"".concat(dirPath.concat("\""));
		Path path = Paths.get(dirPath);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

	}

	public static String calculatePath(String path, String urlMediaFile, String rootPath){
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
			newPath = urlMediaFile.substring(urlMediaFile.lastIndexOf(File.separator)+1);
			newPath = path.concat(File.separator.concat(rootPath.concat(File.separator.concat(newPath))));
		}
		return newPath;
	}

}
