package org.ice.media.m3u.test.util;

import java.io.IOException;

import org.ice.media.m3u.exception.M3UReaderException;
import org.ice.media.m3u.util.DirectoryMediaWriter;
import org.junit.jupiter.api.Test;

class DirectoryMediaWriterTest {

	@Test
	void test() {
		String path = "";
		
		try {
			path = DirectoryMediaWriter.calculatePath("/mnt/datos/tmp/", "/mnt/datos/mi musica/Andrés Calamaro/2000/", "");
		} catch (M3UReaderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DirectoryMediaWriter.makeDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
			//fail();
		}
	
	}

}
