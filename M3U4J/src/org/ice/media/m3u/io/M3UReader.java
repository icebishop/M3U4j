package org.ice.media.m3u.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.ice.media.m3u.M3UList;
import org.ice.media.m3u.MediaFile;

public class M3UReader {

	public M3UList readList(String pathList) {
		M3UList list = new M3UList();
		BufferedReader br = null;
		try  {
			 br =   new BufferedReader(new FileReader(pathList));
 
			String line = br.readLine();
            if(line.startsWith("#EXTM3U")) {
            	list = readExtended(br, list);
            }
            else {
            	br.close();
            	br =   new BufferedReader(new FileReader(pathList));
            	list = read(br, list);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	private M3UList read(BufferedReader reader, M3UList list) throws IOException {
		 String line = null;
		 int position = 1;
		while ((line = reader.readLine()) != null) {
            list.getMediaFilesMap().put(position, (getData(null, line, position)));
            position ++;
         }
		return list;
	}

	private M3UList readExtended(BufferedReader reader, M3UList list) throws IOException {
		String extended = null;
		String line = null;
		int position = 1;
		while ((extended = reader.readLine()) != null) {
			line = reader.readLine();
			list.getMediaFilesMap().put(position, (getData(extended, line, position)));
            position ++;
         }
		return list;

	}

	private MediaFile getData(String extendedData, String line, int position) {
		MediaFile mediaFile = new MediaFile();

		try {
			if (extendedData != null) {
				if (extendedData.startsWith("#EXTINF:")) {
					// todo implementar extended
				}

			} else {
				String ext = line.substring(line.lastIndexOf(".")).toLowerCase();
				mediaFile.setType(ext);
				mediaFile.setName(line.substring(line.lastIndexOf(File.separator)+1, line.toLowerCase().indexOf(ext)));
				mediaFile.setDuration(-1L);
			}

			mediaFile.setUrl(line);
			mediaFile.setPosition(position);
		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
		}
		

		return mediaFile;
	}

}
