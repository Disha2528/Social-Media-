package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.Entity.Answer;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Repository.AnswerRepo;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepo answerRepo;

    //create
    @Override
    public Answer createAnswer(AnswerDTO answerDTO){

        return answerRepo.createAnswer(answerDTO);
    }

    //retrieve
    @Override
    public List<Answer> getAnswer(String discussionId) throws EntityNotFoundException {
        List<Answer> answers= answerRepo.getAnswer(discussionId);
        if(answers==null) throw new EntityNotFoundException("No Answers Exist for this discussion");

        return answers;
    }

    //update
    @Override
    public Answer updateAnswer(AnswerDTO answerDTO) throws EntityNotFoundException {
        Answer answer= answerRepo.getAnswerById(answerDTO.getAnswerId());

        if(answer==null) throw new EntityNotFoundException("No such answer found");

        return answerRepo.updateAnswer(answerDTO);
    }

    //delete
    @Override
    public Answer deleteAnswer(String answerId) throws EntityNotFoundException {
        Answer answer= answerRepo.getAnswerById(answerId);

        if(answer==null) throw new EntityNotFoundException("No such answer found");

        return answerRepo.deleteAnswer(answerId);
    }
}
