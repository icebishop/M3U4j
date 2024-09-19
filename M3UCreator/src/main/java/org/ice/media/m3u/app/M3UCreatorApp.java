package org.ice.media.m3u.app;

import java.io.File;
import java.lang.invoke.MethodHandles;

import org.ice.media.m3u.io.M3UCreator;
import org.ice.media.m3u.io.M3UCreatorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M3UCreatorApp {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	public static void main(String[] args) {
		boolean isUnicode = false;
		boolean isExtended = true;
		boolean isAbsolute = false;
		String relative = null;
		String[] filters = { "mp3", "flac" };
		boolean isRandom = false;
		String path = null;
		String listName = null;
		int split = 0;
		
		try {

			if (args.length >= 2) {

				path = args[0];
				relative = args[0];
				listName = args[0].concat(File.separator).concat(args[1]);
			}

			if (args.length >= 3)
				isRandom = Boolean.valueOf(args[2]);

			if (args.length >= 4)
				split = Integer.valueOf(args[3]);
			
			if (args.length >= 5)
				isExtended = Boolean.valueOf(args[4]);

			if (args.length >= 7) {
				isAbsolute = Boolean.valueOf(args[5]);
				relative = args[6];
			}

			if (args.length >= 8) {
				filters = args[7].split(",");
			}

			if (args.length >= 9) {
				isUnicode = Boolean.valueOf(args[8]);
			}

			M3UCreator m3uCreator = new M3UCreator();
			M3UCreatorData data = new  M3UCreatorData();
			
			data.setDirectory(path);
			data.setPlayListName(listName);
			data.setUnicode(isUnicode);
			data.setExtended(isExtended);
			data.setAbsolute(isAbsolute);
			data.setRelativePath(relative);
			data.setFilters(filters);
			data.setRandom(isRandom);
			data.setSplit(split);
			m3uCreator.create(data);

		} catch (Exception e) {
			logger.error(String.format("Error on create playlist: %s ", e.getMessage()));
		}

	}

}