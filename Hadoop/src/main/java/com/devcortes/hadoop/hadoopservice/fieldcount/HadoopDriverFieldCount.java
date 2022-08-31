package com.devcortes.hadoop.hadoopservice.fieldcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.devcortes.hadoop.HadoopApplication;

@Service
public class HadoopDriverFieldCount extends Configured implements Tool {

	private static final Logger LOGGER = Logger.getLogger(HadoopDriverFieldCount.class);
	private static final String JOB_SUCCESSFUL = "Job was successful";
	private static final String JOB_NOT_SUCCESSFUL = "Job was not successful";

	public Integer runJob() throws Exception{
		
		return ToolRunner.run(new HadoopDriverFieldCount(), null);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver",
				"jdbc:mysql://sb-db01.softbistro.online/vechicle?useUnicode=yes&characterEncoding=UTF-8", "root",
				"rotrotrot");

		Job job = new Job(conf);
		job.setJarByClass(HadoopApplication.class);
		job.setMapperClass(MapServiceFieldCount.class);
		job.setReducerClass(ReduceServiceFieldCount.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(DBOutputWritableFieldCount.class);
		job.setOutputValueClass(NullWritable.class);
		job.setInputFormatClass(DBInputFormat.class);
		job.setOutputFormatClass(DBOutputFormat.class);

		DBInputFormat.setInput(job, DBInputWritableFieldCount.class, "car", null, null, new String[] { "id", "brand" });

		DBOutputFormat.setOutput(job, "output", new String[] { "brand", "count" });

		int returnValue = job.waitForCompletion(true) ? 0 : 1;

		if (job.isSuccessful()) {
			LOGGER.info(JOB_SUCCESSFUL);
		} else if (!job.isSuccessful()) {
			LOGGER.info(JOB_NOT_SUCCESSFUL);
		}

		return returnValue;
	}
}
