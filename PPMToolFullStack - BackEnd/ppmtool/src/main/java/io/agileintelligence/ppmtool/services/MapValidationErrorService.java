package io.agileintelligence.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//Reutilização de validação de erros para todas entidades
@Service
public class MapValidationErrorService {
    public ResponseEntity<?> mapValidationService(BindingResult result){
        if(result.hasErrors()){
            //Cria lista de erro vinda do BindingResult
            //Objetivo: criar lista json de erro com "campoObjeto":"Mensagem do NotBlank"
            //Não trata erro de informação repetida.
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null; //sem erro não tem nada a retornar
    }

}
