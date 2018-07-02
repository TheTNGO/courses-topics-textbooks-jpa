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
		Course 
	}
	
	
}
