package l05.l5product;

import java.util.LinkedList;
import java.util.Random;

public class ProductGenerator {
	private Multiplication thread;
	private int maxProduct;
	private LinkedList<ProductListener> list = new LinkedList<ProductListener>();
	
	public ProductGenerator(int maxProduct) {
		this.maxProduct = Math.abs(maxProduct);
	}
	
	public void addListener(ProductListener pl) {
		if(pl != null) {
			list.add(pl);
		}
	}
	
	public void removeListener(ProductListener pl) {
		list.remove(pl);
	}
	
	public void start(int product) {
		if(thread==null) {
			if(product<0)
				product = (-product) % maxProduct;
			thread = new Multiplication(product);
			thread.start();
		}
	}
	
	public void stop() {
		if(thread!=null) {
			thread.interrupt();
		}
	}

	private class Multiplication extends Thread {
		private int product;
		
		public Multiplication(int product) {
			this.product = product;
		}
		
		public void run() {
			int factor1, factor2, res;
			Random rand = new Random();
			while(!Thread.interrupted()) {
				factor1 = rand.nextInt(product)+1;
				factor2 = rand.nextInt(product)+1;
				res = factor1*factor2;
				if(res==product ) {
					for(ProductListener pl : list) {
						pl.product(factor1 + " * " + factor2 + " = " + product);
					}
				}
			}
			thread=null;
		}
	}
}
