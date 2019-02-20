package f02;

public class UseLinkedStack {
	public static void main(String[] args) {
		LinkedStack<Double> stack = new LinkedStack<Double>();
		for(double d = 10; d<40; d++) {
			stack.push(d);
		}
		System.out.println("size="+stack.size()+", first element="+stack.peek());
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}
}
