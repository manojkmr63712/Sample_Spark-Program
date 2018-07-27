package XMLInputFormat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	public static final Log LOG = LogFactory.getLog(MyMapper.class);
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {
			InputStream is = new ByteArrayInputStream(value.toString().getBytes());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Book");
			for (int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					String Bookid = eElement.getElementsByTagName("Bookid").item(0).getTextContent();
					String BookName = eElement.getElementsByTagName("BookName").item(0).getTextContent();
					String Category = eElement.getElementsByTagName("Category").item(0).getTextContent();
					String Author = eElement.getElementsByTagName("Author").item(0).getTextContent();
					String Location = eElement.getElementsByTagName("Location").item(0).getTextContent();
					String Review = eElement.getElementsByTagName("Review").item(0).getTextContent();
					
					context.write(new Text(Bookid+" * "+BookName+" * "+Category+" * "+Author+" * "+Location+" * "+Review), NullWritable.get());;
				}
			}
		}
		catch (Exception e){
			
		}
		
	}

}
