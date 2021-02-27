package CS232FinalProject;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Driver{
	private static void createGUI(){
		JFrame frame = new MyFrame("Text Parser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(400, 300);
		frame.setVisible(true);

	}

	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				createGUI();
			}
		});

	}
}
