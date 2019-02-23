package l09;

public class Calculator {
	
	public double calculator(double nbr1, double nbr2, char op) {
		double res = Double.NaN;
		switch (op) {
		case '+':
			res = nbr1 + nbr2;
			break;
		case '-':
			res = nbr1 - nbr2;
			break;
		case '*':
			res = nbr1 * nbr2;
			break;
		case '/':
			res = nbr1 / nbr2;
			break;
		}
		return res;
	}
}
