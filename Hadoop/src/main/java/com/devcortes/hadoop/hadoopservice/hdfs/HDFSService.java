package com.devcortes.hadoop.hadoopservice.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.annotation.PostConstruct;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HDFSService {

	private static final Logger LOGGER = Logger.getLogger(HDFSService.class);
	private static final String LOCAL_PATH = "input.txt";
	private static final String LOCAL_PATH_DOWNLOAD = "/home/cortes";
	private static final String DESTINATION_PATH = "hdfs://localhost:54310/user";
	private static final String URL_HDFS = "hdfs://localhost:54310/";
	private static final String HDFS_PATH_READ = "hdfs://localhost:54310/user/input.txt";
	private static final Integer DEFAULT_SIZE_BUFFER = 4096;
	private static final String ROOT_URL = "/";
	private static final String PATH_NEW_DIRECTORY = "hdfs://localhost:54310/newdirectory";
	private static final String PATH_FILE_DOWNLOAD = "hdfs://localhost:54310/user/input.txt";
	private static final String PATH_FILE_DELETE = "hdfs://localhost:54310/newdirectory";

	private FileSystem fileSystem;

	@PostConstruct
	public void setup() throws IOException {
		Configuration configuration = new Configuration();
		fileSystem = FileSystem.get(URI.create(URL_HDFS), configuration);
	}

	public void upload() throws IOException {
		fileSystem.copyFromLocalFile(new Path(LOCAL_PATH), new Path(DESTINATION_PATH));
	}

	public void listDirectory() throws FileNotFoundException, IllegalArgumentException, IOException {
		FileStatus[] files = fileSystem.listStatus(new Path(ROOT_URL));
		for (FileStatus file : files) {
			System.out.println(file.getPath().getName());
		}
	}

	public void viewFile() throws IOException {
		InputStream fileInputStream = fileSystem.open(new Path(HDFS_PATH_READ));
		try {
			IOUtils.copyBytes(fileInputStream, System.out, DEFAULT_SIZE_BUFFER, false);
		} finally {
			IOUtils.closeStream(fileInputStream);
		}
	}
	
	public void createDirectory() throws IllegalArgumentException, IOException  {
		boolean yesOrNo = fileSystem.mkdirs(new Path(PATH_NEW_DIRECTORY));
		System.out.println("Status of given directory " + yesOrNo);
		
	}
	
	public void downloadFile() throws IllegalArgumentException, IOException   {
		fileSystem.copyToLocalFile(new Path(PATH_FILE_DOWNLOAD), new Path(LOCAL_PATH_DOWNLOAD));
	}
	
	public void deleteFile() throws IllegalArgumentException, IOException   {
		fileSystem.delete(new Path(PATH_FILE_DELETE), true);
	}
}
