package com.opolishchuk.zibert.gui;

import java.util.function.Consumer;

public class MenuAction {
	private Consumer<Integer> startServer;
	private boolean isTerminated;
	
	private static final MenuGraph GRAPH;
	
	static{
		GRAPH = new MenuGraph();
		
	}

	public MenuAction(Consumer<Integer> startServer) {
		this.startServer =  startServer;
		this.isTerminated = false;
	}

	public void startLoop() {
		while(!isTerminated){

			showMenu()
		}
	}

}
