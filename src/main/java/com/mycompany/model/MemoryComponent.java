package com.mycompany.model;

import org.springframework.stereotype.Component;
import com.mycompany.interfaces.Memory;

@Component
public class MemoryComponent implements Memory {

	private int address;
	private byte[] memory = { 
			1, // add
			28, 0, 
			1, // add
			
			// constans input
			27, 1, 
			11, // read

			// read
			2, 
			3, // copy number to rest - cycle start
			2, 3,
			13, 

			// cycle end, check
			3, 0, 24, 
			9, 

			// calculate rest
			3, 0, 
			5, // sum

			// increase variable
			0, 1, 
			10, //check rest

			// check rest !=0
			3, 8, 
			12, // display
			
			//dispay index, stop, constans, variable
			0, 0, 1, 2
	};

	public byte getByte(int address) {
		return memory[address];
	}

	public void setByte(int address, byte data) {
		this.memory[address] = data;
	}

	public int getSize() {
		return this.address;
	}

	public byte[] getAll() {
		return memory;
	}

}
