package f01;
import java.util.LinkedList;

public class QueueEx {
	public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        Integer elem;
        for(int i=0; i<10; i++) {
            queue.add(Integer.valueOf(i));
        }
        while(!queue.isEmpty()) {
            elem = queue.remove();
            System.out.print(elem + " ");
        }
	}
}
