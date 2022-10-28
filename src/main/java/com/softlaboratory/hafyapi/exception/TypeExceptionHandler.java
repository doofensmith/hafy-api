package com.softlaboratory.hafyapi.exception;

import com.softlaboratory.hafyapi.controller.TypeController;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(assignableTypes = {TypeController.class})
public class TypeExceptionHandler {



}
