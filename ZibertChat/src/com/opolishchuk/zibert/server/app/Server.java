package com.opolishchuk.zibert.server.app;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import com.opolishchuk.zibert.logging.Logger;

public class Server{

	private static Logger logger = Logger.getLogger();
	private final ServerSocket serverSocket;
	
	private Server(int port) throws IOException{
		this.serverSocket = new ServerSocket(port);
	}
	
	private static Set<ClientThread> clients = new HashSet<>();
	
	private static Server instance = null;

	public static Server getInstance(int port) throws IOException{
		//TODO allow multiple servers here
		System.out.println("Starting server on port " + port);
		return new Server(port);
	}
	
	//TODO make multiple servers
	private static void startServer(Integer port) {
		if(instance == null){
			logger.info("Server is already started");
		}
	}
	
	public static void main(String[] args) {
		Server server = null;
		try {
			server = Server.getInstance(5500);
			System.out.println("Server started");
		} catch (IOException e) {
			//Port may not be acquired
			e.printStackTrace();
		}		
		
		server.listen();		
	}

	private void listen() {
		System.out.println("Listening for incoming connections...");
		while(true){
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				
				System.out.println("Incoming connecion form " + socket);
				ClientThread client = new ClientThread(socket);
				clients.add(client);
				
				System.out.println("New client: " + client.getName() + "@" + socket.getInetAddress());
				client.start();
				
			} 
			catch (ProtocolException e) {				
				System.out.println("Disconnected " + socket);
				try {
					socket.close();
				} catch (IOException e1) {
					System.out.println("Exception happened when closing the socket");
				}
				e.printStackTrace();
			}
			catch (IOException e) {
				System.out.println("I/O error occurs when waiting for a connection");
				e.printStackTrace();
			}
		}
	}

	public static void clientToServer(ClientThread clientThread, String message) {
		System.out.println("Message form " + clientThread + ": " + message);
		clients.forEach(client -> client.serverToClient(clientThread, message));
	}

	public static void dropClient(ClientThread clientThread) {
		clients.remove(clientThread);
		System.out.println(clientThread + " was dropped");
	}

}
