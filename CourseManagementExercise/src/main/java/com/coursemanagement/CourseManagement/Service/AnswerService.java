package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Answer;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;

import java.util.List;

public interface AnswerService {
    //create
    public Answer createAnswer(AnswerDTO answerDTO) throws EntityNotFoundException;

    //retrieve
    public PaginatedResult<Answer> getAnswer(String discussionId, int limit, String lastEvaluatedKey) throws EntityNotFoundException;

    //update
    public Answer updateAnswer(AnswerDTO answerDTO) throws EntityNotFoundException;

    //delete
    public Answer deleteAnswer(String answerId) throws EntityNotFoundException;
}
