package br.org.erudio.controllers;

import br.org.erudio.Pattern;
import br.org.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtractionController extends Pattern {
    @RequestMapping(value = "subtraction/{numberOne}/{numberTwo}",method = RequestMethod.GET)
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne,
                              @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set numeric value");
        }
        return convertToDouble(numberOne)-convertToDouble(numberTwo);
    }
}
