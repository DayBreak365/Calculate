package me.breakofday.calculate;

public class SingletonValue implements ValueProvider {

	private final Operator operator;
	private final double value;

	SingletonValue(Operator operator, double value) {
		this.operator = operator != null ? operator : Operator.PLUS;
		this.value = value;
	}

	SingletonValue(double value) {
		this(Operator.PLUS, value);
	}

	@Override
	public Operator getOperator() {
		return operator;
	}

	@Override
	public double getValue() {
		return value;
	}

}
