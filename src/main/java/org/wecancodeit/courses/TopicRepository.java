package org.wecancodeit.courses;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
	
	// creation driven by
	// shouldAddAdditionalCoursesToModel()
	Topic findByName(String topicName);

}
