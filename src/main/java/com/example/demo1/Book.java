package com.example.demo1;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
@XmlRootElement
public class Book {

	String author;
	String title;

	public Book() {
		super();
		setAuthor("Alexej Milkau");
		setTitle("My first REST service with spring boot.");
	}

	public Book(String author, String title) {
		super();
		setAuthor(author);
		setTitle(title);
	}
}
