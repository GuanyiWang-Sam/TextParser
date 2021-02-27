package CS232FinalProject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {
    JTextField textField = new JTextField(20);
    LinkedBinaryTree tree = new LinkedBinaryTree();
    List<String> questionList = new ArrayList<>();
    List<Object> answerList = new ArrayList<>();

    public MyFrame(String title) {
        super(title);

        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new FlowLayout());

        JButton button01 = new JButton("Browse");
        JButton button02 = new JButton("Choice");
        JButton saveBtn = new JButton("Save All");
        root.add(new JLabel("File"));
        root.add(textField);
        root.add(button01);
        root.add(button02);
        root.add(saveBtn);

        button01.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });
        button02.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choices();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSave();
            }
        });

    }


    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        chooser.setFileFilter(filter);

        int ret= chooser.showOpenDialog(this);

        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            textField.setText(file.getAbsolutePath());

            TextParser textParser = new TextParser();
            textParser.openFile(file.getAbsolutePath());
            while (true) {
                String word = textParser.getNextWord();
                if (word == null) {
                    break;
                }
                tree.add(word);
            }
        }
    }

    private void choices() {
        Object[] options = {"How many times does the word appear in the text?",
                "How many different words are there in the text?",
                "How many total words are in the text?",
                "Which word occurs most frequently?"};
        String select = (String) JOptionPane.showInputDialog(
                this,
                "Please choose from the following choices",
                "Questions",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                null);
        if (select == null) {
            JOptionPane.showMessageDialog(this, "Task End");
        } else if (select.compareTo("How many times does the word appear in the text?") == 0) {
            String input = JOptionPane.showInputDialog(
                    this,
                    "Input the word to search",
                    null);
            if (input == null) {
                JOptionPane.showMessageDialog(this, "Task End");
            } else {
                int count = tree.getcountWord(tree.Search(tree.getRoot(), input));
                questionList.add("How many times does the word '" + input + "' appear in the text?");
                answerList.add(count);
                JOptionPane.showMessageDialog(this, count);

            }
        } else if (select.compareTo("How many different words are there in the text?") == 0) {
            int count = tree.getCount();
            questionList.add("How many different words are there in the text?");
            answerList.add(count);
            JOptionPane.showMessageDialog(this, count);
        } else if (select.compareTo("How many total words are in the text?") == 0) {
            int count = tree.countWord();
            questionList.add("How many total words are in the text?");
            answerList.add(count);
            JOptionPane.showMessageDialog(this, count);
        } else if (select.compareTo("Which word occurs most frequently?") == 0) {
            String mostWord = tree.frequencyMost();
            questionList.add("Which word occurs most frequently?");
            answerList.add(mostWord);
            JOptionPane.showMessageDialog(this, mostWord);
        }
    }


    private void doSave() {
        if (questionList.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a file and choose from the following choices first");
            return;
        }

        File file = new File("result.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                String result = mapString(questionList, answerList);
                bw.write(result);
                JOptionPane.showMessageDialog(this,
                        "Success!");
            } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String mapString(List<String> keyList, List<Object> valueList) {
        if (keyList == null) {
            return null;
        }
        if (keyList.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        int len = keyList.size();
        for (int i = 0; i < len; i++) {
            sb.append("\t\"").append(keyList.get(i)).append("\"").append(":")
                    .append("\"").append(valueList.get(i)).append("\"").append("\n");
        }
        
        sb.append("\n}");
        return sb.toString();
    }

}
