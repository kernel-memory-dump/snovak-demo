package com.demo.snovak.service.mapper;

import com.demo.snovak.domain.*;
import com.demo.snovak.service.dto.TopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Topic and its DTO TopicDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {

    @Mapping(source = "creator.id", target = "creatorId")
    TopicDTO toDto(Topic topic);

    @Mapping(source = "creatorId", target = "creator")
    Topic toEntity(TopicDTO topicDTO);

    default Topic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setId(id);
        return topic;
    }
}
