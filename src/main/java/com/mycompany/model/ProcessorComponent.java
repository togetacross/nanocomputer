package com.mycompany.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.interfaces.IO;
import com.mycompany.interfaces.Memory;
import com.mycompany.interfaces.Processor;
import java.lang.Integer;

@Component
public class ProcessorComponent implements Processor {

	private byte[] register = new byte[4];
	
	@Autowired
	private Memory memory;
	
	@Autowired
	private IO display;

	private void proccessRun(int address) {	
		int pc = address;
		byte cmd = memory.getByte(pc);
		Integer regValue = 0;
		
		while (cmd != 0) {
		  switch (cmd) {
			case 1: // LOAD FROM MEM TO REG
				byte addr = memory.getByte(pc + 1);
				byte regindex = memory.getByte(pc + 2);
				register[regindex] = memory.getByte(addr);
				pc += 3;
				break;
			case 2: // STORE FROM REG TO MEM
				regindex = memory.getByte(pc + 1);
				addr = memory.getByte(pc + 2);
				memory.setByte(addr, register[regindex]);
				pc += 3;
				break;
			case 3: // STORE FROM REG TO REG
				byte reg1 = memory.getByte(pc + 1);
				byte reg2 = memory.getByte(pc + 2);
				register[reg2] = register[reg1];
				pc += 3;
				break;
			case 4: // Jump Address
				pc = memory.getByte(pc + 1);
				break;
			case 5: // REG1 += REG2
				reg1 = memory.getByte(pc + 1);
				reg2 = memory.getByte(pc + 2);
				regValue = (register[reg1] & 0xFF) + (register[reg2] & 0xFF);
				register[reg1] = regValue.byteValue();
				pc += 3;
				break;
			case 6: // REG1 *= REG2
				reg1 = memory.getByte(pc + 1);
				reg2 = memory.getByte(pc + 2);
				regValue = (register[reg1] & 0xFF) * (register[reg2] & 0xFF);
				register[reg1] = regValue.byteValue();
				pc += 3;
				break;
			case 7: // REG1 -= REG2
				reg1 = memory.getByte(pc + 1);
				reg2 = memory.getByte(pc + 2);
				regValue = (register[reg1] & 0xFF) - (register[reg2] & 0xFF);
				register[reg1] = regValue.byteValue();
				pc += 3;
				break;
			case 8: // REG1 /= REG2
				reg1 = memory.getByte(pc + 1);
				reg2 = memory.getByte(pc + 2);
				regValue = (register[reg1] & 0xFF) / (register[reg2] & 0xFF);
				register[reg1] = regValue.byteValue();
				pc += 3;
				break;
			case 9: // REG1 %= REG2
				reg1 = memory.getByte(pc + 1);
				reg2 = memory.getByte(pc + 2);
				regValue = (register[reg1] & 0xFF) % (register[reg2] & 0xFF);
				register[reg1] = regValue.byteValue();
				pc += 3;
				break;
			case 10: // if (REG != 0) JMP ADDR
				reg1 = memory.getByte(pc + 1);
				addr = memory.getByte(pc + 2);
				if (register[reg1] != 0) {
					pc = addr;
				} else {
					pc += 3;
				}
				break;
			case 11: // GET FROM DISPLAY TO REG
				reg1 = memory.getByte(pc + 1);
				byte input = display.get();
				register[reg1] = input;
				pc += 2;
				break;
			case 12: // PUT TO DISPLAY FROM REG
				reg1 = memory.getByte(pc + 1);
				display.put(register[reg1]);		
				pc += 2;
				break;
			case 13: // if (REG1 != REG2) JMP ADDR
				reg1 = memory.getByte(pc + 1);
				reg2 = memory.getByte(pc + 2);
				addr = memory.getByte(pc + 3);
				
				if (register[reg1] != register[reg2]) {
					pc += 4;
				} else {
					pc = addr;
				}
				break;
			default:
				display.error("Something went wrong!");
				break;
			}
			cmd = memory.getByte(pc);
		}
	}

	public void run() {
		proccessRun(0);
	}

	public void runFrom(int address) {
		proccessRun(address);
	}

	public byte[] getRegisters() {
		return register;
	}

	public int getNumOfRegisters() {
		return register.length;
	}

}
