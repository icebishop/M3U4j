package org.ice.media.m3u.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.io.M3UReader;
import org.ice.media.m3u.io.M3UWriter;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;

public class M3UCheckApp {

	public static void main(String[] args) {
		ProgressBar pb = null;

		List<MediaFile> mediaFiles = null;
		List<MediaFile> errorMediaFiles = new ArrayList<MediaFile>();

		String listPath = args[0];

		File f = null;

		try {
			M3UReader m3uReader = new M3UReader();
			M3UList m3uList = m3uReader.readList(listPath);

			ProgressBarBuilder pbb = new ProgressBarBuilder()
				    .setInitialMax(m3uList.getMediaFiles().size())
				    .setStyle(ProgressBarStyle.UNICODE_BLOCK)
				    .setTaskName("List Check")
				    //.setUnit(<unit name>, <unit size>)
				    //.setUpdateIntervalMillis(<update interval>)
				    .setMaxRenderedLength(10)
				    .showSpeed();
			
			pb = pbb.build();
			//pb.start();
			mediaFiles = m3uList.getMediaFiles();
			long megabytes = 0;
			for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
				MediaFile mediaFile = (MediaFile) iterator.next();
				f = new File(mediaFile.getUrl());
				if (!f.exists()) {
					errorMediaFiles.add(mediaFile);
				}
				else {
					megabytes = megabytes + (f.length()/1024/1024);
				}
				pb.step();
			}
			pb.pause();
			
			if(errorMediaFiles.isEmpty()) {
				System.out.println(String.format("List %s OK Size %s MB", m3uList.getName(), megabytes));
			}else {
				System.out.println(String.format("%s Errors on list %s:",errorMediaFiles.size(), m3uList.getName()));
				for (Iterator<MediaFile> iterator = errorMediaFiles.iterator(); iterator.hasNext();) {
					MediaFile mediaFile = (MediaFile) iterator.next();
					System.out.println(String.format("%s : %s " , mediaFile.getPosition(), mediaFile.getUrl()));
				}
				 //Scanner in = new Scanner(System.in);
				System.out.println("Repair list? (Y/N)" );
				String repair =  System.console().readLine();
				System.out.println(repair);
				String newpath = "";
				if(repair.equals("Y")) {
					for (Iterator<MediaFile> iterator = errorMediaFiles.iterator(); iterator.hasNext();) {
						MediaFile mediaFile = (MediaFile) iterator.next();
						System.out.println(String.format("Set path for %s : %s " , mediaFile.getPosition(), mediaFile.getUrl()));
						newpath = System.console().readLine();
						System.out.println(newpath);
						mediaFile.setUrl(newpath);
						m3uList.getMediaFilesMap().put(mediaFile.getPosition(), mediaFile);
					}
					M3UWriter.writeList(true, true, File.separator, listPath, m3uList.getMediaFiles());
				}
				// in.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pb.close();
		}

	}

}
