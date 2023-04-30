import java.io.*;
import java.net.*;

public class Client {

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        int result = -1;
        try {
            // open socket
            Socket socket = new Socket("localhost", PORT);

            // create object to send
            String methodName = "add";
            Object[] args = {lhs, rhs};
            Request req = new Request(methodName, args);

            // send request
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(req);
            oos.flush();

            // read response
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Response res = (Response) ois.readObject();
            result = (int) res.getResult();

            // close sockets
            socket.close();
            oos.close();
            ois.close();
            os.close();
            is.close();

        } catch (Exception e) {
            if (e.getMessage().equals("Connection refused")) {
                System.out.println("Cannot connect to server");
            } else {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();
        int result = -1;
        try {
            // open socket
            Socket socket = new Socket("localhost", PORT);

            // create object to send
            String methodName = "divide";
            Object[] args = {num, denom};
            Request req = new Request(methodName, args);

            // send request
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(req);
            oos.flush();

            // read response
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Response res = (Response) ois.readObject();
            result = (int) res.getResult();

            // close sockets
            socket.close();
            oos.close();
            ois.close();
            os.close();
            is.close();

        } catch (Exception e) {
            if (e.getMessage().equals("Connection refused")) {
                System.out.println("Cannot connect to server");
            } else {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        String result = "";
        try {
            // open socket
            Socket socket = new Socket("localhost", PORT);

            // create object to send
            String methodName = "echo";
            Object[] args = {message};
            Request req = new Request(methodName, args);

            // send request
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(req);
            oos.flush();

            // read response
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Response res = (Response) ois.readObject();
            result = (String) res.getResult();

            // close sockets
            socket.close();
            oos.close();
            ois.close();
            os.close();
            is.close();
            
        } catch (Exception e) {
            if (e.getMessage().equals("Connection refused")) {
                System.out.println("Cannot connect to server");
            } else {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}