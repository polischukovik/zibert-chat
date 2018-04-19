package com.opolishchuk.zibert.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import com.opolishchuk.zibert.gui.MenuAction;
import com.opolishchuk.zibert.logging.Logger;

public class Server{

	private static Logger logger = Logger.getLogger();
	private final ServerSocket serverSocket;
	
	public Server(int port) throws IOException{
		this.serverSocket = new ServerSocket(port);
	}
	
	private static Server instance = null;
	public static void main(String[] args) {
		Consumer<Integer> startServer = port -> startServer(port);
		
		MenuAction menuAction = new MenuAction(startServer);
		menuAction.startLoop();
	}

	//TODO make multiple servers
	private static void startServer(Integer port) {
		if(instance == null){
			logger.info("Server is already started");
		}
	}

}
