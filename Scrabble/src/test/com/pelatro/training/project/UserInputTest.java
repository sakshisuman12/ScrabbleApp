package test.com.pelatro.training.project;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.pelatro.training.project.*;
//import com.pelatro.training.project.UserInput;


class UserInputTest {
	
	ClientHandler ch = null;
	Hashtable<Character, Integer> ht = new Hashtable<>();
	AlphabetRepository ar = null;
	WordBuilder w = null;
	UserInput ip = new UserInput();
	AlphabetSource [] factory = null;
	String filename;

	@Test
	void testNullFactoryObjects() {
		factory=ip.loadConfigurationFile(null);
		Assert.assertNull(factory);
	}
	
	@Test
	void testAlphabetList() {
		filename = "/home/sakshisuman/Documents/scrabble.conf";
		factory = ip.loadConfigurationFile(filename);
		List<Character> factoryAlphabet = new ArrayList<Character>();
		factoryAlphabet.add('a');
		factoryAlphabet.add('b');
		factoryAlphabet.add('c');
		factoryAlphabet.add('d');
		factoryAlphabet.add('e');
		factoryAlphabet.add('f');
		factoryAlphabet.add('g');
		factoryAlphabet.add('h');

		Assert.assertEquals(factoryAlphabet, factory[0].factoryAlphabet);
	}
	
	@Test
	void fileExists() {
		filename = "/home/sakshisuman/Doc/s.conf";
		factory = ip.loadConfigurationFile(filename);
		Assert.assertTrue(ip.flag);
	}
		
	@Test
	void testNumberOfFactories() {
		filename = "/home/sakshisuman/Documents/scrabble.conf";
		factory = ip.loadConfigurationFile(filename);
		Assert.assertEquals(ip.numberOfSources, factory.length);
	}
	
	@Test
	void testRate() {
		filename = "/home/sakshisuman/Documents/scrabble.conf";
		factory = ip.loadConfigurationFile(filename);
		Assert.assertEquals(1,factory[0].getRate());
	}
	
	@Test
	void testWordInput() {
		String word = ip.getUserInput();
		Assert.assertNotNull(word);
		w= new WordBuilder(word);
		Assert.assertEquals(word, w.word);
	}
	
	@Test
	void testHandlerForFactoryClient() {
		ar = new AlphabetRepository();
		ch = new ClientHandler("#a",ar);
		ht.put('a', 1);
		ch.run();
		Assert.assertEquals(ht, ar.hm);
	}
	
	@Test
	void testHandlerForWordClient() {
		ar = new AlphabetRepository();
		ch = new ClientHandler("#a",ar);
		ch.run();
		ClientHandler c = new ClientHandler("#a",ar);
		c.run();
		ClientHandler h = new ClientHandler("$cat",ar);
		h.run();
//		ht.put('c', 1);
		ht.put('a', 2);
//		ht.put('t', 1);
		Assert.assertEquals(ht, ar.hm);
		
	}
	
	@Test
	void testHandlerForWordUpdate() {
		ar = new AlphabetRepository();
		ch = new ClientHandler("#a",ar);
		ch.run();
		ClientHandler c = new ClientHandler("#t",ar);
		c.run();
		ClientHandler h = new ClientHandler("$a",ar);
		h.run();
		ht.put('t', 1);
		Assert.assertEquals(ht, ar.hm);
		
	}

}
