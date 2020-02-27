package me.breakofday.calculate;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Calculate {

	public static void main(String[] args) {
		new CalculateGUI();
	}

	public static double calculate(String expression) {
		return tokenize(new StringCharacterIterator(expression)).getValue();
	}

	private static ValueList tokenize(CharacterIterator expression) {
		ValueList values = new ValueList();
		StringBuilder sb = new StringBuilder();
		Operator lastOperator = null;
		for (char c = expression.current(); c != CharacterIterator.DONE; c = expression.next()) {
			if (c == '(') {
				String functionName = sb.toString();
				sb.setLength(0);
				Function function = functionName.isEmpty() ? Function.NONE : Function.getFunction(functionName);
				if (function != null) {
					values.add(tokenizeBracket(function, lastOperator, expression));
				} else {
					throw new ArithmeticException("Function '" + functionName + "' does not exist");
				}
			} else {
				Operator operator = Operator.getOperator(c);
				if (operator != null) {
					if (sb.length() > 0) {
						if (lastOperator != null) {
							values.add(new SingletonValue(lastOperator, Double.parseDouble(sb.toString())));
							sb.setLength(0);
						} else {
							values.add(new SingletonValue(Double.parseDouble(sb.toString())));
							sb.setLength(0);
						}
					}
					lastOperator = operator;
				} else {
					sb.append(c);
				}
			}
		}
		if (sb.length() > 0) {
			if (lastOperator != null) {
				values.add(new SingletonValue(lastOperator, Double.parseDouble(sb.toString())));
				sb.setLength(0);
			} else {
				values.add(new SingletonValue(Double.parseDouble(sb.toString())));
				sb.setLength(0);
			}
		}
		return values;
	}

	private static ValueList tokenizeBracket(Function bracketFunction, Operator bracketToken, CharacterIterator expression) {
		ValueList values = new ValueList(bracketFunction, bracketToken);
		StringBuilder sb = new StringBuilder();
		Operator lastOperator = null;
		for (char c = expression.next(); c != CharacterIterator.DONE; c = expression.next()) {
			if (c == '(') {
				String functionName = sb.toString();
				sb.setLength(0);
				Function function = functionName.isEmpty() ? Function.NONE : Function.getFunction(functionName);
				if (function != null) {
					values.add(tokenizeBracket(function, lastOperator, expression));
				} else {
					throw new ArithmeticException("Function '" + functionName + "' does not exist");
				}
			} else if (c == ')') {
				if (sb.length() > 0) {
					if (lastOperator != null) {
						values.add(new SingletonValue(lastOperator, Double.parseDouble(sb.toString())));
						sb.setLength(0);
					} else {
						values.add(new SingletonValue(Double.parseDouble(sb.toString())));
						sb.setLength(0);
					}
				}
				return values;
			} else {
				Operator operator = Operator.getOperator(c);
				if (operator != null) {
					if (sb.length() > 0) {
						if (lastOperator != null) {
							values.add(new SingletonValue(lastOperator, Double.parseDouble(sb.toString())));
							sb.setLength(0);
						} else {
							values.add(new SingletonValue(Double.parseDouble(sb.toString())));
							sb.setLength(0);
						}
					}
					lastOperator = operator;
				} else {
					sb.append(c);
				}
			}
		}
		throw new ArithmeticException("Bracket not closed");
	}

}
