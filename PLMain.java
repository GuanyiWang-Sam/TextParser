
public class PLMain {
	
	private final static int maskSrc1 = 0x03E00000;
	private final static int maskSrc2 = 0x001F0000;
	private final static int maskDst = 0x0000F800;
	private final static int maskFunc = 0x0000003F;
	private final static int maskOffset = 0x0000FFFF;
	private final static int maskSrc2Dst = 0x001F0000;
	
	public static void main(String[] args) {
		//instances required for execution		
		//set all the initial pipeline classes
		IFID ifidW = new IFID();
		IFID ifidR = new IFID();
		IDEX idexW = new IDEX();
		IDEX idexR = new  IDEX();
		EXMEM exmemW = new EXMEM();
		EXMEM exmemR = new EXMEM();
		MEMWB memwbW = new MEMWB();
		MEMWB memwbR = new MEMWB();
		
		int[] instruct =new int[] {0xa1020000,0x810AFFFC,0x00831820,0x01263820,0x01224820,0x81180000,0x81510010,0x00624022,0x00000000,0x00000000,0x00000000,0x00000000};
		
		//print out a header with author, etc.
		System.out.println("output from the program for Pipleline Simulation");
		
		//initialize the 0x400 elements of Main_Mem
		short[] Main_Mem = new short[0x400];
		for(int mm=0;mm<0x400;mm++) {
			Main_Mem[mm]=(short)((mm &0xff));
		}
		//initialize the 32 "machine' registers
		int Regs[] = new int[32];
		Regs[0] = 0;
		for(int reg = 1;reg <32;reg++) {
			Regs[reg]=(reg + 0x100);
		}
			
		//print out clock cycle 0 pipeline registers
		System.out.println("");
		System.out.println("Clock Cycle 0");
		Print_out_everything(Regs,ifidW,ifidR,idexW,idexR,exmemW,exmemR,memwbW,memwbR);
		
		//this is where the main loop begins
		
		//process the input through the instruction array
		for(int insA =0; insA<instruct.length;insA++) {
			int clockCycle = insA +1;
			System.out.println();
			System.out.println("Clock Cycle" + clockCycle +"(Before we copy the write side of pipeline registers to the read side)");
		    
			IF_stage(instruct[insA],ifidW);
			ID_stage(ifidR,idexW,Regs);
			EX_stage(idexR,exmemW);
			MEM_stage(exmemR,memwbW,Main_Mem);
			WB_stage(memwbR,Regs);
			Print_out_everything(Regs,ifidW,ifidR,idexW,idexR,exmemW,exmemR,memwbW,memwbR);
			Copy_write_to_read(ifidW,ifidR,idexW,idexR,exmemW,exmemR,memwbW,memwbR);
			
		}
		
	}
	public static void Print_out_everything(int Regs[],IFID ifidW,IFID ifidR,IDEX idexW,IDEX idexR, EXMEM exmemW,EXMEM exmemR, MEMWB memwbW, MEMWB memwbR) {
		for(int reg = 1;reg <32;reg++) {
			System.out.print("Registers:["+Integer.toHexString(Regs[reg])+"]  ");
		}
		
		System.out.println("IF/ID Write(written to by the IF stage)");
		System.out.println("Inst = 0x" +Integer.toHexString(ifidW.getInst())+" "+ ifidW.getDecInst() + " incrPC="+Integer.toHexString(ifidW.getIncrPC()));
		
		System.out.println("IF/ID Read(read by the ID stage)");
		System.out.println("Inst = 0x" +Integer.toHexString(ifidR.getInst())+" "+ ifidR.getDecInst() + " incrPC="+Integer.toHexString(ifidR.getIncrPC()));
		
		
		System.out.println("ID/EX Write(written to by the ID stage)");
		System.out.println("Control: RegDst = " +idexW.getRegDst()+" ALUSrc="
		+ idexW.getALUSrc() + " ALUOp=" + idexW.getALUOp()+" MemRead=" 
		+idexW.getMemRead()+" MemWrite="+ idexW.getMemWrite()
		+" MemToReg="+ idexW.getMemToReg()+" RegWrite=" + idexW.getRegWrite()
		+" ReadReg1Value="+ idexW.getReadReg1Value()+" ReadReg2Value="+ idexW.getReadReg2Value()
		+" SEOffset="+ idexW.getSEOffset()+ " WriteReg_20_16="+idexW.getWriteReg_20_16()
		+" WriteReg_15_11="+idexW.getWriteReg_15_11()
		+" Function"+idexW.getFunction()
		+"incrPC="+Integer.toHexString(idexW.getIncrPC()));
		
		System.out.println("ID/EX Read(read by the EX stage)");
		System.out.println("Control: RegDst = " +idexR.getRegDst()+" ALUSrc="
				+ idexR.getALUSrc() + " ALUOp=" + idexR.getALUOp()+" MemRead=" 
				+idexR.getMemRead()+" MemWrite="+ idexR.getMemWrite()
				+" MemToReg="+ idexR.getMemToReg()+" RegWrite=" + idexR.getRegWrite()
				+" ReadReg1Value="+ idexR.getReadReg1Value()+" ReadReg2Value="+ idexR.getReadReg2Value()
				+" SEOffset="+ idexR.getSEOffset()+ " WriteReg_20_16="+idexR.getWriteReg_20_16()
				+" WriteReg_15_11="+idexR.getWriteReg_15_11()
				+" Function"+idexR.getFunction()
				+"incrPC="+Integer.toHexString(idexR.getIncrPC()));
		
		System.out.println("EX/MEM Write(written to by the EX stage)");
		System.out.println("Control: MemRead=" +exmemW.getMemRead()+" MemWrite="+ exmemW.getMemWrite()
		+" MemToReg="+ exmemW.getMemToReg()+" RegWrite="+exmemW.getRegWrite()
		+" ALUResult="+Integer.toHexString(exmemW.getALUResult())+" SWValue="+ Integer.toHexString(exmemW.getSWValue())
		+" WriteRegNum="+exmemW.getWriteRegNum());
		
		System.out.println("EX/MEM Read(read by the MEM stage)");
		System.out.println("Control: MemRead=" +exmemR.getMemRead()+" MemWrite="+ exmemR.getMemWrite()
		+" MemToReg="+ exmemR.getMemToReg()+" RegWrite="+exmemR.getRegWrite()
		+" ALUResult="+Integer.toHexString(exmemR.getALUResult())+" SWValue="+ Integer.toHexString(exmemR.getSWValue())
		+" WriteRegNum="+exmemR.getWriteRegNum());
		
		System.out.println("MEM/WB Write(written to by the MEM stage)");
		System.out.println("Control: MemToReg="+memwbW.getMemToReg()+" RegWrite="+memwbW.getRegWrite()
		+" LWDataValue="+Integer.toHexString(memwbW.getLWDataValue())+" ALUResult="+ Integer.toHexString(memwbW.getALUResult())
		+" WriteRegNum="+memwbW.getWriteRegNum()+" WBAction" + memwbW.getWBAction());
		
		System.out.println("MEM/WB Read(read by the WB stage)");
		System.out.println("Control: MemToReg="+memwbR.getMemToReg()+" RegWrite="+memwbR.getRegWrite()
		+" LWDataValue="+Integer.toHexString(memwbR.getLWDataValue())+" ALUResult="+ Integer.toHexString(memwbR.getALUResult())
		+" WriteRegNum="+memwbR.getWriteRegNum()+" WBAction=" + memwbR.getWBAction());
		
	}
	public static void Copy_write_to_read(IFID ifidW,IFID ifidR,IDEX idexW,IDEX idexR, EXMEM exmemW,EXMEM exmemR, MEMWB memwbW, MEMWB memwbR) {
		ifidR.copyOf(ifidW);
		idexR.copyOf(idexW);
		exmemR.copyOf(exmemW);
		memwbR.copyOf(memwbW);
			
	}
	
