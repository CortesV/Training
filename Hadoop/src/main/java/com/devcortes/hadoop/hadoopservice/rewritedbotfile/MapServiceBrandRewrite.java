package com.devcortes.hadoop.hadoopservice.rewritedbotfile;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class MapServiceBrandRewrite
		extends Mapper<LongWritable, DBInputWritableRewrite, DBInputWritableRewrite, IntWritable> {

	@Override
	protected void map(LongWritable key, DBInputWritableRewrite value,
			Mapper<LongWritable, DBInputWritableRewrite, DBInputWritableRewrite, IntWritable>.Context context)
			throws IOException, InterruptedException {

		context.write(value, new IntWritable(1));
	}

}
