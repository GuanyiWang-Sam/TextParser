
public class MEMWB {

	//fields for the ID/EX pipeline registers
	//control bits(each an int, even though all that space is not needed)
	//these bits are determined based on the instruction format 
	
	private int MemToReg;
	private int RegWrite;
	//fields needed to pass to the next part of the pipeline
	private int LWDataValue;
	private int ALUResult;
	private int WriteRegNum;
	private String holdInst;
	private String WBAction;
	
	public void copyOf(MEMWB memwb) {
		MemToReg=memwb.getMemToReg();
		RegWrite=memwb.getRegWrite();
		LWDataValue=memwb.getLWDataValue();
		ALUResult=memwb.getALUResult();
		WriteRegNum=memwb.getWriteRegNum();
		holdInst=memwb.getHoldInst();
		WBAction=memwb.getWBAction();
	}
	
	//initialize one set of ID/EX pipeline registers
    public MEMWB() {
	//control bits(each an int, even though all that space is not needed)
		MemToReg = 0;
		RegWrite = 0;
		//fields needed to pass to the next part of the pipeline
		ALUResult = 0;
		LWDataValue = 0;
		WriteRegNum = 0;
		holdInst = "nop";
		WBAction = "nop";
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

	public int getLWDataValue() {
		return LWDataValue;
	}

	public void setLWDataValue(int lWDataValue) {
		LWDataValue = lWDataValue;
	}

	public int getALUResult() {
		return ALUResult;
	}

	public void setALUResult(int aLUResult) {
		ALUResult = aLUResult;
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

	public String getWBAction() {
		return WBAction;
	}

	public void setWBAction(String wBAction) {
		WBAction = wBAction;
	}	
	
}
