package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.Entity.Answer;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;

import java.util.List;

public interface AnswerService {
    //create
    public Answer createAnswer(AnswerDTO answerDTO);

    //retrieve
    public List<Answer> getAnswer(String discussionId) throws EntityNotFoundException;

    //update
    public Answer updateAnswer(AnswerDTO answerDTO) throws EntityNotFoundException;

    //delete
    public Answer deleteAnswer(String answerId) throws EntityNotFoundException;
}
