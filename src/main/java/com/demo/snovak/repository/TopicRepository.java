package com.demo.snovak.repository;

import com.demo.snovak.domain.Topic;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("select topic from Topic topic where topic.creator.login = ?#{principal.username}")
    List<Topic> findByCreatorIsCurrentUser();

}
