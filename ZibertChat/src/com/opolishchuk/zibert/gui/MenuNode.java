package com.opolishchuk.zibert.gui;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class MenuNode {
	private final MenuNode parent;
	private final String text;		
	private final Set<Option> options;	
		
	public MenuNode(String text) {
		this.parent = null;
		this.text = text;
		this.options = new HashSet<>();
	}
	
	private MenuNode(MenuNode parent, String text){
		this.parent = parent;
		this.text = text;
		this.options = new HashSet<>();
	}
	
//	public MenuNode addOption(int id, String text, Supplier<MenuNode> select){
//		options.add(new Option(id, text, select));
//	}
//	
	static class Option{
		int id;
		String text;
	}
	
	
}
