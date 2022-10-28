package com.softlaboratory.hafyapi.exception;

import com.softlaboratory.hafyapi.controller.RolesController;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Log4j2
@ControllerAdvice(assignableTypes = {RolesController.class})
public class RolesExceptionHandler {



}
