package org.ice.media.m3u.app;

import java.io.File;

import org.ice.media.m3u.io.M3UCreator;

public class M3UCreatorApp {

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
			m3uCreator.create(/* path */ path, /* list name */ listName, /* unicode */ isUnicode,
					/* extended */ isExtended, /* absolute */ isAbsolute, /* relative */ relative,
					/* filters */ filters, /* random */ isRandom, split);

		} catch (Exception e) {
			System.out.println(String.format("Error on create playlist: %s : %s", e.getMessage()));
		}

	}

}