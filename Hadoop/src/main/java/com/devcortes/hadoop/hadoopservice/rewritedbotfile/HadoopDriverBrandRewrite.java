package com.devcortes.hadoop.hadoopservice.rewritedbotfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.devcortes.hadoop.HadoopApplication;

@Service
public class HadoopDriverBrandRewrite extends Configured implements Tool {

	private static final Logger LOGGER = Logger.getLogger(HadoopDriverBrandRewrite.class);
	private static final String JOB_SUCCESSFUL = "Job was successful";
	private static final String JOB_NOT_SUCCESSFUL = "Job was not successful";

	public Integer runJob() throws Exception {

		String[] mas = { "output" };
		return ToolRunner.run(new HadoopDriverBrandRewrite(), mas);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver",
				"jdbc:mysql://sb-db01.softbistro.online/vechicle?useUnicode=yes&characterEncoding=UTF-8", "root",
				"rotrotrot");

		Job job = new Job(conf);
		job.setJarByClass(HadoopApplication.class);
		job.setMapperClass(MapServiceBrandRewrite.class);
		job.setReducerClass(ReduceServiceBrandRewrite.class);
		job.setMapOutputKeyClass(DBInputWritableRewrite.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(DBOutputWritableRewrite.class);
		job.setOutputValueClass(NullWritable.class);
		job.setInputFormatClass(DBInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		DBInputFormat.setInput(job, DBInputWritableRewrite.class, "car", null, null,
				new String[] { "id", "uuid", "year_produce", "brand", "model", "color", "price" });

		FileOutputFormat.setOutputPath(job, new Path(args[0]));

		int returnValue = job.waitForCompletion(true) ? 0 : 1;

		if (job.isSuccessful()) {
			LOGGER.info(JOB_SUCCESSFUL);
		} else if (!job.isSuccessful()) {
			LOGGER.info(JOB_NOT_SUCCESSFUL);
		}

		return returnValue;
	}
}
