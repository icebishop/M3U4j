package org.ice.media.m3u.app;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.exception.M3UReaderException;
import org.ice.media.m3u.io.M3UExporter;
import org.ice.media.m3u.io.M3UReader;
import org.ice.media.m3u.io.M3UWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;

public class M3UExportApp {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private static String listPath;
	private static String exportPath;
	private static String rootPath;
	private static int split;
	private static String mode;
	private static String fileMode;
	private static String rewrite;

	public static void main(String[] args) {

		ProgressBar pb = null;

		List<MediaFile> mediaFiles = null;

		setParameters(args);

		M3UWriter.setMode(fileMode);

		try {
			M3UReader m3uReader = new M3UReader();
			M3UList m3uList = m3uReader.readList(listPath);

			ProgressBarBuilder pbb = new ProgressBarBuilder().setInitialMax(m3uList.getMediaFiles().size())
					.setStyle(ProgressBarStyle.UNICODE_BLOCK).setTaskName("Export").setUnit("Trck", 1)
					// .setUpdateIntervalMillis(<update interval>)
					.setMaxRenderedLength(150).showSpeed();

			pb = pbb.build();

			mediaFiles = exportList(pb, m3uList);

			if (split > 0) {
				M3UList m3uList2 = new M3UList();
				m3uList2.setName(m3uList.getName().substring(0, m3uList.getName().lastIndexOf(".")));
				m3uList2.setMediaFiles(mediaFiles);

				List<M3UList> m3uLists = m3uList2.split(split);

				for (Iterator<M3UList> iterator = m3uLists.iterator(); iterator.hasNext();) {
					M3UList m3uList3 = iterator.next();

					M3UWriter.writeList(true, true, rootPath,
							exportPath.concat(File.separator.concat(m3uList3.getName())), m3uList3.getMediaFiles());
				}

			} else
				M3UWriter.writeList(true, true, rootPath,
						exportPath.concat(File.separator
								.concat(m3uList.getName().substring(0, m3uList.getName().lastIndexOf(".")))),
						mediaFiles);

		} catch (Exception e) {
			if (pb != null)
				pb.setExtraMessage(e.getMessage());
			logger.error(String.format("Error on export playlist: %s", e.getMessage()));
		} finally {
			if (pb != null)
				pb.close();
		}

	}

	private static List<MediaFile> exportList(ProgressBar pb, M3UList m3uList) throws M3UReaderException {

		M3UExporter exporter = new M3UExporter(pb);
		exporter.setRewrite(rewrite.equals("rewrite"));
		List<MediaFile> mediaFiles;
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

		return mediaFiles;
	}

	private static void setParameters(String[] args) {
		listPath = args[0];

		exportPath = args[1];

		rootPath = "";

		if (args.length >= 3)
			rootPath = args[2];

		if (rootPath.equals(""))
			rootPath = exportPath;

		split = 0;
		if (args.length >= 4)
			split = Integer.valueOf(args[3]);

		mode = null;
		if (args.length >= 5)
			mode = args[4];

		fileMode = "UNIX";
		if (args.length >= 6)
			fileMode = args[5];

		rewrite = "";
		if (args.length >= 7)
			rewrite = args[6];
	}

}
