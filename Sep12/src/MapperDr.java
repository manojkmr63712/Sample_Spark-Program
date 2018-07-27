import org.apache.hadoop.mapred.MapReduceBase;
import java.io.IOException;
import java.util.StringJoiner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
public class MapperDr extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable>{
	public void map(LongWritable key, Text value, 
			OutputCollector<Text, IntWritable> output, Reporter r)
			throws IOException 
	{
		try {
			
            if (key.get() == 0 && value.toString().contains("Transaction_date,Product,Price,Payment_Type,Name,City,State,Country,Account_Created,Last_Login,Latitude,Longitude") /*Some condition satisfying it is header*/)
                return;
		
            else {
                // For rest of data it goes here
            	String[] line = value.toString().split(",");
            	String Product = line[1];
         		String amount = line[2];
         		String state = line[6];
         		int avgamount = Integer.parseInt(amount);
         		StringJoiner joiner = new StringJoiner(" - ");
         		joiner.add(Product).add(state);
         		String joinedString = joiner.toString();
         		output.collect(new Text(joinedString), new IntWritable(avgamount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
 		
		}
	}
