package com.pelatro.training.project;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WordRepository {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int wordId;
	
	private String word;
	
	private String status;
	
	private Timestamp time;
	
	
	
	public int getWordId() {
		return wordId;
	}



	public void setWordId(int wordId) {
		this.wordId = wordId;
	}



	public String getWord() {
		return word;
	}



	public void setWord(String word) {
		this.word = word;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Timestamp getTime() {
		return time;
	}



	public void setTime(Timestamp timeStamp) {
		this.time = timeStamp;
	}



	@Override
	public String toString() {
		return "Id :"+wordId+"  Word : "+word+"  Timestamp : "+time+"  Status : "+status;
	}
	
}
