package org.ice.media.m3u.io;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.exception.M3UReaderException;
import org.ice.media.m3u.util.DirectoryMediaWriter;

import me.tongfei.progressbar.ProgressBar;

/**
 * @author ice Export playlist songs to a directory
 */
public class M3UExporter {

	public List<MediaFile> export(String path, List<MediaFile> mediaFiles, String rootPath, String listName)
			throws M3UReaderException {

		try {
			ProgressBar pb = new ProgressBar("Export", mediaFiles.size());
			pb.start();

			for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
				MediaFile mediaFile = iterator.next();

				File source = new File(mediaFile.getUrl());
				String newMediaUrl = DirectoryMediaWriter.calculatePath(path,
						mediaFile.getUrl().substring(0, mediaFile.getUrl().lastIndexOf(File.separator)), rootPath);
				DirectoryMediaWriter.makeDirectory(newMediaUrl);
				mediaFile.setUrl(newMediaUrl.concat(File.separator
						.concat(mediaFile.getUrl().substring(mediaFile.getUrl().lastIndexOf(File.separator) + 1))));

				File dest = new File(mediaFile.getUrl());

				FileUtils.copyFile(source, dest);

				pb.step();
				pb.setExtraMessage(mediaFile.getName());

			}

			pb.stop();

			return mediaFiles;

		} catch (Exception e) {
			throw new M3UReaderException(String.format("Error on write plailist %s", e.getMessage()));
		}
	}

	public List<MediaFile> exportToNumeratedFiles(String path, List<MediaFile> mediaFiles, String listName)
			throws M3UReaderException {

		try {
			ProgressBar pb = new ProgressBar("Export", mediaFiles.size());

			String padFormat = String.valueOf(mediaFiles.size());

			padFormat = "%0" + padFormat.length() + "d - ";
			int counter = 1;
			pb.start();

			for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
				MediaFile mediaFile = iterator.next();

				File source = new File(mediaFile.getUrl());

				mediaFile.setUrl(path.concat(File.separator.concat(String.format(padFormat, counter))
						.concat(mediaFile.getName().concat(mediaFile.getType()))));
				File dest = new File(mediaFile.getUrl());

				FileUtils.copyFile(source, dest);

				pb.step();
				pb.setExtraMessage(mediaFile.getName());

				counter++;
			}

			pb.stop();

			return mediaFiles;

		} catch (Exception e) {
			throw new M3UReaderException(String.format("Error on write playlist %s", e.getCause()));
		}
	}

}
