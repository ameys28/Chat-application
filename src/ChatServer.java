import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
  private static Set<PrintWriter> clientWriters = new HashSet<>();

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(12345); // Port number for chat
    System.out.println("Chat server started on port 12345...");

    while (true) {
      Socket clientSocket = serverSocket.accept(); // Accept incoming client connections
      System.out.println("Client connected: " + clientSocket.getInetAddress());
      new ClientHandler(clientSocket).start(); // Handle each client in a separate thread
    }
  }

  // Inner class to handle each client connection
  private static class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {
        // Setting up I/O streams
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        synchronized (clientWriters) {
          clientWriters.add(out); // Add the client's PrintWriter to the set
        }

        String message;
        while ((message = in.readLine()) != null) {
          System.out.println("Received: " + message);
          // Broadcast the message to all connected clients
          synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
              writer.println(message); // Send message to all clients
            }
          }
        }
      } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
      } finally {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
