package org.ice.media.m3u.test.io;

import static org.junit.jupiter.api.Assertions.*;

import org.ice.media.m3u.io.M3UCreator;
import org.ice.media.m3u.io.M3UCreatorData;
import org.junit.jupiter.api.Test;

class M3UCreatorTest {
	
	private static String path = "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE";

	@Test
	void testCreate01() {

		M3UCreator m3uCreator = new M3UCreator();
		
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test1");
		data.setUnicode(true);
		data.setExtended(true);
		data.setAbsolute(true);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(true);
		data.setSplit(100);
		try {
			
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate02() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test2");
		data.setUnicode(true);
		data.setExtended(true);
		data.setAbsolute(true);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(false);
		data.setSplit(100);
		try {
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate03() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test3");
		data.setUnicode(true);
		data.setExtended(true);
		data.setAbsolute(false);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(true);
		data.setSplit(100);
		try {
			
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate04() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test4");
		data.setUnicode(true);
		data.setExtended(false);
		data.setAbsolute(true);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(true);
		data.setSplit(100);
		try {
			
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate05() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test5");
		data.setUnicode(false);
		data.setExtended(true);
		data.setAbsolute(true);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(true);
		data.setSplit(100);
		try {
			
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate06() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test6");
		data.setUnicode(false);
		data.setExtended(true);
		data.setAbsolute(true);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(false);
		data.setSplit(100);
		try {
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate07() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test7");
		data.setUnicode(false);
		data.setExtended(true);
		data.setAbsolute(false);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(true);
		data.setSplit(100);
		try {
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate08() {

		M3UCreator m3uCreator = new M3UCreator();
		M3UCreatorData data = new  M3UCreatorData();
		String[] filters = { "mp3" };
		data.setDirectory(path);
		data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test8");
		data.setUnicode(false);
		data.setExtended(false);
		data.setAbsolute(true);
		data.setRelativePath(path);
		data.setFilters(filters);
		data.setRandom(true);
		data.setSplit(100);
		try {
			
			m3uCreator.create(data);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}
	}
	
		@Test
		void testCreate09() {

			M3UCreator m3uCreator = new M3UCreator();
			M3UCreatorData data = new  M3UCreatorData();
			String[] filters = { "mp3" };
			data.setDirectory(path);
			data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test9");
			data.setUnicode(false);
			data.setExtended(false);
			data.setAbsolute(true);
			data.setRelativePath(path);
			data.setFilters(filters);
			data.setRandom(false);
			data.setSplit(100);
			try {
				
				m3uCreator.create(data);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}
		
		@Test
		void testCreate10() {

			M3UCreator m3uCreator = new M3UCreator();
			M3UCreatorData data = new  M3UCreatorData();
			String[] filters = { "mp3" };
			data.setDirectory(path);
			data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test10");
			data.setUnicode(false);
			data.setExtended(false);
			data.setAbsolute(false);
			data.setRelativePath(path);
			data.setFilters(filters);
			data.setRandom(false);
			data.setSplit(100);
			try {
				
				m3uCreator.create(data);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}
		
		@Test
		void testCreate11() {

			M3UCreator m3uCreator = new M3UCreator();
			M3UCreatorData data = new  M3UCreatorData();
			String[] filters = { "mp3" };
			data.setDirectory(path);
			data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test11");
			data.setUnicode(false);
			data.setExtended(false);
			data.setAbsolute(false);
			data.setRelativePath(path);
			data.setFilters(filters);
			data.setRandom(false);
			data.setSplit(100);
			try {
				
				m3uCreator.create(data);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}
		
	@Test
		void testCreate12() {

			M3UCreator m3uCreator = new M3UCreator();
			M3UCreatorData data = new  M3UCreatorData();
			String[] filters = { "mp3" };
			data.setDirectory(path);
			data.setPlayListName("/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test12");
			data.setUnicode(true);
			data.setExtended(false);
			data.setAbsolute(false);
			data.setRelativePath(path);
			data.setFilters(filters);
			data.setRandom(false);
			data.setSplit(100);
			try {
				
				m3uCreator.create(data);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}	

}
