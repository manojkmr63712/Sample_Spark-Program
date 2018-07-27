import org.apache.hadoop.mapred.MapReduceBase;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
public class D_Mapper extends MapReduceBase 
			implements Mapper<LongWritable,Text,Text,Text>{
	@Override
	public void map(LongWritable key, Text value, 
			OutputCollector<Text, Text> output, Reporter r)
			throws IOException 
	{
		String s1 =value.toString();
		String s = "";
		String newName1 = null;
		int position;
		int str;
		if(s1.contains("NumberLong"))
		{
			int index1=s1.indexOf("NumberLong");//returns the index of is substring
			int index2=s1.indexOf("(",index1);
			str=index2+1;
			char cur = s1.charAt(str);
			int index3=s1.indexOf(")",index1);
			position=index3+1;
			newName1 = s1.substring(0,index1)+cur+s1.substring(position);
			output.collect(new Text(s), new Text(newName1));
			}
		else
		{
			output.collect(new Text(s), new Text(s1));
		}
	}
}