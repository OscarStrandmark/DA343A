package l08;

import java.io.IOException;

public interface CalcClient {

	public void newCalculation(String expression) throws IOException;

	public void setCalcController(CalcController calcController);
}
