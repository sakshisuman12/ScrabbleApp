package com.pelatro.training.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class AlphabetRepository implements Runnable {
	AlphabetRepository ar = null;
	int rate;
	public Hashtable<Character, Integer> hm = new Hashtable<>();
	public int setRate(AlphabetSource as) {
		return this.rate = as.rate;
	}
	
	@SuppressWarnings("resource")
	public void run() 
	{
		ar = new AlphabetRepository();
		try {
		ServerSocket ss = new ServerSocket(4002);
			
				while(true) {
				Socket clientSoc = ss.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
				String input = br.readLine();
//				System.out.println(input);
				Thread ch = new ClientHandler(input,ar);
				ch.start();
				clientSoc.close();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

