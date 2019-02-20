package f02;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ArrayListEx {
	// Enda gemensamma superclass är Object, oftast en dålig ide
	public void mess() {
        Dimension d;
        List list = new ArrayList();
        list.add(new Dimension(100,150)); // add(Object) lägger till sist
        list.add(0,new Bike());           // add(0,Object) skjuter in i position 0
        list.add(new Dimension(400,300)); // lägger till sist
        list.add(1,new House());          // skjuter in i position 1
        list.add(list.size(),new Bike()); // lägger till sist
        
        System.out.println("----------mess----------");
        System.out.println("Innehåll i listan:");
        for(int i=0; i<list.size(); i++) {               // size ger antalet objekt som lagras i listan
        	System.out.println(list.get(i).getClass());  // get(index) returnerar referens till objekt i angiven position
        }

        System.out.println("\nBorttagen: " + list.remove(1)); // tar bort element 2, returnerar referens till bortaget objekt
        System.out.println("Ändrat element: " + list.set(1, new Point()));
        
        System.out.println("\nInnehåll i listan:");
        for(int i=0; i<list.size(); i++) {
        	System.out.println(list.get(i).getClass());  // get(index) returnerar referens till objekt i angiven position
        }
        
        System.out.println("\nDimension-objekt:");
        for(Object o : list) {            // man kan använda förenklad for-loop
        	if(o instanceof Dimension) {
        		d = (Dimension)o;         // typkonvertering krävs vid otypad lista
        		System.out.println(d.getWidth());
        	}
        }
	}
	
	public void noGenerics() {
        Dimension d;
        List list = new ArrayList();
        list.add(new Dimension(100,150));
//        list.add(new Bike());             // går, men ej lämpligt, programkrasch i andra loopen
        list.add(new Dimension(400,300)); 
//        list.add(1,new House());          // går, men ej lämpligt, programkrasch i andra loopen
//        list.add(list.size(),new Bike()); // går, men ej lämpligt, programkrasch i andra loopen
        
        System.out.println("\n-------noGenerics, toString()-------");
        for(int i=0; i<list.size(); i++) {
        	System.out.println(list.get(i).toString()); // toString() finns i Object
        }
        
        System.out.println("\n-------noGenerics, getWidth()-------");
        for(int i=0; i<list.size(); i++) {
        	d = (Dimension)list.get(i);                  // behövs typkonvertering
        	System.out.println("width=" + d.getWidth()); // getWidth() finns ej i Object
        }
	}
	
	public void generics() {
        Dimension d;
        List<Dimension> list = new ArrayList<Dimension>();
        list.add(new Dimension(100,150));
//        list.add(new Bike());             // går ej
        list.add(new Dimension(400,300)); 
//        list.add(1,new House());          // går ej
//        list.add(list.size(),new Bike()); // går ej
        
        System.out.println("\n--------generics, toString() --------");        
        for(int i=0; i<list.size(); i++) {
        	d = list.get(i);                // behövs ej typkonvertering
        	System.out.println(d.toString());
        }
        
        System.out.println("\n--------generics, getWidth() --------");        
        for(int i=0; i<list.size(); i++) {
        	System.out.println(list.get(i).getWidth()); // behövs ej typkonvertering
        }
	}
	
    public static void main(String[] args) {
    	ArrayListEx prog = new ArrayListEx();
    	prog.mess();
//    	prog.noGenerics();
//    	prog.generics();
    }
}

class Bike {}

class House {}