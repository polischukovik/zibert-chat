package com.opolishchuk.zibert.client.app;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable{
	private Scanner in;
	private PrintStream out;
	private Socket socket;
	private Thread incomingThread;
	private boolean isRunning;

	public Client(String host, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
	}

	public static void main(String[] args) {
		System.out.println("***** Zibert chat *****");
		System.out.println("Enter name:");
		
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		String host = "localhost";
		int port = 5500;
		
		System.out.println("Connecting to server " + host + ":" + port);
		
		try {
			Client client = new Client(host, port);
			client.start();
		} catch (IOException e) {
			System.out.println("Failed to connect to server");
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void start() {
		this.incomingThread = new Thread(this);
		
	}

	@Override
	public void run() {
		while(isRunning) {
			in.nextLine()
		}
	}
	
}
