package me.breakofday.calculate;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculateGUI extends JFrame {

	private final JTextArea outputArea;

	CalculateGUI() {
		super("Calculate - made by daybreak");
		setSize(960, 540);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.outputArea = new JTextArea(24, 80);
		outputArea.setBackground(Color.BLACK);
		outputArea.setForeground(Color.LIGHT_GRAY);
		outputArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		outputArea.setEditable(false);
		add(outputArea);

		add(new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		JTextField inputField = new JTextField(10);
		inputField.setBackground(Color.BLACK);
		inputField.setForeground(Color.WHITE);
		inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		inputField.addKeyListener(new KeyAdapter() {
			private String last = "";
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						last = inputField.getText();
						String line = last.replace(" ", "").toLowerCase();
						inputField.setText("");
						writeLine(line);
						switch (line) {
							case "clear":
								outputArea.setText("");
								break;
							case "example":
								writeLine();
								writeLine("--------- Simple examples to try ---------");
								writeLine("1 + 5");
								writeLine("5 * (1 / (8 + 2))");
								writeLine("(5 / 10) * (10 / 5)");
								writeLine("(1 + 5) ^ (1 + 1)");
								writeLine("---------------------------------------------------");
								break;
							default:
								try {
									writeLine(String.valueOf(Calculate.calculate(line)));
								} catch (Exception ex) {
									if (ex instanceof NumberFormatException) {
										ex.printStackTrace();
										writeLine("'" + line + "' is invalid expression");
									} else if (ex instanceof ArithmeticException) {
										writeLine(ex.getLocalizedMessage());
									} else {
										writeLine(ex.getClass().getName() + ": " + ex.getMessage());
									}
								}
								break;
						}
						break;
					case KeyEvent.VK_UP:
						inputField.setText(last);
						break;
				}
			}

		});
		add(inputField, BorderLayout.SOUTH);

		pack();
		setVisible(true);
		setLocation(getX() + getWidth() + getInsets().right, getY());
	}

	public void writeLine(String line) {
		outputArea.append(line);
		outputArea.append(System.lineSeparator());
	}

	public void writeLine() {
		outputArea.append(System.lineSeparator());
	}

}
