package org.ice.media.m3u.app;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.io.M3UExporter;
import org.ice.media.m3u.io.M3UReader;
import org.ice.media.m3u.io.M3UWriter;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;

public class M3UExportApp {

	public static void main(String[] args) {
		
		ProgressBar pb = null; 
			
		List<MediaFile> mediaFiles = null;
		
		String listPath = args[0];
		
		String exportPath = args[1];

		String rootPath = "";

		if (args.length >= 3)
			rootPath = args[2];

		if (rootPath.equals(""))
			rootPath = exportPath;

		int split = 0;
		if (args.length >= 4)
			split = Integer.valueOf(args[3]);

		String mode = null;
		if (args.length >= 5)
			mode = args[4];

		try {
			M3UReader m3uReader = new M3UReader();
			M3UList m3uList = m3uReader.readList(listPath);
			
			pb = new ProgressBar("List Export", m3uList.getMediaFiles().size(), ProgressBarStyle.UNICODE_BLOCK) ;
			
			
			M3UExporter exporter = new M3UExporter(pb  );
			if (mode != null && mode.equals("random")) {
				if (rootPath.equals(exportPath))
					mediaFiles = exporter.exportToNumeratedFiles(exportPath, m3uList.randomize(), m3uList.getName());
				else
					mediaFiles = exporter.export(exportPath, m3uList.randomize(), rootPath, m3uList.getName());
			} else {
				if (rootPath.equals(exportPath))
					mediaFiles = exporter.exportToNumeratedFiles(exportPath, m3uList.getMediaFiles(), m3uList.getName());
				else
					mediaFiles = exporter.export(exportPath, m3uList.getMediaFiles(), rootPath, m3uList.getName());
			}

			if (split > 0) {
				M3UList m3uList2 = new M3UList();
				m3uList2.setName(m3uList.getName().substring(0, m3uList.getName().lastIndexOf(".")));
				m3uList2.setMediaFiles(mediaFiles);

				List<M3UList> m3uLists = m3uList2.split(split);

				for (Iterator<M3UList> iterator = m3uLists.iterator(); iterator.hasNext();) {
					M3UList m3uList3 = (M3UList) iterator.next();

					M3UWriter.writeList(true, true, rootPath,
							exportPath.concat(File.separator.concat(m3uList3.getName())), m3uList3.getMediaFiles());
				}

			} else
				M3UWriter.writeList(true, true, rootPath,
						exportPath.concat(File.separator
								.concat(m3uList.getName().substring(0, m3uList.getName().lastIndexOf(".")))),
						mediaFiles);

		} catch (Exception e) {		
			e.printStackTrace();
			System.out.println(String.format("Error on export playlist"));
		}finally {
			if(pb != null)
				pb.stop();
		}

	}

}
