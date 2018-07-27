package XMLInputFormat;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class MyXMLRecordReader extends RecordReader<LongWritable, Text> 
{
	private byte[] startTag;
	private byte[] endTag;
	private long start;
	private long end;
	private FSDataInputStream fsin;
	private DataOutputBuffer buffer = new DataOutputBuffer();
	private LongWritable key = new LongWritable();
	private Text value = new Text();

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		fsin.close();
		
	}

	@Override
	public LongWritable getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return (fsin.getPos()-start) / (float)(end-start);
	}

	@Override
	public void initialize(InputSplit arg0, TaskAttemptContext arg1) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		FileSplit fileSplit = (FileSplit) arg0;
		String START_TAG_KEY = "<Book>";
		String END_TAG_KEY = "</Book>";
		startTag = START_TAG_KEY.getBytes("utf-8");
		endTag = END_TAG_KEY.getBytes("utf-8");
		
		
		start = fileSplit.getStart();
		end = start + fileSplit.getLength();
		Path file = fileSplit.getPath();
		
		FileSystem fs = file.getFileSystem(arg1.getConfiguration());
		fsin = fs.open(fileSplit.getPath());
		fsin.seek(start);
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		if (fsin.getPos() < end){
			if (readUntilMatch(startTag, false)){
				try {
					buffer.write(startTag);
					if(readUntilMatch(endTag, true)){
						value.set(buffer.getData(),0,buffer.getLength());
						key.set(fsin.getPos());
						return true;
					}
				} finally {
					buffer.reset();
				}
			}
		}
		return false;
	}

	private boolean readUntilMatch(byte[] match, boolean withinBlock) throws IOException {
		// TODO Auto-generated method stub
		int i = 0;
		while(true){
			int b = fsin.read();
			if (b == -1)
				return false;
			if (withinBlock)
				buffer.write(b);
			if (b == match[i]){
				i++;
				if(i >= match.length)
					return true;
			}else
				i = 0;
			if (!withinBlock && i == 0 && fsin.getPos() >= end)
				return false;
		}
		
	}
	
}
