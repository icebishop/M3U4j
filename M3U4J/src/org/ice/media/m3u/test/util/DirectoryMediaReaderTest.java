package org.ice.media.m3u.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.util.DirectoryMediaReader;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DirectoryMediaReaderTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Test
	void test() {
	
		String[] filters = {"mp3"};
		
		List<Long> time = new ArrayList<>(300); 
		
		try {
			for (int i = 0; i < 300; i++) {
				
			
			Date startDate = new Date();
			List<String> listStrings = DirectoryMediaReader.readDirectory("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE", filters);
			time.add(new Date().getTime()- startDate.getTime());
			logger.info("{}",time.get(time.size()-1));
			logger.info("{}",listStrings.size());
			
			}
			
			long total=0;
			for (Iterator<Long> iterator = time.iterator(); iterator.hasNext();) {
				Long long1 = iterator.next();
				total = total+long1;
			}
			
			logger.info("{}",total/time.size());
		} catch (IOException e) {
			fail(e.getCause());
		}
		
	}

}
