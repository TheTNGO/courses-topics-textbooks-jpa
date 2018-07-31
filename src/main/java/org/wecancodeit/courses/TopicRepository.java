package org.wecancodeit.courses;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
	
	// creation driven by
	// shouldAddAdditionalCoursesToModel()
	Topic findByName(String topicName);

	Topic findByNameIgnoreCaseLike(String topicName); //Ignores letter-case in URL
	
	//Rest Controller
	Collection<Topic> findByCoursesContains(Course course);

}
