package org.ice.media.m3u.app;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.io.M3UExporter;
import org.ice.media.m3u.io.M3UReader;

public class M3UExportApp {

	public static void main(String[] args) {
		//"/home/ice/Escritorio/test.m3u"
		String listPath = args[0];
		//"/run/media/ice/43DF34C022951566"
		String exportPath = args[1];
		
		String mode = null;
		if(args.length == 3 )
			mode = args[2];
		
		M3UReader m3uReader = new M3UReader();
		M3UList m3uList = m3uReader.readList(listPath);
		
		M3UExporter exporter = new M3UExporter();
		if(mode != null && mode.equals("random")) {
			exporter.export(exportPath, m3uList.randomize());
		}else {
			exporter.export(exportPath, m3uList.getMediaFiles());
		}
		
		
		
		
	}

}
