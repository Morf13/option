package com.hello.opa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")

public class Exercise {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	private String title;
	private String task;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User author;
	private String answer;
	
	public Exercise() {
		
	}
	
	
	
	public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }


	public Exercise(String title, String task, User author, String answer) {
		this.title = title;
		this.task = task;
		this.author = author;
		this.answer = answer;
	}

	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
