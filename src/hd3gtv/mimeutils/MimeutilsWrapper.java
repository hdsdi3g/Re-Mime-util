/*
 * 
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * Copyright (C) hdsdi3g for hd3g.tv 2010
 * 
*/

package hd3gtv.mimeutils;

import java.io.File;
import java.util.Collection;

import eu.medsea.mimeutil.MimeUtil;

public class MimeutilsWrapper {
	
	static {
		File mimefile;
		String currentmimefile = (String) System.getProperties().get("magic-mime");
		if (currentmimefile != null) {
			if (currentmimefile.equals("") == false) {
				mimefile = new File(currentmimefile);
				if (mimefile.exists() == false) {
					mimefile = new File("magic.mime");
					if (mimefile.exists()) {
						System.getProperties().put("magic-mime", mimefile.getPath());
					}
				}
			}
		}
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector");
		MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.WindowsRegistryMimeDetector");
		// MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.OpendesktopMimeDetector");
	}
	
	public static String getMime(File file) {
		try {
			Collection<?> mimeTypes = MimeUtil.getMimeTypes(file);
			return String.valueOf(mimeTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return "application/octet-streamA";
		}
	}
	
}
