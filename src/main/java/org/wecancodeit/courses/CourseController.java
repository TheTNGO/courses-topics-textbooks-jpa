package org.wecancodeit.courses;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

	@Resource
	private CourseRepository courseRepo;
	
	@Resource
	private TopicRepository topicRepo;
	
	@Resource
	private TextbookRepository textbookRepo;
	
	
	@RequestMapping("/course")
	public String findOneCourse(@RequestParam(value="id")long courseId, Model model) throws CourseNotFoundException {
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(course.isPresent()) {
			model.addAttribute("courses", course.get());
			// model.addAttribute("textbooks",textbookRepo.findByCourseContains(course.get()));
			return "course";
		}
		throw new CourseNotFoundException();
	}

	@RequestMapping("/show-courses")
	public String findAllCourses(Model model) {
		model.addAttribute("courses", courseRepo.findAll());
		return ("courses");
	}

	@RequestMapping("/topic")
	public String findOneTopic(@RequestParam(value="id")long topicId, Model model) throws TopicNotFoundException {
		Optional<Topic> topic = topicRepo.findById(topicId);
		
		if(topic.isPresent()) {
			model.addAttribute("topics", topic.get());
			model.addAttribute("courses", courseRepo.findByTopicsContains(topic.get()));
			return "topic";
		}
		throw new TopicNotFoundException();
	}

	@RequestMapping("/show-topics")
	public String findAllTopics(Model model) {
		model.addAttribute("topics", topicRepo.findAll());
		return ("topics");	
	}

	@RequestMapping("/textbook")
	public String findOneTextBook(@RequestParam(value="id")long textBookId, Model model) throws TextBookNotFoundException {
		Optional<Textbook> textbook = textbookRepo.findById(textBookId);
		
		if(textbook.isPresent()) {
			model.addAttribute("textbooks", textbook.get());
			model.addAttribute("courses", courseRepo.findByTextbooksContains(textbook.get()));
			return ("textbook");
		}
		throw new TextBookNotFoundException();
	}
	
	@RequestMapping("show-textbooks")
	public String findAllBooks(Model model) {
		model.addAttribute("textbooks", textbookRepo.findAll());
		return ("textbooks");
	}

	@RequestMapping("/add-course")
	public String addCourse(String courseName, String courseDescription, String topicName) {
		Topic topic = topicRepo.findByName(topicName);
		Course newCourse = courseRepo.findByName(courseName);
		
		if(newCourse == null) {
			newCourse = new Course(courseName, courseDescription, topic);
			courseRepo.save(newCourse);
		}
		return "redirect:/show-courses";
	}

	@RequestMapping("/delete-course")
	public String deleteCourseByName(String courseName) {
		
		if(courseRepo.findByName(courseName) !=null) {
			Course deletedCourse = courseRepo.findByName(courseName);
			courseRepo.delete(deletedCourse);
		}
		return "redirect:/show-courses";
	}

	@RequestMapping("/del-course")
	public String deleteCourseById(Long courseId) {
		
		courseRepo.deleteById(courseId);

		return "redirect:/show-courses";
	}
	
	@RequestMapping("/find-by-topic")
	public String findCoursesByTopic(String topicName, Model model) {
		Topic topic = topicRepo.findByName(topicName);
		model.addAttribute("courses", courseRepo.findByTopicsContains(topic));
		
		return "/topic";
	}

	@RequestMapping("/sort-courses")
	public String sortCourses(Model model) {
		model.addAttribute("courses", courseRepo.findAllByOrderByNameAsc());
		
		return "courses";
	}
	
}
