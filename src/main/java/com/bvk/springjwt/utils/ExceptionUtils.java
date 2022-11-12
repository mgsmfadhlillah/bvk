package com.bvk.springjwt.utils;

import com.bvk.springjwt.logger.Debug;
import com.bvk.springjwt.payload.response.MessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtils {
  public static ResponseEntity<Object> translate(Exception ex) throws JsonProcessingException {
    MessageResponse response = new MessageResponse();
    String json = "";
    switch (ex.getClass().getSimpleName()) {
      case "DataIntegrityViolationException":
      case "SQLIntegrityConstraintViolationException":
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setMessage("The Data is Duplicate");
        Debug.info("The Data is Duplicate >> "+ex.getMessage());
        break;
      case "NullPointerException":
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("It's Null Pointer");
        Debug.info("It's Null Pointer >> "+ex.getMessage());
        break;
      default:
        Debug.info("Exception Type : " + ex.getClass().getName());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Something strange, please check your log for stack trace");
        Debug.info("Please check your log for stack trace >> "+ex.getMessage());
        break;
    }
    Debug.info(ex.getCause().toString());
    json = new ObjectMapper().writeValueAsString(response);
    Debug.info("Exception Responses : "+json);

    return new ResponseEntity<Object>(json, HttpStatus.valueOf(response.getStatus()));
  }
}