package com.quickkafka.Kafka;


import java.util.Properties;
import org.json.simple.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.logging.Level;
import java.util.*;  
import java.util.logging.Logger;

public class App 
{
	private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());
	//private static final String topicName="demo-topic1";
	static String key="sample key";
	static String value="sample value3";

	/**
	 * @param args
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ArrayList<String> al=new ArrayList<String>();
		al.add("11c1310e-c0c2- 461b-a4eb- f6bf8da2d23c");
		al.add("12c1310e-c0c2- 461b-a4eb- f6bf8da2d23c");
		al.add("13c1310e-c0c2- 461b-a4eb- f6bf8da2d23c");
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> sampleProducer= new KafkaProducer<String,String>(props);
		
		//ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, value);		
	//	sampleProducer.send(record);
		try{
			while (true){
				for (int i = 0; i < 3; i++){
					String message;
					long epoch = System.currentTimeMillis()/1000;
					int temp = (int)(Math.random()*30);
					JSONObject item = new JSONObject();
					JSONObject location = new JSONObject();
					location.put("latitude", "52.14691120000001");
					location.put("longitude","11.658838699999933");
					item.put("temperature", temp);
					item.put("timestamp",epoch);
					item.put("location",location);
					item.put("deviceId", al.get(i));
					JSONObject array = new JSONObject();
					array.put("data", item);
					message = array.toString();
					sampleProducer.send(new ProducerRecord<String, String>("demo-topic1",message));
					sampleProducer.flush();
				}
				Thread.sleep(1000);
			}
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Producer thread was interrupted");
		}
		finally {
			sampleProducer.close();
		}
	} 

}