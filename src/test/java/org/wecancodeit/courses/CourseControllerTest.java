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
}
