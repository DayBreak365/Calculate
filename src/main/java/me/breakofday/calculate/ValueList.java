package me.breakofday.calculate;

import java.util.LinkedList;
import java.util.ListIterator;

public class ValueList extends LinkedList<ValueProvider> implements ValueProvider {

	private final Function function;
	private final Operator operator;

	ValueList(Function function, Operator operator) {
		this.function = function != null ? function : Function.NONE;
		this.operator = operator != null ? operator : Operator.PLUS;
	}

	ValueList(Operator operator) {
		this(Function.NONE, operator);
	}

	ValueList() {
		this(Operator.PLUS);
	}

	@Override
	public Operator getOperator() {
		return operator;
	}

	@Override
	public double getValue() {
		ListIterator<ValueProvider> iterator = listIterator();
		while (iterator.hasNext()) {
			ValueProvider provider = iterator.next();
			Operator operator = provider.getOperator();
			if (operator.equals(Operator.SQUARE)) {
				if (iterator.hasPrevious()) {
					iterator.remove();
					ValueProvider previous = iterator.previous();
					iterator.remove();
					if (previous.getOperator() == Operator.MULTIPLY || previous.getOperator() == Operator.DIVIDE) {
						iterator.add(new SingletonValue(previous.getOperator(), Math.pow(previous.getValue(), provider.getValue())));
					} else {
						iterator.add(new SingletonValue(Math.pow(previous.getOperator().parseValue(previous.getValue()), provider.getValue())));
					}
				} else {
					throw new ArithmeticException("Invalid Expression");
				}
			}
		}

		iterator = listIterator();
		while (iterator.hasNext()) {
			ValueProvider provider = iterator.next();
			Operator operator = provider.getOperator();
			if (operator.equals(Operator.MULTIPLY)) {
				if (iterator.hasPrevious()) {
					iterator.remove();
					ValueProvider previous = iterator.previous();
					iterator.remove();
					iterator.add(new SingletonValue(previous.getOperator().parseValue(previous.getValue()) * provider.getValue()));
				} else {
					throw new ArithmeticException("Invalid Expression");
				}
			} else if (operator.equals(Operator.DIVIDE)) {
				if (iterator.hasPrevious()) {
					iterator.remove();
					ValueProvider previous = iterator.previous();
					iterator.remove();
					iterator.add(new SingletonValue(MathUtil.divide(previous.getOperator().parseValue(previous.getValue()), provider.getValue())));
				} else {
					throw new ArithmeticException("Invalid Expression");
				}
			}
		}
		double value = 0.0;
		for (ValueProvider provider : this) {
			value += provider.getOperator().parseValue(provider.getValue());
		}
		return function.calculate(value);
	}

}
