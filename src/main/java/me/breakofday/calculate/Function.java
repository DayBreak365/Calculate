package me.breakofday.calculate;

import java.util.HashMap;
import java.util.Map;

public abstract class Function {

	private static final Map<String, Function> functions = new HashMap<>();

	public static Function getFunction(String name) {
		return functions.get(name);
	}

	public static final Function NONE = new Function() {
		@Override
		public double calculate(double value) {
			return value;
		}
	};

	public static final Function SIN = new Function("sin") {
		@Override
		public double calculate(double value) {
			return Math.sin(value);
		}
	};
	public static final Function COS = new Function("cos") {
		@Override
		public double calculate(double value) {
			return Math.cos(value);
		}
	};
	public static final Function TAN = new Function("tan") {
		@Override
		public double calculate(double value) {
			return Math.tan(value);
		}
	};
	public static final Function RADIANS = new Function("radians", "rad") {
		@Override
		public double calculate(double value) {
			return Math.toRadians(value);
		}
	};
	public static final Function FACTORIAL = new Function("!", "factorial", "fac") {
		@Override
		public double calculate(double value) {
			return MathUtil.factorial(value);
		}
	};
	public static final Function ROUND = new Function("round") {
		@Override
		public double calculate(double value) {
			return Math.round(value);
		}
	};
	public static final Function FLOOR = new Function("floor") {
		@Override
		public double calculate(double value) {
			return Math.floor(value);
		}
	};
	public static final Function CEIL = new Function("ceil") {
		@Override
		public double calculate(double value) {
			return Math.ceil(value);
		}
	};
	public static final Function ABS = new Function("abs") {
		@Override
		public double calculate(double value) {
			return Math.abs(value);
		}
	};

	public abstract double calculate(double value);

	private Function(String... names) {
		for (String name : names) {
			functions.put(name, this);
		}
	}

}
