package com.devcortes.hadoop.hadoopservice.fieldcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

public class ReduceServiceFieldCount extends Reducer<Text, IntWritable, DBOutputWritableFieldCount, NullWritable> {

	private static final Logger LOGGER = Logger.getLogger(ReduceServiceFieldCount.class);

	public void reduce(Text key, Iterable<IntWritable> values, Context ctx) {
		int sum = 0;

		for (IntWritable value : values) {
			sum += value.get();
		}

		try {
			ctx.write(new DBOutputWritableFieldCount(key.toString(), sum), NullWritable.get());
		} catch (IOException | InterruptedException e) {
			LOGGER.info(e.getMessage());
			throw new RuntimeException();
		}
	}
}
