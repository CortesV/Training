package com.devcortes.hadoop.hadoopservice.filterbrand;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class MapServiceBrand extends Mapper<LongWritable, DBInputWritableBrand, DBInputWritableBrand, IntWritable> {

	@Override
	protected void map(LongWritable key, DBInputWritableBrand value,
			Mapper<LongWritable, DBInputWritableBrand, DBInputWritableBrand, IntWritable>.Context context)
			throws IOException, InterruptedException {

		if ("aaaa".equals(value.getBrand())) {
			context.write(value, new IntWritable(1));
		}
	}

}
