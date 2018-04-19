package com.opolishchuk.zibert.client.app;

import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

	public static void main(String[] args) {
		System.out.println("***** Zibert chat *****");
		System.out.println("Enter name:");
		
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		String host = "localhost";
		int port = 5500;
		
		System.out.println("Connecting to server " + host + ":" + port);
		Socket socket = new Socket(host, port);
	
	}

	@Override
	public void run() {
		
	}
	
}
