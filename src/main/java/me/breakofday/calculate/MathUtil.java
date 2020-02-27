package me.breakofday.calculate;

public class MathUtil {

	private MathUtil() {}

	public static double divide(double a, double b) {
		if (b == 0.0) throw new ArithmeticException("Divided by zero");
		return a / b;
	}

}
