package l09;

public class StartCalcServer {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
//		CalcServerA serverA = new CalcServerA(calculator, 721);
		CalcServerB serverB = new CalcServerB(calculator, 722);
	}
}
