package l01;

public class test_Dstring {
	public static void main(String[] args) {
		DString dstr = new DString("0123456789");
		
		dstr.append('A');
		
		//test av indexOf()
		System.out.println(dstr.indexOf('A'));
		
		//test av substring(start)
		System.out.println(dstr.substring(0));
		
		dstr.delete(10);
		
		//test av delete()
		System.out.println(dstr.substring(0));

		//test av substring(start,end)
		System.out.println(dstr.substring(2, 7));
		
	}
}
