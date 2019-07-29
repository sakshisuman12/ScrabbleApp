package com.pelatro.training.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput{
	public boolean flag = false;
	public int numberOfSources=0;
	
	@SuppressWarnings("resource")
	public String getUserInput() {
		System.out.println("Enter the word");
		Scanner sc = new Scanner(System.in);
		return sc.next().toString();
	}
	
	public AlphabetSource[] loadConfigurationFile(String filename) {
		
		int i=1;
		this.flag = false;
		int rate;
		String data[]=new String[3];
		String line;
		List<Character> factoryAlphabet = new ArrayList<Character>();
		AlphabetSource [] factory=null;
		try {
			@SuppressWarnings("resource")
			
			BufferedReader br = new BufferedReader(new FileReader(filename));
			data = br.readLine().split(":");
			this.numberOfSources = Integer.parseInt(data[1]);
			factory=new AlphabetSource[this.numberOfSources];
			while((line=br.readLine())!=null) {
				factoryAlphabet.clear();
				if(i>0 && i<=(numberOfSources)) {
					System.out.println(i);
					data = line.split(":");
//					for(String d:data)
//						System.out.println(d);
					char chars[] = data[1].toCharArray();
					for (char c : chars) {
				        factoryAlphabet.add(c);
					}
					rate = Integer.parseInt(data[2]);
//					System.out.println(rate);
//					System.out.println(i-2);
					factory[i-1] = new AlphabetSource(rate,factoryAlphabet);
//					System.out.println(factory[i-2].factoryAlphabet);
				}
				i++;
			}
			return factory;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			this.flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}

