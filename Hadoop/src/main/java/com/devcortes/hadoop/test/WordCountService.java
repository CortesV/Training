package com.devcortes.hadoop.test;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

public class WordCountService extends Configured implements Tool {

	private static final Logger LOGGER = Logger.getLogger(WordCountService.class);
	private static final String JOB_SUCCESSFUL = "Job was successful";
	private static final String JOB_NOT_SUCCESSFUL = "Job was not successful";
	private static final String JOB_NAME = "WordCounter";

	public static void main(String[] args) throws Exception {

		String[] mas = { "input.txt", "output.txt" };
		int exitCode = ToolRunner.run(new WordCountService(), mas);
		System.exit(exitCode);
	}

	public int run(String[] args) throws Exception {

		Job job = new Job();
		job.setJarByClass(WordCountService.class);
		job.setJobName(JOB_NAME);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapperClass(MapService.class);
		job.setReducerClass(ReduceService.class);

		int returnValue = job.waitForCompletion(true) ? 0 : 1;

		if (job.isSuccessful()) {
			LOGGER.info(JOB_SUCCESSFUL);
		} else if (!job.isSuccessful()) {
			LOGGER.info(JOB_NOT_SUCCESSFUL);
		}

		return returnValue;
	}
}