	//IF_stage method  - simulate user input 
	public static void IF_stage (int instruct,IFID ifidW) {
		ifidW.setInst(instruct);
		ifidW.setDecInst(decInst(instruct));
		ifidW.setIncrPC(ifidW.getIncrPC()+4);
	}
	public static String decInst(int instruct) {
		String decInst = "XX";
		int opCode = (instruct>>> 26);
		int funCode = calculateField(instruct,maskFunc,0);
		//R type
		if(opCode == 0) {
			if(funCode == 0x20) {
				decInst = "[ add" +" $"+calculateField(instruct,maskDst,11)+" ,$"+ calculateField(instruct,maskSrc1,21)+" ,$"+ calculateField(instruct,maskSrc2,16) +"]";
			}else if(funCode == 0x22) {
				decInst = "[ sub " +" $"+calculateField(instruct,maskDst,11)+" ,$"+ calculateField(instruct,maskSrc1,21)+" ,$"+ calculateField(instruct,maskSrc2,16) +"]";
			}		
		}else if((opCode == 0x20)||(opCode == 0x23)) {//load
			decInst = "[ lw " +" $"+ calculateField(instruct,maskSrc2,16) +" "+ calculateOffset(instruct)+ " ($"+ calculateField(instruct,maskSrc1,21)+") ]";
		}else if(opCode == 0x28) {//store
			decInst = "[ sw " +" $"+ calculateField(instruct,maskSrc2,16) +" "+ calculateOffset(instruct)+ " ($"+ calculateField(instruct,maskSrc1,21)+") ]";;
		}
		return decInst;
	}
	
	
	public static int calculateField(int instruct, int mask, int numBits) {
		int field;
		field = (instruct & mask);
		field = field >>> numBits;
		return field;
	}
	public static int calculateOffset(int instruct) {
		int field = 0;
		if((instruct & maskOffset)>>>15 == 1) {
			field = -(~((instruct & maskOffset)-1) & maskOffset);
		}else {
			field = instruct & maskOffset;
		}
		return field;
	}
	public static String determineOpCode(String format, int instruct) {
		String Opcode ="XX";
		if(format == "r") {
			if(instruct == 0x20) {
				Opcode = "add";
			}else if(instruct == 0x22) {
				Opcode = "sub";
			}
		}else if(format == "i"){
			if(instruct == 0x23) {
				Opcode = "lw";
			}else if(instruct == 0x2B) {
				Opcode = "sw";
			}
		}else {
			Opcode = "XX";
		}
		return Opcode;
	}
	
