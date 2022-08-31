package com.devcortes.hadoop.hadoopservice.fieldcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class MapServiceFieldCount extends Mapper<LongWritable, DBInputWritableFieldCount, Text, IntWritable> {

	private static final Logger LOGGER = Logger.getLogger(MapServiceFieldCount.class);

	private IntWritable one = new IntWritable(1);

	protected void map(LongWritable id, DBInputWritableFieldCount value, Context ctx) {

		try {
			String[] keys = value.getBrand().split(" ");
			
			for (String key : keys) {
				ctx.write(new Text(key), one);
			}
		} catch (IOException | InterruptedException e) {
			LOGGER.info(e.getMessage());
			throw new RuntimeException();
		}
	}
}
