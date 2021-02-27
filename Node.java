package CS232FinalProject;

public class Node {
	private String word;
	private int countWord;
	private Node lChild;
	private Node rChild;
	
	public Node() {
		this.countWord =1;
	}
    
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int internalGetCountWord() {
		return countWord;
	}
	
	public void setcountWord(int countWord) {
		this.countWord = countWord;
	}
	public Node getlChild() {
		return lChild;
	}
	public void setlChild(Node lChild) {
		this.lChild = lChild;
	}
	public Node getrChild() {
		return rChild;
	}
	public void setrChild(Node rChild) {
		this.rChild = rChild;
	}

}

