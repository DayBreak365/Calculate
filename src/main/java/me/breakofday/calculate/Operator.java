package me.breakofday.calculate;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {

	private static final Map<Character, Operator> operators = new HashMap<>();

	public static Operator getOperator(char character) {
		return operators.get(character);
	}

	public static final Operator PLUS = new Operator('+') {
		@Override
		public double parseValue(double value) {
			return value;
		}
	};
	public static final Operator MINUS = new Operator('-') {
		@Override
		public double parseValue(double value) {
			return -value;
		}
	};
	public static final Operator MULTIPLY = new Operator('*') {
		@Override
		public double parseValue(double value) {
			throw new UnsupportedOperationException();
		}
	};
	public static final Operator DIVIDE = new Operator('/') {
		@Override
		public double parseValue(double value) {
			throw new UnsupportedOperationException();
		}
	};
	public static final Operator SQUARE = new Operator('^') {
		@Override
		public double parseValue(double value) {
			throw new UnsupportedOperationException();
		}
	};

	private final char character;

	private Operator(char character) {
		this.character = character;
		operators.put(character, this);
	}

	public abstract double parseValue(double value);

	public char getCharacter() {
		return character;
	}

}
