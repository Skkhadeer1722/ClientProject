package com.dataextract.common;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	public String nullReplacement(String value) {
		return value == null ? "" : value;
	}

	public void fileDelete(File fileName) {

		if (fileName.exists()) {
			fileName.delete();
		} else
			System.out.println("file not found");

	}
}
