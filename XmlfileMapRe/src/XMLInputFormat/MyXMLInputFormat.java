package XMLInputFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;


public class MyXMLInputFormat extends TextInputFormat
{
	public static final String START_TAG_KEY ="<Book>";
	public static final String END_TAG_KEY ="</Book>";
	
	public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context){
		return new MyXMLRecordReader();
	}
}
