package com.devcortes.hadoop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.hadoop.hadoopservice.fieldcount.HadoopDriverFieldCount;
import com.devcortes.hadoop.hadoopservice.filterbrand.HadoopDriverBrand;
import com.devcortes.hadoop.hadoopservice.hdfs.HDFSService;
import com.devcortes.hadoop.hadoopservice.rewritedbotfile.HadoopDriverBrandRewrite;
import com.devcortes.hadoop.hadoopservice.sqoop.SqoopImportToHdfs;

@RestController
public class HadoopController {

	@Autowired
	private HadoopDriverFieldCount hadoopDriverFieldCount;

	@Autowired

	private HadoopDriverBrandRewrite hadoopDriverRewrite;

	@Autowired
	private HadoopDriverBrand hadoopDriver;

	@Autowired
	private SqoopImportToHdfs sQoopImportToHdfs;

	@Autowired
	private HDFSService hdfsService;

	@RequestMapping(value = "/fieldcount", method = RequestMethod.GET, produces = "application/json")
	public Integer fieldcount() throws Exception {
		return hadoopDriverFieldCount.runJob();
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET, produces = "application/json")
	public Integer filter() throws Exception {
		return hadoopDriver.runJob();
	}

	@RequestMapping(value = "/rewrite", method = RequestMethod.GET, produces = "application/json")
	public Integer rewrite() throws Exception {
		return hadoopDriverRewrite.runJob();
	}

	@RequestMapping(value = "/sqoop/import", method = RequestMethod.GET, produces = "application/json")
	public void sqoopeImport() throws Exception {

		sQoopImportToHdfs.importTableToHdfs();
	}

	@RequestMapping(value = "/hdfs/upload", method = RequestMethod.GET, produces = "application/json")
	public void upload() throws Exception {
		hdfsService.upload();
	}

	@RequestMapping(value = "/hdfs/view", method = RequestMethod.GET, produces = "application/json")
	public void viewFile() throws Exception {
		hdfsService.viewFile();
	}

	@RequestMapping(value = "/hdfs/list", method = RequestMethod.GET, produces = "application/json")
	public void listDirectory() throws Exception {
		hdfsService.listDirectory();
	}

	@RequestMapping(value = "/hdfs/create", method = RequestMethod.GET, produces = "application/json")
	public void createDirectory() throws Exception {
		hdfsService.createDirectory();
	}

	@RequestMapping(value = "/hdfs/download", method = RequestMethod.GET, produces = "application/json")
	public void downloadFile() throws Exception {
		hdfsService.downloadFile();
	}

	@RequestMapping(value = "/hdfs/delete", method = RequestMethod.GET, produces = "application/json")
	public void delete() throws Exception {
		hdfsService.deleteFile();
	}

}
