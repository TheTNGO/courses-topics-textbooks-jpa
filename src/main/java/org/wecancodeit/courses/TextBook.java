package org.wecancodeit.courses;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Textbook {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	
	@JsonIgnore // helps with making sure that JSON objects don't repeat themselves in HTML Template
	@ManyToOne
	private Course course;

	
	// Constructors
	protected Textbook() {}
	
	public Textbook(String title, Course course) {
		this.title = title;
		this.course = course;
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Textbook other = (Textbook) obj;
		if (id != other.id)
			return false;
		return true;
	}


	

}
