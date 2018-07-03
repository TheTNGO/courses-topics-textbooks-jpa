package org.wecancodeit.courses;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

public interface TextbookRepository extends CrudRepository<Textbook, Long> {
	
	Collection<Textbook> findByCourseContains(Course course);
	
}
