package org.ice.media.m3u.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;
import org.ice.media.m3u.io.M3UReader;
import org.ice.media.m3u.io.M3UWriter;

import me.tongfei.progressbar.ProgressBar;
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

			pb = new ProgressBar("List Check", m3uList.getMediaFiles().size(), ProgressBarStyle.UNICODE_BLOCK);
			pb.start();
			mediaFiles = m3uList.getMediaFiles();

			for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
				MediaFile mediaFile = (MediaFile) iterator.next();
				f = new File(mediaFile.getUrl());
				if (!f.exists()) {
					errorMediaFiles.add(mediaFile);
				}
				pb.step();
			}
			pb.stop();
			
			if(errorMediaFiles.isEmpty()) {
				System.out.println(String.format("List %s OK", m3uList.getName()));
			}else {
				System.out.println(String.format("Errors on list %s:", m3uList.getName()));
				for (Iterator<MediaFile> iterator = errorMediaFiles.iterator(); iterator.hasNext();) {
					MediaFile mediaFile = (MediaFile) iterator.next();
					System.out.println(String.format("%s : %s " , mediaFile.getPosition(), mediaFile.getUrl()));
				}
				 Scanner in = new Scanner(System.in);
				System.out.println("Repair list? (Y/N)" );
				String repair =  in.nextLine();
				String newpath = "";
				if(repair.equals("Y")) {
					for (Iterator<MediaFile> iterator = errorMediaFiles.iterator(); iterator.hasNext();) {
						MediaFile mediaFile = (MediaFile) iterator.next();
						System.out.println(String.format("Set path for %s : %s " , mediaFile.getPosition(), mediaFile.getUrl()));
						newpath = in.nextLine();
						mediaFile.setUrl(newpath);
						m3uList.getMediaFilesMap().put(mediaFile.getPosition(), mediaFile);
					}
					M3UWriter.writeList(true, true, "", listPath, m3uList.getMediaFiles());
				}
				 in.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pb.stop();
		}

	}

}