    //ID_stage method - set the ID/EX registers fields
	public static void ID_stage(IFID ifidR,IDEX idexW, int[] Regs) {
		if(ifidR.getInst()!=0) {
			int opCode = (ifidR.getInst()>>> 26);
			// int function =(calculateField(ifidR.getInst(),maskFunc,0));
			//fields needed to pass from IDEX to the next part of the pipeline
			idexW.setReadReg1Value (Regs[calculateField(ifidR.getInst(),maskSrc1,21)]);
			idexW.setReadReg2Value(Regs[calculateField(ifidR.getInst(),maskSrc2,16)]);
			idexW.setSEOffset(calculateOffset(ifidR.getInst()));
			idexW.setWriteReg_20_16(calculateField(ifidR.getInst(),maskSrc2Dst,16));
			idexW.setWriteReg_15_11(calculateField(ifidR.getInst(),maskDst,11));
			idexW.setFunction(calculateField(ifidR.getInst(),maskFunc,0));
			//set the ID/EX control fields by instruction type
			if(opCode == 0) {
				//this is an R-format instruction
				idexW.setRegDst(1);
				idexW.setALUSrc(0);
				idexW.setALUOp(10);
				idexW.setMemRead(0);
				idexW.setMemWrite(0);
				idexW.setMemToReg(0);
				idexW.setRegWrite(1);
				idexW.setHoldInst((determineOpCode("r", calculateField(ifidR.getInst(),maskFunc,0))));
				idexW.setSEOffset(-1);
		}else {
			if((opCode == 0x20 || (opCode == 0x23))) {
				//this is a load type instruction
				idexW.setRegDst(0);
				idexW.setALUSrc(1);
				idexW.setALUOp(00);
				idexW.setMemRead(1);
				idexW.setMemWrite(0);
				idexW.setMemToReg(1);
				idexW.setRegWrite(1);
				idexW.setHoldInst(determineOpCode("i",(ifidR.getInst()>>>26)));
				idexW.setFunction(-1);
				
			}else {
				//this is a store type instruction 
				idexW.setRegDst(-1);
				idexW.setALUSrc(1);
				idexW.setALUOp(00);
				idexW.setMemRead(0);
				idexW.setMemWrite(1);
				idexW.setMemToReg(-1);
				idexW.setRegWrite(0);
				idexW.setHoldInst(determineOpCode("i",(ifidR.getInst()>>>26)));
				idexW.setFunction(-1);
			}
		}
	}
		else {
			//set the values to 0 when the instruction is all 0's
			idexW.setReadReg1Value(0);
			idexW.setReadReg2Value(0);
			idexW.setSEOffset(0);
			idexW.setWriteReg_20_16(0);
			idexW.setWriteReg_15_11(0);
			idexW.setFunction(0);
			idexW.setRegDst(0);
			idexW.setALUSrc(0);
			idexW.setALUOp(0);
			idexW.setMemRead(0);
			idexW.setMemWrite(0);
			idexW.setMemToReg(0);
			idexW.setRegWrite(0);
			idexW.setHoldInst("nop");
		}
	}
		
