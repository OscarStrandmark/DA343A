package f06;

public class DemoRunOnThread {
	public static void main(String[] args) {
		RunOnThread rot = new RunOnThread();
		rot.start();
		rot.execute(new ToDo3(1));
		rot.execute(new ToDo10(2));
		rot.execute(new ToDo3(3));
		rot.execute(new ToDo3(4));
		rot.execute(new ToDo3(5));
		rot.execute(new ToDo3(6));
		rot.execute(new ToDo3(7));
		rot.execute(new ToDo10(8));
		rot.execute(new ToDo3(9));
		rot.execute(new ToDo3(10));
		rot.execute(new Exit(rot));
	}
}

class ToDo3 implements Runnable {
	private int i;
	
	public ToDo3(int i) {
		this.i = i;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName() + " startar ToDo3:" + i);
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {}
		System.out.println(Thread.currentThread().getName() + " avslutar ToDo3:" + i);
	}
}

class ToDo10 implements Runnable {
	private int i;
	
	public ToDo10(int i) {
		this.i = i;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName() + " startar ToDo10:" + i);
		try {
			Thread.sleep(000);
		} catch(InterruptedException e) {}
		System.out.println(Thread.currentThread().getName() + " avslutar ToDo10:" + i);
	}
}

class Exit implements Runnable {
	private RunOnThread rot;
	
	public Exit(RunOnThread rot) {
		this.rot = rot;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Exit");
		rot.stop();
	}
}

