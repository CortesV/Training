package com.devcortes.hadoop.hadoopservice.sqoop;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class SqoopImportToHdfs {

	public void importTableToHdfs() throws IOException {
		String[] command = { "/bin/bash", "-c",
				"~/Programs/sqoop-1.4.6/bin/sqoop import --bindir ./ --connect jdbc:mysql://sb-db01.softbistro.online/vechicle --username root --password rotrotrot --table car --m 1" };
		Process proc = Runtime.getRuntime().exec(command);
	}

}
