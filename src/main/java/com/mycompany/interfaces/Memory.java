package com.mycompany.interfaces;

public interface Memory {

	byte getByte(int address);

	void setByte(int address, byte data);

	int getSize();

	byte[] getAll();
}
