package com.devcortes.hadoop.hadoopservice.filterbrand;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceServiceBrand extends Reducer<DBInputWritableBrand, IntWritable, DBOutputWritableBrand, NullWritable> {

	@Override
	protected void reduce(DBInputWritableBrand value, Iterable<IntWritable> arg1,
			Reducer<DBInputWritableBrand, IntWritable, DBOutputWritableBrand, NullWritable>.Context context)
			throws IOException, InterruptedException {
		DBOutputWritableBrand result = new DBOutputWritableBrand(value.getId(), value.getUuid(), value.getYearProduce(),
				value.getBrand(), value.getModel(), value.getColor(), value.getPrice());
		context.write(result, NullWritable.get());
	}

}
