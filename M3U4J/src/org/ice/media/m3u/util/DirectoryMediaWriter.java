package org.ice.media.m3u.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryMediaWriter {
	
	public static void makeDirectory(String dirPath) throws IOException {
		
		dirPath = "\"".concat(dirPath.concat("\""));
		Path path = Paths.get(dirPath);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw e;
            }
        }
		
	}
	
	
	public static String calculatePath(String path, String urlMediaFile, String rootPath) {
		String newPath = null;
		
		if(rootPath.startsWith(File.separator))
			rootPath = rootPath.substring(1);
		if(path.endsWith(File.separator))
			path = path.substring(0,path.length()-1);
		if(urlMediaFile.startsWith(File.separator))
			urlMediaFile = urlMediaFile.substring(1);
		
		newPath = urlMediaFile.substring(urlMediaFile.indexOf(rootPath));		
		newPath = path.concat(File.separator.concat(newPath));	
		return newPath;
	}

}
