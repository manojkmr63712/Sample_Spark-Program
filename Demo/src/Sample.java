public class Sample{
	public static void main(String args[]){
		String s1="hii (how) are you(u) (you) you(t) (good)";
		//passing substring
		int position = 0;
		String newName1 = null;
		int str;
		if(s1.contains("you"))
		{
		int index1=s1.indexOf("you");//returns the index of is substring
		int index2=s1.indexOf("(",index1);
		str=index2+1;
		char cur = s1.charAt(str);
		int index3=s1.indexOf(")",index1);
		position=index3+1;
		int index5=s1.indexOf("you",index3);
		
		newName1 = s1.substring(0,index1)+cur+s1.substring(position);
		System.out.println(newName1);
		System.out.println(index5);
		System.out.println(index1);
		}
		else
		{
			System.out.println(s1);
		}
		
	}
}