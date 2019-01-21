package l01;

public class test_Dstring {
	public static void main(String[] args) {
		DString dstr = new DString("0123456789");
		DString str2 = new DString("0123456789");
		
		System.out.println( str2.substring(0) );
		str2.delete(9);
		System.out.println( str2.substring(0) );
		
//		dstr.append('A');
//		
//		//test av indexOf()
//		System.out.println(dstr.indexOf('A'));
//		
//		//test av substring(start)
//		System.out.println(dstr.substring(0));
//		
//		dstr.delete(10);
//		
//		//test av delete()
//		System.out.println(dstr.substring(0));
//
//		//test av substring(start,end)
//		System.out.println(dstr.substring(2, 7));
//		
//		dstr.append('x').append('y').append('z');
//		
//		System.out.println(dstr.substring(0));
//		
//		System.out.println(dstr.compareTo(str2));
	}
}
