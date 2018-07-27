package com.mapreduce.join;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Mapreducejoin {
	
	public static class DrivMapper extends Mapper<Object,Text,Text,Text> {
		public void map(Object key,Text value, Context context) throws IOException, InterruptedException {
			try {
				
	            if (value.toString().contains("driverId,name,ssn,location,certified,wage-paln") /*Some condition satisfying it is header*/)
	                return;
			
	            else {
			String[] record = value.toString().split(",");
			context.write(new Text(record[0]), new Text("driver\t" + record[1]));
	            }
		}catch (Exception e) {
            e.printStackTrace();
        }
	}
	}
	public static class TimeMapper extends Mapper<Object,Text,Text,Text> {
		public void map(Object key,Text value, Context context) throws IOException, InterruptedException {
			try {
				
	            if (value.toString().contains("driverId,week,hours-logged,miles-logged") /*Some condition satisfying it is header*/)
	                return;
			
	            else {
			String[] record = value.toString().split(",");
			context.write(new Text(record[0]), new Text("time\t" + record[3]));
	            	}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	public static class Reducejoin extends Reducer<Text,Text,Text,Text> {
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			String name = "";
			double total = 0.0;
			for(Text t : values)
			{
				String part[] = t.toString().split("\t");
				if(part[0].equals("time")){
					total += Float.parseFloat(part[1]);
				}else if(part[0].equals("driver")){
					name = part[1];
				}
				String str = String.format("%d\t%f", total);
				context.write(new Text(name), new Text(str));
			}
			
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws Exception
	{
		Configuration conf = new Configuration();
		Job job1 = new Job(conf,"Reduce-side join");
		job1.setJarByClass(Mapreducejoin.class);
		job1.setReducerClass(Reducejoin.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		MultipleInputs.addInputPath(job1, new Path(args[1]), TextInputFormat.class,DrivMapper.class);
		MultipleInputs.addInputPath(job1, new Path(args[2]), TextInputFormat.class,TimeMapper.class);
		Path outputPath = new Path(args[3]);
		FileOutputFormat.setOutputPath(job1,outputPath);
		System.exit(job1.waitForCompletion(true)? 0 : 1);
		
	}
	
}












