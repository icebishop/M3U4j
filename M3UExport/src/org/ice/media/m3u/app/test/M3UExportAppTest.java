package org.ice.media.m3u.app.test;

import org.ice.media.m3u.app.M3UExportApp;
import org.junit.jupiter.api.Test;

class M3UExportAppTest {

//	@Test
//	void testMain1() {
//		String [] args = {"/home/ice/Escritorio/Lista de reproducción.m3u","/mnt/datos/tmp"};
//		M3UExportApp.main(args);
//	}

//	@Test
//	void testMain2() {
//		String [] args = {"/home/ice/Escritorio/Lista de reproducción.m3u","/mnt/datos/tmp","mi musica"};
//		M3UExportApp.main(args);
//	}

//	@Test
//	void testMain3() {
//		String [] args = {"/home/ice/Escritorio/Lista de reproducción.m3u","/mnt/datos/tmp",""};
//		M3UExportApp.main(args);
//	}

//	@Test
//	void testMain4() {
//		String [] args = {"/home/ice/Escritorio/Lista de reproducción.m3u","/mnt/datos/tmp","mi musica","30"};
//		M3UExportApp.main(args);
//	}

//	@Test
//	void testMain5() {
//		String [] args = {"/home/ice/Escritorio/Lista de reproducción.m3u","/mnt/datos/tmp","","30"};
//		M3UExportApp.main(args);
//	}

//	@Test
//	void testMain6() {
//		String [] args = {"/home/ice/Escritorio/Lista de reproducción.m3u","/mnt/datos/tmp","mi musica","30","random"};
//		M3UExportApp.main(args);
//	}

//	@Test
//	void testMain7() {
//		String[] args = { "/mnt/datos/home/ice/personal/varios/playlists/español.m3u",
//				"/mnt/datos/tmp/usb/playlist", "mi musica", "100", "random", "WINDOWS" , "rewrite" };
//		M3UExportApp.main(args);
//	}
	
	@Test
	void testMain7() {
		String[] args = { "/mnt/datos/home/ice/personal/varios/playlists/gorillaz.m3u",
				"/run/media/ice/0C49-F5DA", "mi musica", "0", "", "" , "rewrite" };
		M3UExportApp.main(args);
	}

}
