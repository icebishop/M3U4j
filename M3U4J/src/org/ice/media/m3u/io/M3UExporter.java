package org.ice.media.m3u.io;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.ice.media.m3u.MediaFile;

import me.tongfei.progressbar.ProgressBar;

public class M3UExporter {

	public void export(String path, List<MediaFile> mediaFiles ) {
		
		ProgressBar pb = new ProgressBar("Export", mediaFiles.size());
		
		String padFormat = String.valueOf(mediaFiles.size());
		
		padFormat = "%0"+ padFormat.length()+"d - ";
		int counter = 1;
		pb.start();
		
		for (Iterator<MediaFile> iterator = mediaFiles.iterator(); iterator.hasNext();) {
			MediaFile mediaFile= iterator.next();
			
			File source = new File(mediaFile.getUrl());
	        File dest = new File(path.concat(File.separator.concat(String.format(padFormat, counter)).concat(mediaFile.getName().concat(mediaFile.getType()))));

	        try {
				FileUtils.copyFile(source, dest);
				//System.out.println(String.format("File %s done!", mediaFile.getName()));
				pb.step();
				pb.setExtraMessage(mediaFile.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        counter++;
		}
		pb.stop();
		
	}
	
}