		//EX_stage method - set the EX/MEM registers fields
		public static void EX_stage (IDEX idexR,EXMEM exmemW) {
			//execute only if there is data in the pipeline registers
			if((idexR.getMemWrite() == 1 || (idexR.getMemRead() == 1)||(idexR.getRegWrite() == 1))){
				//carry the control bits through to the EX/MEM pipeline 
				exmemW.setMemRead(idexR.getMemRead());
				exmemW.setMemWrite(idexR.getMemWrite());
				exmemW.setMemToReg(idexR.getMemToReg());
				exmemW.setRegWrite(idexR.getRegWrite());
				
				//set the EX/MEM pipeline fields by instruction type 
				if(idexR.getALUOp() == 10) {
					//this is an R-format instruction
					if(idexR.getALUOp() ==10) {
						//this is an R-format instruction
						if(idexR.getFunction() == 0x20) {
							exmemW.setALUResult(idexR.getReadReg1Value()+idexR.getReadReg2Value());
						}else {
							exmemW.setALUResult(idexR.getReadReg1Value()-idexR.getReadReg2Value());	
						}
						exmemW.setSWValue(idexR.getReadReg2Value());
						exmemW.setWriteRegNum(idexR.getWriteReg_15_11());
					}else {
						if(idexR.getRegDst()==0) {
							//this is a load type instruction
							exmemW.setALUResult(idexR.getReadReg1Value()+idexR.getSEOffset());
							exmemW.setSWValue(idexR.getReadReg2Value());
							exmemW.setWriteRegNum(-1);
						}
					}
				}
				else {
					//set the values to 0 when there's no activity in the pipeline 
					exmemW.setMemRead(0);
					exmemW.setMemWrite(0);
					exmemW.setMemToReg(0);
					exmemW.setRegWrite(0);
					exmemW.setALUResult(0);
					exmemW.setSWValue(0);
					exmemW.setWriteRegNum(0);
					exmemW.setHoldInst("nop");
				}
			}
		}
		
			//MEM_stage method - set the MEM/WB registers fields
			//public static void MEM_stage (EXMEM exmemR, MEMWB memwbW, int[] Regs, short[] Main_Mem)
			public static void MEM_stage(EXMEM exmemR, MEMWB memwbW, short[] Main_Mem) {
				String WBAction;
				//execute only if there is data in the pipeline register
				if((exmemR.getMemToReg()!= 0) || (exmemR.getRegWrite()!=0)) {
					//carry the control bits through to the EX\MEM pipeline 
					memwbW.setMemToReg(exmemR.getMemToReg());
					memwbW.setRegWrite(exmemR.getRegWrite());
					memwbW.setHoldInst(exmemR.getHoldInst());
					
					//set the EX/MEM pipeline fields by instruction type 
					if(exmemR.getMemToReg() == 0) {
						//this is an R-format instruction
						memwbW.setLWDataValue(-1);
						memwbW.setALUResult(exmemR.getALUResult());
						memwbW.setWriteRegNum(exmemR.getWriteRegNum());
						WBAction = "($" +memwbW.getWriteRegNum() +"will be set to a new value of"
								+ (String.format("%02x",(memwbW.getALUResult()))) + " in the WB stage)";
						memwbW.setWBAction(WBAction);
					
				}else {
					if(exmemR.getRegWrite() == 1) {
						//this is a load type instruction
						memwbW.setALUResult(exmemR.getALUResult());
						memwbW.setLWDataValue(Main_Mem[exmemR.getALUResult()]);
						memwbW.setWriteRegNum(exmemR.getWriteRegNum());
						WBAction= "($" +memwbW.getWriteRegNum() +"will be set to a new value of" + (String.format("%02x",(memwbW.getLWDataValue())))+" in the WB stage";
			            memwbW.setWBAction(WBAction);
						
					}else {
						//this is a store type instruction
						memwbW.setALUResult(exmemR.getALUResult());
						memwbW.setLWDataValue(-1);
						memwbW.setWriteRegNum(-1);
						Main_Mem[exmemR.getALUResult()] = (short)exmemR.getSWValue();
						WBAction = "(Value" + (String.format("%02x",(Main_Mem[exmemR.getALUResult()])))
	           							     +" has been written to memory address"
	                                         +(String.format("%02x",(memwbW.getALUResult())))+ " in the MEM stage)";
	                    memwbW.setWBAction(WBAction);
					}
				}
			}
				else {
					//set the values to 9 when there's no activity in the pipeline
					memwbW.setMemToReg(0);
					memwbW.setRegWrite(0);
					memwbW.setLWDataValue(0);
					memwbW.setALUResult(0);
					memwbW.setWriteRegNum(0);
					memwbW.setHoldInst("nop");
				}
		}
			//WB_stage method -write back to the registers on a load or r-type
			public static void WB_stage(MEMWB memwbR, int[] Regs) {
				//execute only if there is data in the pipeline registers
				if((memwbR.getRegWrite()==1)&&(memwbR.getMemToReg() == 0)) {
					Regs[memwbR.getWriteRegNum()]
					=memwbR.getALUResult();
				}else if((memwbR.getRegWrite()==1)&&(memwbR.getMemToReg() == 1)){
					Regs[memwbR.getWriteRegNum()]
					=memwbR.getLWDataValue();
				    }
		
			}	
			
	}
	


