package com.mycompany.interfaces;

public interface Processor {

	void run();

	void runFrom(int address);

	byte[] getRegisters();

	int getNumOfRegisters();
}
