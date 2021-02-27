package CS232FinalProject;

public class LinkedBinaryTree {
	
	private Node root;
	private int count;
	
	public Node getRoot() {
		return root;
	}
	
	public int getCount() {
		return count;
	}		
    public int getcountWord(Node node) {
    	if(node==null) {
    		return 0;
    	}
    	return node.internalGetCountWord();
    }
	public void add(String word) {
		if (count == 0) {
			Node n = new Node();
			n.setWord(word);
			root = n;
			count = 1;
			return;
		}
		
		internalAdd(root, word);
	}
	
	private void internalAdd(Node root, String word) {
			
		if ((word.compareTo(root.getWord())<0)) {
			Node n = new Node();
			if (root.getlChild() == null) {			
				n.setWord(word);
				count++;
				root.setlChild(n);
				return;
			} else {
				internalAdd(root.getlChild(), word);
			}
		}
		
        if((word.compareTo(root.getWord()))==0) {
        	root.setcountWord(root.internalGetCountWord()+1);
        }
		if ((word.compareTo(root.getWord()))>0 ) {
			Node n = new Node();
			if (root.getrChild() == null) {
				n.setWord(word);
				count++;
				root.setrChild(n);
				return;
			} else {
				internalAdd(root.getrChild(), word);
			}
		}

	}
	public Node Search(Node root, String word) {
		if (root == null) {
			return null;
		}
		
		if ((root.getWord().compareTo(word))==0) {

			return root;
		}else if ((word.compareTo(root.getWord()))<0) {
			return Search(root.getlChild(), word);
		} else{
			return Search(root.getrChild(), word);
		}
	}

	
	int sum = 0;
	public int countWord() {
	    return internalCountWord(root);
	}

	private int internalCountWord(Node root) {
	    if (root != null) {
	        sum+=root.internalGetCountWord();
	        internalCountWord(root.getlChild());
	        internalCountWord(root.getrChild());
	    }
	    return sum;
	}
	
    int frequency=0;
	String mostFrequentWord = "";
	public String frequencyMost() {
			return internalFrequencyMost(root);
     }
		
    private String internalFrequencyMost(Node root) {
	    if (root != null) {
				if(frequency<root.internalGetCountWord()) {
    				frequency = root.internalGetCountWord();
    				mostFrequentWord = root.getWord();
    				}
			
		internalFrequencyMost(root.getlChild());
		internalFrequencyMost(root.getrChild());
	  }
        return mostFrequentWord;
     }

}
