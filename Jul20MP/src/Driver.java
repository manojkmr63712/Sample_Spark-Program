import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class Driver extends Configured implements Tool
{
	@Override
	public int run(String[] args) throws Exception 
	{
		if (args.length<2)
		{
			System.out.println("Please give input and out put dre");
			return -1;
		}
		JobConf conf = new JobConf(Driver.class);
		FileInputFormat.setInputPaths(conf,new Path(args[1]));
		FileOutputFormat.setOutputPath(conf,new Path(args[2]));
		conf.setMapperClass(D_Mapper.class);
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(Text.class);
		JobClient.runJob(conf);
		return 0;
	}
	public static void main(String args[]) throws Exception 
	{
		int exitCode = ToolRunner.run(new Driver(),args);
		System.exit(exitCode);
	}
}
