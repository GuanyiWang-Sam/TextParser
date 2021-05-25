
public class IDEX {
      //fields for the ID/EX pipeline registers
	  //control bits(each an int, even though all that space is not needed)
	  // these bits are determined based on the instruction format
	
	  private int RegDst;
	  private int ALUSrc;
	  private int ALUOp;
	  private int MemRead;
	  private int MemWrite;
	  private int MemToReg;
	  private int RegWrite;
	  private int incrPC; 
	  
	  //fields needed to pass to the next part of the pipeline
	  private int ReadReg1Value;
	  private int ReadReg2Value;
	  private int SEOffset;
	  private int WriteReg_20_16;
	  private int WriteReg_15_11;
	  private int Function;
	  private String holdInst;
	  
	  //initialize one set of ID/EX pipeline registers
	public void copyOf(IDEX idex) {
		RegDst=idex.getRegDst();
		ALUSrc=idex.getALUSrc();
		ALUOp=idex.getALUOp();
		MemRead=idex.MemRead;
		MemWrite=idex.MemWrite;
		MemToReg=idex.MemToReg;
		RegWrite=idex.RegWrite;
		ReadReg1Value=idex.ReadReg1Value;
		ReadReg2Value=idex.ReadReg2Value;
		SEOffset=idex.SEOffset;
		WriteReg_20_16=idex.WriteReg_20_16;
		WriteReg_15_11=idex.WriteReg_15_11;
		Function=idex.getFunction();
		holdInst=idex.getHoldInst();
		incrPC=idex.getIncrPC();
	}
	public IDEX() {
		RegDst = 0;
		ALUSrc = 0;
		ALUOp = 0;
		MemRead = 0;
		MemWrite = 0;
		MemToReg = 0;
		RegWrite = 0;
		ReadReg1Value = 0;
		ReadReg2Value = 0;
		SEOffset = 0;
		WriteReg_20_16 = 0;
		WriteReg_15_11 = 0;
		Function = 0;
		holdInst = "nop";
	}
	

	public int getIncrPC() {
		return incrPC;
	}
	public void setIncrPC(int incrPC) {
		this.incrPC = incrPC;
	}
	public int getRegDst() {
		return RegDst;
	}

	public void setRegDst(int regDst) {
		RegDst = regDst;
	}

	public int getALUSrc() {
		return ALUSrc;
	}

	public void setALUSrc(int aLUSrc) {
		ALUSrc = aLUSrc;
	}

	public int getALUOp() {
		return ALUOp;
	}

	public void setALUOp(int aLUOp) {
		ALUOp = aLUOp;
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

	public int getReadReg1Value() {
		return ReadReg1Value;
	}

	public void setReadReg1Value(int readReg1Value) {
		ReadReg1Value = readReg1Value;
	}

	public int getReadReg2Value() {
		return ReadReg2Value;
	}

	public void setReadReg2Value(int readReg2Value) {
		ReadReg2Value = readReg2Value;
	}

	public int getSEOffset() {
		return SEOffset;
	}

	public void setSEOffset(int sEOffset) {
		SEOffset = sEOffset;
	}

	public int getWriteReg_20_16() {
		return WriteReg_20_16;
	}

	public void setWriteReg_20_16(int writeReg_20_16) {
		WriteReg_20_16 = writeReg_20_16;
	}

	public int getWriteReg_15_11() {
		return WriteReg_15_11;
	}

	public void setWriteReg_15_11(int writeReg_15_11) {
		WriteReg_15_11 = writeReg_15_11;
	}

	public int getFunction() {
		return Function;
	}

	public void setFunction(int function) {
		Function = function;
	}

	public String getHoldInst() {
		return holdInst;
	}

	public void setHoldInst(String holdInst) {
		this.holdInst = holdInst;
	}
}
