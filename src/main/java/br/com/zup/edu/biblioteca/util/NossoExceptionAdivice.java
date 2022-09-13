package br.com.zup.edu.biblioteca.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class NossoExceptionAdivice {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<?> handler(MethodArgumentNotValidException ex){

        List<String> menssagens = new ArrayList<>();

        BindingResult bindingResult = ex.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError: fieldErrors){

            String msg = msgErro(fieldError.getField(), fieldError.getDefaultMessage());

            menssagens.add(msg);
        }

        return ResponseEntity.badRequest().body(menssagens);
    }

    private static String msgErro(String campo, String menssagem){

        return String.format("campo %s %s",campo,menssagem);
    }


}
