package f05;

public interface ProgressListener {
    public void progress(String filename);
    public void ready(String archive);
    public void exception(String message);
}
