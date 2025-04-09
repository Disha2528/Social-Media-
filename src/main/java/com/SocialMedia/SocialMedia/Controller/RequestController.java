package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.DTO.RequestDTO;
import com.SocialMedia.SocialMedia.Entities.Request;
import com.SocialMedia.SocialMedia.Exceptions.EntityAlreadyExistsException;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Service.RequestService;
import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.MessageUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.SocialMedia.SocialMedia.Util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    //works
    @PostMapping("/send")
    public ResponseEntity<ResponseHandler> sendRequest(@RequestBody @Validated(OnCreate.class) RequestDTO requestDTO){
        try{
            RequestDTO request= requestService.sendRequest(requestDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("request.send.success"), HttpStatus.OK.value(), true, "Request",request);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Request", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch(EntityAlreadyExistsException ex){
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.CONFLICT.value(), false, "Request", null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Request",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @GetMapping("/viewRequests")
    public ResponseEntity<ResponseHandler> viewRequests(@RequestParam(defaultValue = "3") int limit, @RequestParam(required = false) String lastEvaluatedKey){
        try{
            PaginatedResult<Request> requests= requestService.viewRequests(limit, lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("request.get.success"), HttpStatus.OK.value(), true, "Request",requests);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Request",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/withdrawRequest/{receiverId}")
    public ResponseEntity<ResponseHandler> withdrawRequest(@PathVariable @Valid String receiverId){
        try{
            RequestDTO request= requestService.withdrawRequest(receiverId);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("request.withdraw.success"), HttpStatus.OK.value(), true, "Request",request);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Comment", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Request",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/resolve/{senderId}")
    public ResponseEntity<ResponseHandler> resolveRequest(
            @PathVariable @Valid String senderId,
            @RequestParam Status status) {
        try {
            RequestDTO resolvedRequest = requestService.resolveRequest(senderId, status);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("request.resolve.success"), HttpStatus.OK.value(), true, "Request", resolvedRequest);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Comment", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseHandler response = new ResponseHandler(
                    e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Request", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
