package org.ice.media.m3u.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.util.DirectoryMediaReader;
import org.junit.jupiter.api.Test;

class DirectoryMediaReaderTest {

	@Test
	void test() {
	
		String[] filters = {"mp3"};
		
		List<Long> time = new ArrayList<Long>(300); 
		
		try {
			for (int i = 0; i < 300; i++) {
				
			
			Date startDate = new Date();
			List<String> listStrings = DirectoryMediaReader.readDirectory("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE", filters);
			time.add(new Date().getTime()- startDate.getTime());
			System.out.println(time.get(time.size()-1));
			System.out.println(listStrings.size());
//			for (Iterator<String> iterator = listStrings.iterator(); iterator.hasNext();) {
//				String string =  iterator.next();
//				System.out.println(string);
//			}
			
			}
			
			long total=0;
			for (Iterator<Long> iterator = time.iterator(); iterator.hasNext();) {
				Long long1 = (Long) iterator.next();
				total = total+long1;
			}
			
			System.out.println(total/time.size() );
		} catch (IOException e) {
			fail(e.getCause());
		}
		
	}

}
