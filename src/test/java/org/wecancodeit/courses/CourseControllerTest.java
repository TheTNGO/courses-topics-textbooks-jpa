package org.wecancodeit.courses;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
public class CourseControllerTest {

	@InjectMocks
	private CourseController underTest;
	
	@Mock
	private Course course;
	
	@Mock
	private CourseRepository courseRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCourseToModel() {
		long arbitraryCourseId = 1;
		when(courseRepo.findById(arbitraryCourseId)).thenReturn(Optional.of(course));
		
		underTest.findOneCourse(arbitraryCourseId, model);
		verify(model).addAttribute("courses", course);
	}
	
	
}
