package com.coursemanagement.CourseManagement.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coursemanagement.CourseManagement.DTO.CourseDTO;
import com.coursemanagement.CourseManagement.Entity.Course;
import com.coursemanagement.CourseManagement.Entity.User;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Repository.UserRepo;
import com.coursemanagement.CourseManagement.Repository.CourseRepo;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthUtil authUtil;

    // Create course
    @Override
    public Course addCourse(CourseDTO courseDTO) throws AccessDeniedException {

        User user= userRepo.getUserByemail(authUtil.currentUser());
        courseDTO.setInstructorId(user.getUserId());
        return courseRepo.addCourse(courseDTO);

    }

    // Get all courses
    @Override
    public List<Course> getCourses() throws AccessDeniedException {
        return courseRepo.getCourses();
    }

    // Get courses by instructor
    @Override
    public List<Course> courseByInstructor(String instructorId) throws AccessDeniedException, EntityNotFoundException {


        User user = userRepo.getUserById(instructorId);
        if(user==null) throw new EntityNotFoundException("Instructor Not Found");

        return courseRepo.courseByInstructor(instructorId);
    }

    // Update course
    @Override
    public Course updateCourse(CourseDTO courseDTO) throws AccessDeniedException, EntityNotFoundException {

        Course course = courseRepo.getCourseById(courseDTO.getCourseId());
        if(course==null) throw new EntityNotFoundException("Course Not Found");

        modelMapper.map(courseDTO, course);
        dynamoDBMapper.save(course);

        return course;
    }

    // Delete course
    @Override
    public Course deleteCourse(String id) throws AccessDeniedException, EntityNotFoundException {
        Course course= courseRepo.getCourseById(id);
        if(course==null) throw new EntityNotFoundException("Course Not Found");

        courseRepo.delete(id);
        return course;
    }
}
