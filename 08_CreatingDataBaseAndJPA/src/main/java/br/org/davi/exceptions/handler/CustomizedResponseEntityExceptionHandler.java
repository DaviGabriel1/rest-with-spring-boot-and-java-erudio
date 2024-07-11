package br.org.davi.exceptions.handler;

import br.org.davi.exceptions.ExceptionResponse;
import br.org.davi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) //trata exceções genericas
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e,
                                                                       WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),e.getMessage()
        , request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class) //em casos como um queryparam mal feito
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception e,
                                                                       WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),e.getMessage()
                , request.getDescription(false)); // informações da exceção
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
