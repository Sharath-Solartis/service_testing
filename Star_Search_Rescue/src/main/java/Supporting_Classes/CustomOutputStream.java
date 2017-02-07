package Supporting_Classes;

import javax.swing.JTextArea;
import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream{
	 private JTextArea textArea;
     
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	    }
	     
	    @Override
	    public void write(int b) throws IOException {
	        // redirects data to the text area
	        textArea.append(String.valueOf((char)b));
	        // scrolls the text area to the end of data
	        //textArea.addInputMethodListener();
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }
}
