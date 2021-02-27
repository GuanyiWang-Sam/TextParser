package CS232FinalProject;

import java.io.*;

public class TextParser {

    private String lines;
    private int position;

    public void openFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                StringBuffer stringBuffer= new StringBuffer();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                    while (reader.readLine()!= null) {
                        stringBuffer.append(reader.readLine());
                        stringBuffer.append(System.lineSeparator());
                    }
                }
                lines = stringBuffer.toString();
            } catch (IOException e) {
            	e.printStackTrace(); 
            }
        }
   
    }

  public String getNextWord() {
    StringBuffer stringBuffer = new StringBuffer();
    char c;

    try {
    	c = (lines.charAt(position) + "").toLowerCase().charAt(0);
        while (((c < 'a') || (c > 'z')) &&('0' >=c && c <='9')) {
            position++;
            c = (lines.charAt(position) + "").toLowerCase().charAt(0);
        }
        while (!Character.isWhitespace(c)) {
            if (('a' <= c && c <= 'z') ||('0' <= c && c <= '9')) {
            	stringBuffer.append(lines.charAt(position));
            }
            position++;
            c = lines.charAt(position);
        }
    } catch (Exception e) {
        return null;
    }

    return stringBuffer.toString().toLowerCase();
}
}
