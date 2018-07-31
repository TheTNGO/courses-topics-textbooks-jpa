package org.wecancodeit.courses;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

	Collection<Course> findByTopicsContains(Topic topic);

	Collection<Course> findByTopicsId(Long id);
	
	Collection<Course> findByTextbooksContains(Textbook textbook);
	
	Collection<Course> findByTextbooksId(Long id);

	/* Forms */
	
	Course findByName(String courseName);

	Collection<Course> findAllByOrderByNameAsc();

	Course findByNameIgnoreCaseLike(String courseName);

}
