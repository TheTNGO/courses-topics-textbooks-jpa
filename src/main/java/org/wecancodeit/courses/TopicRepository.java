package org.wecancodeit.courses;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {

	Topic findByName(String topicName);

	Topic findByNameIgnoreCaseLike(String topicName);


}
