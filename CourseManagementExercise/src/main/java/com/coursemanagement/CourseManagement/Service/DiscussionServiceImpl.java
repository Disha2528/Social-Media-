package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.DiscussionDTO;
import com.coursemanagement.CourseManagement.Entity.Course;
import com.coursemanagement.CourseManagement.Entity.Discussion;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Repository.CourseRepo;
import com.coursemanagement.CourseManagement.Repository.DiscussionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscussionServiceImpl implements DiscussionService{

    @Autowired
    private DiscussionRepo discussionRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public Discussion createDiscussion(DiscussionDTO discussionDTO){
        return discussionRepo.createDiscussion(discussionDTO);
    }

    @Override
    public List<Discussion> getDiscussions(){
        return discussionRepo.getDiscussions();
    }

    @Override
    public List<Discussion> getDiscussionsByCourseId(String courseId) throws EntityNotFoundException {

        Course course = courseRepo.getCourseById(courseId);
        if(course==null) throw new EntityNotFoundException("Course not found");
        List<Discussion> discussions= discussionRepo.getDiscussionsByCourseId(courseId);

        return discussions;
    }



}
