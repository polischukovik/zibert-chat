package com.opolishchuk.zibert.server.app;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ProtocolException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientThread implements Runnable{
	private final Socket socket;
	private final String name;
	private final Thread thread;
	private final PrintStream out;
	private final Scanner in;
	private boolean isActive;

	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new PrintStream(socket.getOutputStream());
		this.in = new Scanner(socket.getInputStream());
		
		checkProtocol(socket);
		this.name = readClientName(socket); 
		this.isActive = true;
		this.thread = new Thread(this, "Client-" + name);
	}

	@Override
	public void run() {
		try {			
			String line = null;
			while(isActive){
				StringBuilder message = new StringBuilder();
				line = null;
				while(!"".equals(line)){
					line = in.nextLine();
					if("&quit".equals(line)){
						this.isActive = false;
					} else {
						message.append(line);
					}
				}			
				Server.clientToServer(this, message.toString());
			}
		} catch (NoSuchElementException e) {		
			Server.dropClient(this);	
		} finally {
			try {
				this.socket.close();
			} catch (IOException e) {
				System.out.println("Error closing socket");
			}
			Server.dropClient(this);
		}
	}

	private String readClientName(Socket socket) throws IOException {		
		String name = in.nextLine();
		if(name.length() < 3){
			throw new ProtocolException("Name should contain at least 3 symbols");
		}
		return name;
	}

	private void checkProtocol(Socket socket) throws IOException{
		String checkValue = "&";
		if(!checkValue.equals(in.nextLine())){	
			throw new ProtocolException("Invalid protocol header");
		}
	}
	
	public void serverToClient(ClientThread clientThread, String message){		
		out.println(clientThread.getName() + ": " + message);
	}

	public String getName() {
		return name;
	}

	public void start() {
		thread.start();
	}

	@Override
	public String toString() {
		return "Client[" + name + "]";
	}
	
}
