package br.org.erudio.controllers;

import br.org.erudio.Pattern;
import br.org.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquareRootController extends Pattern {
    @RequestMapping(value = "squareroot/{numberOne}",method = RequestMethod.GET)
    public Double squareRoot(@PathVariable(value = "numberOne") String numberOne) throws Exception{
        if(!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please set numeric value");
        }
        return Math.sqrt(convertToDouble(numberOne));
    }
}
