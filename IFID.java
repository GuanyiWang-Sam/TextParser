
public class IFID {
	//field for the IF/ID pipeline registers
	private int inst; //the instruction to be executed
	private String decInst; // the decoded instruction 
	private int incrPC; 
	
	//initialize one set of IF/ID pipeline register
	public IFID() {
		inst = 0x0;
		decInst = "nop";
		incrPC =0x7a000;
	}
	
	public int getIncrPC() {
		return incrPC;
	}

	public void setIncrPC(int incrPC) {
		this.incrPC = incrPC;
	}

	public void setInst(int value) {
		inst = value;
	}
	public int getInst() {
		return inst;
	}
	public void setDecInst(String instruct) {
		decInst = instruct;
	}
	public String getDecInst() {
		return decInst;
	}
	public void copyOf(IFID ifid) {
		inst=ifid.getInst();
		decInst = ifid.getDecInst();
		incrPC = ifid.getIncrPC();	
	}

}
