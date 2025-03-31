package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Answer;
import com.coursemanagement.CourseManagement.Entity.User;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Repository.AnswerRepo;
import com.coursemanagement.CourseManagement.Repository.UserRepo;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthUtil authUtil;

    //create
    @Override
    public Answer createAnswer(AnswerDTO answerDTO) throws EntityNotFoundException {

        User user= userRepo.getUserByemail(authUtil.currentUser());
        answerDTO.setResponderId(user.getUserId());
        return answerRepo.createAnswer(answerDTO);
    }

    //retrieve
    @Override
    public PaginatedResult<Answer> getAnswer(String discussionId, int limit, String lastEvaluatedKey) throws EntityNotFoundException {
        PaginatedResult<Answer> answers= answerRepo.getAnswer(discussionId,limit,lastEvaluatedKey);
        if(answers==null) throw new EntityNotFoundException("No Answers Exist for this discussion");

        return answers;
    }

    //update
    @Override
    public Answer updateAnswer(AnswerDTO answerDTO) throws EntityNotFoundException {
        Answer answer= answerRepo.getAnswerById(answerDTO.getAnswerId());

        if(answer.getResponderId()!=userRepo.getUserByemail(authUtil.currentUser()).getUserId() || answer==null) throw new EntityNotFoundException("You have no Answer with answerId: "+ answerDTO.getAnswerId());

        return answerRepo.updateAnswer(answerDTO);
    }

    //delete
    @Override
    public Answer deleteAnswer(String answerId) throws EntityNotFoundException {
        Answer answer= answerRepo.getAnswerById(answerId);

        if(answer==null || answer.getResponderId()!=userRepo.getUserByemail(authUtil.currentUser()).getUserId()) throw new EntityNotFoundException("No such answer found");

        return answerRepo.deleteAnswer(answerId);
    }
}
