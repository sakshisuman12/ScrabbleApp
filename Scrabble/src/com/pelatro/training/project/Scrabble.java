package com.pelatro.training.project;

public class Scrabble {
	
	public static void main(String [] args) {

		final String filename = "/home/sakshisuman/Documents/scrabble.conf";
		UserInput lc = new UserInput();
		AlphabetSource factory[]=lc.loadConfigurationFile(filename);
//		System.out.println(factory[0].factoryAlphabet);
		AlphabetRepository a = new AlphabetRepository();
		Thread at = new Thread(a,"A");
		at.start();

		for(int i=0;i<lc.numberOfSources;i++) {
			Thread ft = new Thread(factory[i],"f");
			ft.setDaemon(true);
			ft.setPriority(1);
			ft.start();		
		}
		String word;
		while(true) {
			word = lc.getUserInput();
			WordBuilder w = new WordBuilder(word);
		
			Thread wt = new Thread(w,"w");
			wt.start();
		}
	}
		
}
