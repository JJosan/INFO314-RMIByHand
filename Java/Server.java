import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 10314;
    private static final int THREADS = 5;
    private static ExecutorService exec = null;
    
    public static void main(String[] args) throws IOException {
        exec = Executors.newFixedThreadPool(THREADS);
        exec.submit(() -> rmiServer());
    }

    private static void rmiServer() {
        System.out.println("server started on port: "+PORT);
        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = null;
            while ((socket = server.accept()) != null) {
                final Socket threadSocket = socket;
                exec.submit( () -> handleRmiRequest(threadSocket));
            }
            server.close();
        } catch(IOException e) {

        }
    }

    private static void handleRmiRequest(Socket socket) {
        try {
            // read request
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Request req = (Request) ois.readObject();
            String methodName = req.getMethodName();
            Object[] args = req.getArguments();

            // get correct function and get result
            Object result = null;
            switch(methodName) {
                case "echo":
                    result = echo((String)args[0]);
                    break;
                case "add":
                    result = add((int)args[0], (int)args[1]);
                    break;
                case "divide":
                    result = divide((int)args[0], (int)args[1]);
                    break;
                default:
                    break;
            }
            // send response
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            Response res = new Response(result);
            oos.writeObject(res);
            oos.flush();


        } catch(Exception e) {

        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}