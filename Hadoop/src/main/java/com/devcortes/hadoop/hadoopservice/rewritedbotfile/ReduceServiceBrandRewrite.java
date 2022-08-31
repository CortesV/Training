package com.devcortes.hadoop.hadoopservice.rewritedbotfile;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceServiceBrandRewrite extends Reducer<DBInputWritableRewrite, IntWritable, Text, NullWritable> {

	@Override
	protected void reduce(DBInputWritableRewrite value, Iterable<IntWritable> arg1,
			Reducer<DBInputWritableRewrite, IntWritable, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String result = value.toString();
		
		context.write(new Text(result), NullWritable.get());
	}

}
