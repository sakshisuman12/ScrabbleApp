package com.pelatro.training.project;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class WordBuilder implements Runnable {
	public String word;
	
	public WordBuilder(String s){
		this.word = s;
	}
	
	public void wordInput() {
		try {
			Socket soc = new Socket("LOCALHOST", 4002);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
//			System.out.println("Connected to the Source");
			this.word = "$"+this.word;
			pw.println(this.word);
				
			pw.close();
			soc.close();
//			System.out.println("Word processed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		wordInput();
	}
}
