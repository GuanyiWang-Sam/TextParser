
public class EXMEM {

	//fields for the ID/EX pipeline registers
	// control bits(each an int, even though all that space is not needed)
    //these bits are determined based on the instruction format
	
	private int MemRead;
	private int MemWrite;
	private int MemToReg;
	private int RegWrite;
	//fields needed to pass to the next part of the pipeline 
	private int ALUResult;
	private int SWValue;
	private int WriteRegNum;
	private String holdInst;
	
	public void copyOf(EXMEM exmem) {
		MemRead=exmem.getMemRead();
		MemWrite=exmem.getMemWrite();
		MemToReg=exmem.getMemToReg();
		RegWrite=exmem.getRegWrite();
		ALUResult=exmem.getALUResult();
		SWValue=exmem.getSWValue();
		WriteRegNum=exmem.getWriteRegNum();
		holdInst=exmem.getHoldInst();
	}
	
	//initialize one set of ID/EX pipeline registers
	public EXMEM() {
		MemRead = 0;
		MemWrite = 0;
		MemToReg = 0;
		RegWrite = 0;
		//fields needed to pass to the next part of the pipeline
		ALUResult = 0;
		SWValue = 0;
		WriteRegNum = 0;
		holdInst = "nop";
	}

	public int getMemRead() {
		return MemRead;
	}

	public void setMemRead(int memRead) {
		MemRead = memRead;
	}

	public int getMemWrite() {
		return MemWrite;
	}

	public void setMemWrite(int memWrite) {
		MemWrite = memWrite;
	}

	public int getMemToReg() {
		return MemToReg;
	}

	public void setMemToReg(int memToReg) {
		MemToReg = memToReg;
	}

	public int getRegWrite() {
		return RegWrite;
	}

	public void setRegWrite(int regWrite) {
		RegWrite = regWrite;
	}

	public int getALUResult() {
		return ALUResult;
	}

	public void setALUResult(int aLUResult) {
		ALUResult = aLUResult;
	}

	public int getSWValue() {
		return SWValue;
	}

	public void setSWValue(int sWValue) {
		SWValue = sWValue;
	}

	public int getWriteRegNum() {
		return WriteRegNum;
	}

	public void setWriteRegNum(int writeRegNum) {
		WriteRegNum = writeRegNum;
	}

	public String getHoldInst() {
		return holdInst;
	}

	public void setHoldInst(String holdInst) {
		this.holdInst = holdInst;
	}
}
