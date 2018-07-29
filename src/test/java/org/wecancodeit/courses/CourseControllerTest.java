package org.wecancodeit.courses;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CourseControllerTest {

	@InjectMocks
	private CourseController underTest;

	@Mock
	private Course course;
	Long courseId;

	@Mock
	private Course anotherCourse;

	@Mock
	private CourseRepository courseRepo;

	@Mock
	private Topic topic;

	@Mock
	private Topic anotherTopic;

	@Mock
	private TopicRepository topicRepo;

	@Mock
	private Textbook book;

	@Mock
	private Textbook anotherBook;

	@Mock
	private TextbookRepository textbookRepo;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleCourseToModel() throws CourseNotFoundException {
		long arbitraryCourseId = 1;
		when(courseRepo.findById(arbitraryCourseId)).thenReturn(Optional.of(course));

		underTest.findOneCourse(arbitraryCourseId, model);
		verify(model).addAttribute("courses", course);
	}

	@Test
	public void shouldAddAllCoursesToModel() {
		Collection<Course> allCourses = Arrays.asList(course, anotherCourse);
		when(courseRepo.findAll()).thenReturn(allCourses);

		underTest.findAllCourses(model);
		verify(model).addAttribute("courses", allCourses);
	}

	@Test
	public void shouldAddSingleTopicToModel() throws TopicNotFoundException {
		long arbitraryTopicId = 1;
		when(topicRepo.findById(arbitraryTopicId)).thenReturn(Optional.of(topic));

		underTest.findOneTopic(arbitraryTopicId, model);
		verify(model).addAttribute("topics", topic);
	}

	@Test
	public void shouldAddAllTopicsToModel() {
		Collection<Topic> allTopics = Arrays.asList(topic, anotherTopic);
		when(topicRepo.findAll()).thenReturn(allTopics);

		underTest.findAllTopics(model);
		verify(model).addAttribute("topics", allTopics);
	}

	@Test
	public void shouldAddSingleTextBookToModel() throws TextBookNotFoundException {
		long arbitraryTextBookId = 1;
		when(textbookRepo.findById(arbitraryTextBookId)).thenReturn(Optional.of(book));

		underTest.findOneTextBook(arbitraryTextBookId, model);
		verify(model).addAttribute("textbooks", book);
	}

	@Test
	public void shouldAllAllTextBooksToModel() {
		Collection<Textbook> allBooks = Arrays.asList(book, anotherBook);
		when(textbookRepo.findAll()).thenReturn(allBooks);

		underTest.findAllBooks(model);
		verify(model).addAttribute("textbooks", allBooks);
	}
	
	
	/* Forms */
	
	// Creating capability of finding topic objects by their name.
	// Used to allow end-user to type in course name, category, and topic through a form in "show-courses"
	// Did not use mock objects because we wanted to test this using live circumstances
	@Test
	public void shouldAddAdditionalCoursesToModel() {

		// Variables (topicName, courseName, courseDescription) are ones required to be
		// filled out by end-user
		// that will allow new courses to be added to the courses.html template by the
		// end-user

		// initialized variable with a String literal in it
		String topicName = "topic name";
		// drove query creation of topicRepo.findByName() in TopicRepository class
		// simultaneously creates a new topic with that name
		Topic newTopic = topicRepo.findByName(topicName);

		String courseName = "new course";
		String courseDescription = "new course description";

		underTest.addCourse(courseName, courseDescription, topicName); // tied to form/end-user additions.
																		// user will add in (as Strings):
																		// courseName - String
																		// courseDescription - String
																		// topicName - String

		Course newCourse = new Course(courseName, courseDescription, newTopic); // course that is created based off of
																				// the controller (underTest)
																				// calling .addCourse([params]);

		when(courseRepo.save(newCourse)).thenReturn(newCourse);

	}
	
	// using mocks again
	// used to allow end-user to remove courses in "show-courses" by typing its name into a form
	@Test
	public void shouldRemoveCourseFromModelByName() {
		String courseName = course.getName();
		when(courseRepo.findByName(courseName)).thenReturn(course);
		underTest.deleteCourseByName(courseName);
		verify(courseRepo).delete(course); // look this up
											// mocks are typically tested with "verify"
											// verifies that the couresRepo actually deleted the course specified
		
	}
	
	// Removing from IDs allows us to grab the info of the single Course we are currently viewing
	// and remove it from the database without going back to "show-courses"
	
	@Test
	public void shouldRemoveCourseFromModelByID() {
		underTest.deleteCourseById(courseId);
		verify(courseRepo).deleteById(courseId);
		
	}
	
}
