package br.com.dcm.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import java.nio.file.*;

public class FileUtil {

	public static void copyFile(File source, File dest) throws IOException {
		FileUtils.copyFile(source, dest);
	}

	public static void removeFile(File file) throws IOException {
		try {
			Files.deleteIfExists(Paths.get(file.getPath()));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}
		System.out.println("Deletion successful.");
	}
	
	public static void moveFile(File file, File fileDest) throws IOException {
		copyFile(file, fileDest);
		
		removeFile(file);
}

}
