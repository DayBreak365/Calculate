package me.breakofday.calculate;

import com.google.common.math.BigIntegerMath;

public class MathUtil {

	private MathUtil() {}

	public static double divide(double a, double b) {
		if (b == 0.0) throw new ArithmeticException("Divided by zero");
		return a / b;
	}

	public static double factorial(double a) {
		if (a == Math.floor(a)) {
			if (!Double.isInfinite(a)) return BigIntegerMath.factorial((int) a).doubleValue();
			else throw new ArithmeticException("Factorial of infinite value");
		} else throw new ArithmeticException("Factorial of non natural number");
	}

}
