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
import me.tongfei.progressbar.ProgressBarBuilder;
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

		String fileMode = "UNIX";
		if (args.length >= 6)
			fileMode = args[5];

		String rewrite = "";
		if (args.length >= 7)
			rewrite = args[6];

		M3UWriter.setMode(fileMode);

		try {
			M3UReader m3uReader = new M3UReader();
			M3UList m3uList = m3uReader.readList(listPath);

			ProgressBarBuilder pbb = new ProgressBarBuilder().setInitialMax(m3uList.getMediaFiles().size())
					.setStyle(ProgressBarStyle.UNICODE_BLOCK).setTaskName("Export").setUnit("Trck", 1)
					// .setUpdateIntervalMillis(<update interval>)
					.setMaxRenderedLength(150).showSpeed();

			pb = pbb.build();
			// ProgressBar("List Export", m3uList.getMediaFiles().size(),
			// ProgressBarStyle.UNICODE_BLOCK) ;

			M3UExporter exporter = new M3UExporter(pb);
			exporter.setRewrite(rewrite.equals("rewrite"));
			if (mode != null && mode.equals("random")) {
				if (rootPath.equals(exportPath))
					mediaFiles = exporter.exportToNumeratedFiles(exportPath, m3uList.randomize());
				else
					mediaFiles = exporter.export(exportPath, m3uList.randomize(), rootPath);
			} else {
				if (rootPath.equals(exportPath))
					mediaFiles = exporter.exportToNumeratedFiles(exportPath, m3uList.getMediaFiles());
				else
					mediaFiles = exporter.export(exportPath, m3uList.getMediaFiles(), rootPath);
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
			System.out.println(String.format("Error on export playlist: %s", e.getMessage()));
		} finally {
			if (pb != null)
				pb.close();
		}

	}

}
