package org.wecancodeit.courses;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CoursePopulator implements CommandLineRunner{
	
	@Resource
	private CourseRepository courseRepo;
	
	@Resource
	private TopicRepository topicRepo;
	
	@Resource
	private TextbookRepository textbookRepo;

	@Override
	public void run(String... args) throws Exception {
		// Create Topics
		Topic java = new Topic("Java");
		java = topicRepo.save(java);
		Topic spring = new Topic("Spring");
		spring = topicRepo.save(spring);
		Topic tdd = new Topic("TDD");
		tdd = topicRepo.save(tdd);
		
		// Create courses
		Course java101 = new Course("Intro to Java", "Learn the fundaments of Java Development", java);
		java101 = courseRepo.save(java101);
		Course java102 = new Course("Advanced Software Design", "Advanced Java Techniques",
				java, tdd);
		java102 = courseRepo.save(java102);
		
		// Create Textbooks
		textbookRepo.save(new Textbook("Head First Java", java101));
		textbookRepo.save(new Textbook("Head First Design Patterns", java102));
		textbookRepo.save(new Textbook("Clean Code", java102));
		textbookRepo.save(new Textbook("Intro to JPA", java102));
		
	}
	
	
}
