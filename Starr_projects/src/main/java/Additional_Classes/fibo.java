package Additional_Classes;
import java.net.*;
import java.io.*;
import org.dom4j.*;
import org.dom4j.io.*;
public class fibo {
	 public final static String DEFAULT_SERVER
	   = "http://www.elharo.com/fibonacci/XML-RPC";

	  public static void main(String[] args) {

	    if (args.length <= 0) {
	      System.out.println(
	       "Usage: java Fibonaccidom4jClient number url"
	      );
	      return;
	    }

	    String server = DEFAULT_SERVER;
	    if (args.length >= 2) server = args[1];

	    try {
	      // Build request document
	      Document request = DocumentHelper.createDocument();
	      Element methodCall = request.addElement("methodCall");
	      Element methodName = methodCall.addElement("methodName");
	      methodName.addText("calculateFibonacci");
	      Element params = methodCall.addElement("params");
	      Element param = params.addElement("param");
	      Element value = param.addElement("value");
	      // Had to break the naming convention here because of a
	      // conflict with the Java keyword int
	      Element intElement = value.addElement("int");
	      intElement.addText(args[0]);

	      // Transmit the request document
	      URL u = new URL(server);
	      URLConnection uc = u.openConnection();
	      HttpURLConnection connection = (HttpURLConnection) uc;
	      connection.setDoOutput(true);
	      connection.setDoInput(true);
	      connection.setRequestMethod("POST");
	      OutputStream out = connection.getOutputStream();

	      XMLWriter serializer = new XMLWriter(out);
	      serializer.write(request);

	      out.flush();
	      out.close();

	      // Read the response
	      InputStream in = connection.getInputStream();
	      SAXReader reader = new SAXReader();
	      Document response = reader.read(in);
	      in.close();
	      connection.disconnect();

	      // Use XPath to find the element we want
	      Node node = response.selectSingleNode("/methodResponse/params/param/value/double");

	      String result = node.getStringValue();
	      System.out.println(result);

	    }
	    catch (Exception e) {
	      System.err.println(e);
	    }

	  }

}
