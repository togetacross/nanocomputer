package com.mycompany.model;

import javax.swing.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.mycompany.interfaces.IO;

@Component
@Primary
public class IOSwingComponent implements IO {

	private byte data;

	public byte get() {
		Integer number = 0;
		while (number < 2 || number > 255) {
			String input = JOptionPane.showInputDialog("Type a number from 2 to 255");
			try {
				number = Integer.parseInt(input.trim());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Try a valid number...", "Error", JOptionPane.ERROR_MESSAGE);
			}				
		}
		this.data = number.byteValue();
		return this.data;
	}

	public void put(byte result) {
		JOptionPane.showMessageDialog(null, isPrime(result) ? this.data + " Is Prime!" : this.data + " Is Not Prime!", "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	public void error(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private boolean isPrime(byte result) {
		return this.data == result;
	}

}
