package f06;


public class TestSender {
    public static void main(String[] args) {
		Viewer viewer = new Viewer();
        String mes1 = "JAG HÄVDAR ATT JORDEN ÄR RUND";
        String mes2 = "153 + 23 - 100 = 76";
        String mes3 = "en int-array kan innehålla många heltal";
        Sender m1 = new Sender(mes1.split(" "), viewer);
        Sender m2 = new Sender(mes2.split(" "), viewer);
        Sender m3 = new Sender(mes3.split(" "), viewer);
        m1.start();
        m2.start();
//        m3.start();
    }
}
