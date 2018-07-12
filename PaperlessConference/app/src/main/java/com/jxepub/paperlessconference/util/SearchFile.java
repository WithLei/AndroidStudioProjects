package com.jxepub.paperlessconference.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchFile {
	static List<File> files = new ArrayList<File>();

	private static void searchFile(File file) {
		if (file.length() <= 0 || !file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File[] filelsit = file.listFiles();
			for (File file2 : filelsit) {
				if (!file2.isDirectory()) {
					files.add(file2);
				} else {
					getFileList(file);
				}
			}
		} else {
			files.add(file);
		}
	}

	public static List<File> getFileList(File file) {
		searchFile(file);
		return files;
	}
}
