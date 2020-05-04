package org.ice.media.m3u.test.io;

import static org.junit.jupiter.api.Assertions.*;

import org.ice.media.m3u.io.M3UCreator;
import org.junit.jupiter.api.Test;

class M3UCreatorTest {

	@Test
	void testCreate01() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test1",
				    	   /*unicode*/ true,
				    	  /*extended*/ true,
				    	  /*absolute*/ true,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ true,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate02() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test2",
				    	   /*unicode*/ true,
				    	  /*extended*/ true,
				    	  /*absolute*/ true,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ false,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate03() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test3",
				    	   /*unicode*/ true,
				    	  /*extended*/ true,
				    	  /*absolute*/ false,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ true,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate04() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test4",
				    	   /*unicode*/ true,
				    	  /*extended*/ false,
				    	  /*absolute*/ true,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ true,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate05() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test5",
				    	   /*unicode*/ false,
				    	  /*extended*/ true,
				    	  /*absolute*/ true,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ true,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate06() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test6",
				    	   /*unicode*/ false,
				    	  /*extended*/ true,
				    	  /*absolute*/ true,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ false,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate07() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test7",
				    	   /*unicode*/ false,
				    	  /*extended*/ true,
				    	  /*absolute*/ false,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ true,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}

	}
	
	@Test
	void testCreate08() {

		M3UCreator m3uCreator = new M3UCreator();

		try {
			String[] filters = { "mp3" };
			m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test8",
				    	   /*unicode*/ false,
				    	  /*extended*/ false,
				    	  /*absolute*/ true,
				    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
				    	   /*filters*/ filters,
				    	    /*random*/ true,
				    	    /*split */ 100);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getCause());
		}
	}
	
		@Test
		void testCreate09() {

			M3UCreator m3uCreator = new M3UCreator();

			try {
				String[] filters = { "mp3" };
				m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test9",
					    	   /*unicode*/ false,
					    	  /*extended*/ false,
					    	  /*absolute*/ true,
					    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	   /*filters*/ filters,
					    	    /*random*/ false,
					    	    /*split */ 100);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}
		
		@Test
		void testCreate10() {

			M3UCreator m3uCreator = new M3UCreator();

			try {
				String[] filters = { "mp3" };
				m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test10",
					    	   /*unicode*/ false,
					    	  /*extended*/ false,
					    	  /*absolute*/ false,
					    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	   /*filters*/ filters,
					    	    /*random*/ true,
					    	    /*split */ 100);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}
		
		@Test
		void testCreate11() {

			M3UCreator m3uCreator = new M3UCreator();

			try {
				String[] filters = { "mp3" };
				m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test11",
					    	   /*unicode*/ false,
					    	  /*extended*/ false,
					    	  /*absolute*/ false,
					    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	   /*filters*/ filters,
					    	    /*random*/ false,
					    	    /*split */ 100);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}
		
	@Test
		void testCreate12() {

			M3UCreator m3uCreator = new M3UCreator();

			try {
				String[] filters = { "mp3" };
				m3uCreator.create(/*path*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	 /*list name*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE/test12",
					    	   /*unicode*/ true,
					    	  /*extended*/ false,
					    	  /*absolute*/ false,
					    	  /*relative*/ "/mnt/datos/tmp/Musica Decembrina/LOS MEJORES DE DICIEMBRE",
					    	   /*filters*/ filters,
					    	    /*random*/ false,
					    	    /*split */ 100);
			} catch (Exception e) {
				e.printStackTrace();
				fail(e.getCause());
			}

	}	

}
