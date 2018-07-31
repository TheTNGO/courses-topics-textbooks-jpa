package org.wecancodeit.courses;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest Controllers are used to help with transferring JSON objects from here (Java backend) to the front-end (Site JS)
// Viewed with HAL Browser

@RestController // signifies that this is a Controller that can handle JSON objects
@RequestMapping("/courses") // all end-points referred to by this RequestMapping go through /courses first
public class CourseRestController {

	@Resource
	private CourseRepository courseRepo;

	@Resource
	private TopicRepository topicRepo;

	// Use HAL Browser to view all entities/JSON tied in with this RequestMapping
	// shows all courses found with courseRepo.findAll();
	// why Iterable?
	@RequestMapping("") // /courses defaults to here first
	public Iterable<Course> findAllCourses() {
		return courseRepo.findAll();
	}

	// (?) {id} is JSON notation?
	// {id} is a path variable
	// Noted with @PathVariable in constructor
	// should just pull up JSON info for course in question
	@RequestMapping("/{id}")
	public Optional<Course> findOneCourse(@PathVariable long id) { // using Optional here because of Id usage
		return courseRepo.findById(id);
	}
	
	/* Find all courses by topic */
	@RequestMapping("/topics/{topicName}")
	public Collection<Course> findAllCoursesByTopic(@PathVariable(value="topicName") String topicName){
		Topic topic = topicRepo.findByNameIgnoreCaseLike(topicName); // find a Topic object whose name contains the string in "topicName"
		return courseRepo.findByTopicsContains(topic);	// Pull up all courses whose topics collection contains (topic)
	}

	
}
