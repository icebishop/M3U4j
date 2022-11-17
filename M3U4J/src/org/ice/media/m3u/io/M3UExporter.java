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
	
	private ProgressBar pb;
	private static final  String FILE_NAME_FILTER = "[`~!¡¿?@#$%^*_+={}\\[\\]()|:;…“’<,>๐฿&äëïöüáéíóúÄËÏÖÜÁÉÍÓÚÑñ]";
	private boolean rewrite;
	
	public boolean isRewrite() {
		return rewrite;
	}

	public void setRewrite(boolean rewrite) {
		this.rewrite = rewrite;
	}

	public M3UExporter(ProgressBar pb) {
		this.pb = pb;
	}

	public List<MediaFile> export(String path, List<MediaFile> mediaFiles, String rootPath)
			throws M3UReaderException {

		pb.setExtraMessage("Export List");
		pb.reset();
		try {

			for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
				MediaFile mediaFile = iterator.next();

				File source = new File(mediaFile.getUrl());
				String newMediaUrl = DirectoryMediaWriter.calculatePath(path,
						mediaFile.getUrl().substring(0, mediaFile.getUrl().lastIndexOf(File.separator)), rootPath);
				mediaFile.setUrl(newMediaUrl.concat(File.separator
						.concat(mediaFile.getUrl().substring(mediaFile.getUrl().lastIndexOf(File.separator) + 1))));
				mediaFile.setUrl(mediaFile.getUrl()
						.replaceAll(FILE_NAME_FILTER, "_"));
				File dest = new File(mediaFile.getUrl());

				write(dest, source);

				pb.step();
				pb.setExtraMessage(mediaFile.getName());

			}

			pb.pause();

			return mediaFiles;

		} catch (Exception e) {
			pb.pause();
			throw new M3UReaderException(e.getMessage());
		}
	}
	
	private void write(File dest,File source) throws M3UReaderException {
		try {
			if(!dest.exists()||rewrite)
				FileUtils.copyFile(source, dest);
		} catch (Exception e) {
			throw new M3UReaderException(e.getMessage());
		}
	}

	public List<MediaFile> exportToNumeratedFiles(String path, List<MediaFile> mediaFiles)
			throws M3UReaderException {

		try {

			String padFormat = String.valueOf(mediaFiles.size());

			padFormat = "%0" + padFormat.length() + "d - ";
			int counter = 1;

			for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
				MediaFile mediaFile = iterator.next();

				File source = new File(mediaFile.getUrl());

				mediaFile.setUrl(path.concat(File.separator.concat(String.format(padFormat, counter))
						.concat(mediaFile.getName().concat(mediaFile.getType()))));
				mediaFile.setUrl(mediaFile.getUrl()
						.replaceAll(FILE_NAME_FILTER, "_"));
				File dest = new File(mediaFile.getUrl());
				
				if(!dest.exists()||rewrite)
					FileUtils.copyFile(source, dest);

				pb.step();
				pb.setExtraMessage(mediaFile.getName());

				counter++;
			}

			pb.pause();

			return mediaFiles;

		} catch (Exception e) {
			throw new M3UReaderException(String.format("Error on write playlist %s", e.getCause()));
		}
	}

}
