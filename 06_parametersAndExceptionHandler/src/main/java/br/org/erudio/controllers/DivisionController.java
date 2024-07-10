package br.org.erudio.controllers;

import br.org.erudio.Pattern;
import br.org.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController extends Pattern {
    @RequestMapping(value = "division/{numberOne}/{numberTwo}",method = RequestMethod.GET)
    public Double division(@PathVariable(value = "numberOne") String numberOne,
                           @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set numeric value");
        }
        else if(numberTwo.equals("0")){
            throw new UnsupportedMathOperationException("the numberTwo cannot be 0");
        }
        return convertToDouble(numberOne)/convertToDouble(numberTwo);
    }
}
