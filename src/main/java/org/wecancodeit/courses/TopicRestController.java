package org.wecancodeit.courses;

import java.util.Optional;

import javax.annotation.Resource;
import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-topics")
public class TopicRestController {
	
	@Resource
	private CourseRepository courseRepo;
		
	@Resource
	private TopicRepository topicRepo;
	
	
	@RequestMapping("")
	public Iterable<Topic> findAllTopics() {
		return topicRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public Optional<Topic> findOneTopic(@PathVariable long id) { // using Optional here because of Id usage
		return topicRepo.findById(id);
	}
	
	// Find all topics in course
	@RequestMapping("/courses/{courseName}")
	public Collection<Topic> findAllTopicsInCourse(@PathVariable(value="courseName") String courseName){
		Course course = courseRepo.findByNameIgnoreCaseLike(courseName); // Create a Course object that matches the literal in the passed in "courseName"
		return topicRepo.findByCoursesContains(course); // Pull up all Topics that belong to the Course object (course)
	}
	
	/* Find all courses by topic */
	@RequestMapping("{topicName}/courses")
	public Collection<Course> findAllCoursesByTopic(@PathVariable(value="topicName") String topicName){
		Topic topic = topicRepo.findByNameIgnoreCaseLike(topicName); // find a Topic object whose name contains the string in "topicName"
		return courseRepo.findByTopicsContains(topic);	// Pull up all courses whose topics collection contains (topic)
	}
	

}
