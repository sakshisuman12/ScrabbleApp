package com.pelatro.training.project;


import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientHandler extends Thread {
	String input;
	AlphabetRepository ar = null;
	public ClientHandler(String msg, AlphabetRepository ar) {
		this.input = msg;
		this.ar = ar;
	}

	public void run() {
		if(input.startsWith("#")) {
			input=input.substring(1);
			char c = input.charAt(0);
//			System.out.println(c);
			alphabetRepo(c);
//			System.out.println(ar.hm);
		}
		else if(input.startsWith("$")) {
			input=input.substring(1);
			if(input.equalsIgnoreCase("Exit"))
				System.exit(0);
			String status;
			boolean a = wordFormation(input);
			if(a) {
				System.out.println("Congratulation! "+input+" Word formed");
				status="Success";
			}
			else {
				System.out.println("Word "+input+" cannot be formed.");
				status="Failure";
			}
			updateWordRepository(input,status);
		}
	}
	private void updateWordRepository(String word, String status) {
		  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.getTransaction().begin();
		  
		  WordRepository wordR = new WordRepository();
		  Date date= new Date();
		  long time = date. getTime();
		  
		  Timestamp ts = new Timestamp(time);
		  
		  wordR.setStatus(status);
		  wordR.setTime(ts);
		  wordR.setWord(word);
		  session.save(wordR);
		  
		  session.getTransaction().commit();
		  session.close();
	}

	@SuppressWarnings("unused")
	synchronized public boolean wordFormation(String input) {
		char word[] = input.toCharArray();
		int i=0;
		try {
			String str = "";
			for(char w:word) {
				if(ar.hm.containsKey(w) == false)
				{
					notifyAll();
					wait(5000);
					if(ar.hm.containsKey(w) == false)
					{
						updateTable(str);
						return false;
					}
				}
				updateWord(w);
				str += w;
			}
		} catch (Exception e) {
				e.printStackTrace();
			}
		return true;					
	}
	private void updateWord(char w) {
		if(ar.hm.containsKey(w) == true){
			Integer c=ar.hm.get(w);
			if(c > 1)
			{
				ar.hm.put(w, c-1);
			}
			else if(c == 1)
			{
				ar.hm.remove(w);
			}
		}
	}
	private void updateTable(String str) {
		char chars[] = str.toCharArray();
		for(char c:chars)
			alphabetRepo(c);
	}

	synchronized public void alphabetRepo(Character c){
		
		Integer count = ar.hm.get(c);
		try {
//			wait(ar.rate*1000);
			if(count==null)
			{
				ar.hm.put(c, 1);
			}
			else
				ar.hm.put(c, count+1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
