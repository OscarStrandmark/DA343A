package f05;

public class ProgressListenerConsole implements ProgressListener {

	@Override
	public void progress(String filename) {
		System.out.println(filename);
	}

	@Override
	public void ready(String archive) {
		System.out.println("Nytt arkiv: " + archive);
	}

	@Override
	public void exception(String message) {
		System.out.println("Exception: " + message);
	}
	
}
