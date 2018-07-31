package org.wecancodeit.courses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.lang.String.format;


@Entity
public class Course {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	
	@JsonIgnore // helps with making sure that JSON objects don't repeat themselves in HTML Template
	@ManyToMany
	private Collection<Topic> topics;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course")
	private Collection<Textbook> textbooks;
	

	// Constructors
	protected Course() { } 
	
	public Course(String name, String description, Topic...topics) {
		this.name = name;
		this.description = description;
		this.topics = new HashSet<>(Arrays.asList(topics));
	}

	// Getters
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Collection<Topic> getTopics() {
		return topics;
	}
	
	public Collection<Textbook> getTextbooks() {
		return textbooks;
	}
	
	/*Rest Controller */
	// putting Topic URLS in Course JSON objects
	// tied with "topics" collection
	public Collection<String> getTopicsUrls(){
		Collection<String> urls = new ArrayList<>();
		
		// for each topic in our "topics" collection, find the id of the course, and get the URL of the topic (object)
		for (Topic t: topics) {	// tied into topics collection that is part of this entity
			
			urls.add(format("/courses/%d/topics/%s", this.getId(), t.getName().toLowerCase()));// format - %d is a number/digit based variable (int, long)
																							// %s is a String based variable
																							// variables being thrown in must be specified with commas in argument IN ORDER
		}
		
		return urls;
		
	}
	
	
	/* END Rest Controller */
	
	// hashCode() & equals() for entity id
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}




	

	
}
