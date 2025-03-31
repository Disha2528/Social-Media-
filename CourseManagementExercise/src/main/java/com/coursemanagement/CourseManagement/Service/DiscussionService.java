package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.DiscussionDTO;
import com.coursemanagement.CourseManagement.Entity.Discussion;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;

import java.util.List;

public interface DiscussionService {
    public Discussion createDiscussion(DiscussionDTO discussionDTO);

    public  List<Discussion> getDiscussions();

    public List<Discussion> getDiscussionsByCourseId(String CourseId) throws EntityNotFoundException;
}
