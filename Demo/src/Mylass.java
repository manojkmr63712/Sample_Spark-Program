import java.util.*;
public class Mylass {

	public static void main(String[] args) {
		HashMap<String,List<Integer>> obj = new HashMap<String,List<Integer>>();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Number of Cards :");
		int num = input.nextInt();
		String data1;
		Integer n1;
		for(int i=1;i<=num;i++){
			System.out.println("Enter the Card :");
			data1 = input.next();
			String data2 = input.next();
			n1 = Integer.parseInt(data2);
			input.nextLine();
			if(!obj.containsKey(data1)){
				List<Integer> data3 = new ArrayList<Integer>();
				data3.add(n1);
				obj.put(data1,data3);
			}
			else{
				List<Integer> data4 = new ArrayList<Integer>(obj.get(data1));
				data4.add(n1);
				obj.put(data1,data4);
			}
		}
		List<String> str = new ArrayList<String>();
		for(Map.Entry m:obj.entrySet()){
			str.add((String) m.getKey());
			System.out.println(m.getKey());
		}
		//for(int i=0;i<=str.size()-1;i++){
			for(Map.Entry n:obj.entrySet()){
				String strkey = (String) n.getKey();
				List<Integer> strvalu = new ArrayList<Integer>();
				strvalu.add((Integer) n.getValue());
				for(int i=0;i<=strvalu.size()-1;i++){
					Integer strvalue = strvalu[i];
				}
			}	
		//}
		 
	}

}
