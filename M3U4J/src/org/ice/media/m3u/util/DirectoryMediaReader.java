package org.ice.media.m3u.util;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryMediaReader {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	private DirectoryMediaReader() {
		super();
	}
	
	public static List<String> readDirectory(String path, String[] filters) throws IOException{
		
		MediaFileFilter fileFilter = new MediaFileFilter(filters);
		
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {

			return  walk.map(Path::toString)
					.filter(file -> fileFilter.accept(new File(file))).collect(Collectors.toList());
			

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return new ArrayList<>();

	}

}
