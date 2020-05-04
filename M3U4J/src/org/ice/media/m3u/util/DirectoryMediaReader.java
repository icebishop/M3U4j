package org.ice.media.m3u.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryMediaReader {
	
	public static List<String> readDirectory(String path, String[] filters) throws IOException{
		
		MediaFileFilter fileFilter = new MediaFileFilter(filters);
		
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {

			List<String> result = walk.map(x -> x.toString())
					.filter(file -> fileFilter.accept(new File(file))).collect(Collectors.toList());

			return result;
			
			

		} catch (IOException e) {
			throw e;
		}

	}

}
