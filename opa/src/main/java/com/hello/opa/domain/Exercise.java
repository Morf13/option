package com.hello.opa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;




@Entity
@Table(name = "exercises")

public class Exercise {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank(message = "Please add the Title") 
	@Length(max = 255, message = "Title too long")
	
	private String title;
	//@NotBlank(message = "Please add Excel file") 
	private String fileName;
	
	@NotBlank(message = "Please choose Exercise type")
	private String typeOfTask;
	
	public String getType() {
		return typeOfTask;
	}

	public void setType(String type) {
		this.typeOfTask = type;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User author;
	
	public Exercise() {
		
	}
	
	
	
	public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }


	public Exercise(String title, User author) {
		this.title = title;
		this.author = author;
		
	}

	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
