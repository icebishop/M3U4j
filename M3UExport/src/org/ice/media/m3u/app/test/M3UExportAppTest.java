package org.ice.media.m3u.app.test;

import org.ice.media.m3u.app.M3UExportApp;
import org.junit.jupiter.api.Test;

class M3UExportAppTest {
	
	private static final String LIST ="/home/ice/Escritorio/Lista de reproducción.m3u";
	private static final String OUTPATH = "/mnt/datos/tmp";

	@Test
	void testMain1() {
		String [] args = {LIST,OUTPATH};
		M3UExportApp.main(args);
	}

	@Test
	void testMain2() {
		String [] args = {LIST,OUTPATH,"mi musica"};
		M3UExportApp.main(args);
	}

	@Test
	void testMain3() {
		String [] args = {LIST,OUTPATH,""};
		M3UExportApp.main(args);
	}

	@Test
	void testMain4() {
		String [] args = {LIST,OUTPATH,"mi musica","30"};
		M3UExportApp.main(args);
	}

	@Test
	void testMain5() {
		String [] args = {LIST,OUTPATH,"","30"};
		M3UExportApp.main(args);
	}

	@Test
	void testMain6() {
		String [] args = {LIST,OUTPATH,"mi musica","30","random"};
		M3UExportApp.main(args);
	}

	@Test
	void testMain7() {
		String[] args = {LIST,
				OUTPATH, "mi musica", "100", "random", "WINDOWS" , "rewrite" };
		M3UExportApp.main(args);
	}
	
	@Test
	void testMain8() {
		String[] args = { LIST,
				OUTPATH, "mi musica", "0", "", "" , "rewrite" };
		M3UExportApp.main(args);
	}

}
