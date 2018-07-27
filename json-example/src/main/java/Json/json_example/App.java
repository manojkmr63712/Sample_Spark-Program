package Json.json_example;


/**
 * Hello world!
 *
 */
import org.json.simple.JSONObject;


class App {

   @SuppressWarnings("unchecked")
public static void main(String[] args){
	   String message;
	   long epoch = System.currentTimeMillis()/1000;
	   JSONObject item = new JSONObject();
	   JSONObject location = new JSONObject();
	   location.put("latitude", "52.14691120000001");
	   location.put("longitude","11.658838699999933");
	   item.put("temperature", 3);
	   item.put("timestamp",epoch);
	   item.put("location",location);
	   item.put("deviceId", "11c1310e-c0c2- 461b-a4eb- f6bf8da2d23c");
	   JSONObject array = new JSONObject();
	   array.put("data", item);
	   message = array.toString();
	   System.out.println(message);
   }
}
