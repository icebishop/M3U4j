package org.ice.media.m3u.test.util;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.ice.media.m3u.util.DirectoryMediaWriter;
import org.junit.jupiter.api.Test;

class DirectoryMediaWriterTest {

	@Test
	void test() {
		String path = "";
		
		path = DirectoryMediaWriter.calculatePath("/mnt/datos/tmp/", "/mnt/datos/mi musica/Andrés Calamaro/2000/", "");
		try {
			DirectoryMediaWriter.makeDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail("");
		}
	
	}

}
