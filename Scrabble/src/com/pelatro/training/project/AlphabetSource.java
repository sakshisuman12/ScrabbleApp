package com.pelatro.training.project;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AlphabetSource extends Thread {
	
	public int rate;
	AlphabetRepository alr = null;
	public List<Character> factoryAlphabet = new ArrayList<Character>();
	AlphabetSource(int rate, List<Character> factoryAlphabet)
	{
		this.rate=rate;
//		this.factoryAlphabet=factoryAlphabet;
		for(char i:factoryAlphabet) {
			this.factoryAlphabet.add(i);
		}
	}
	
	public int getRate() {
		return rate;
	}
	
	public void run(){
		alr = new AlphabetRepository();
		alr.setRate(this);
		char c;
		int i = 0;
//		factory=Scrabble.loadConfigurationFile();	
		try {
			while(true) {
				c = factoryAlphabet.get(i);
				Socket soc = new Socket("LOCALHOST", 4002);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
//				System.out.println("Connected to the Source");
				String msg = "#"+c;
				pw.println(msg);
				pw.close();
				soc.close();
				i = (i+1)%factoryAlphabet.size();
				Thread.sleep(this.rate*1000);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
