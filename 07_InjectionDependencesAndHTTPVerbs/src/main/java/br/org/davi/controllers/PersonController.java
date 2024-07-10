package br.org.davi.controllers;

import br.org.davi.model.Person;
import br.org.davi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person") //mapping a nivel de controller
public class PersonController{

    @Autowired //fará a instanciação do service em tempo de execução (private PersonService service = new PersonService())
    private PersonService service;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/)
    public Person findById(@PathVariable(value = "id") String id) throws Exception{
        return service.findById(id);
    }
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/)
    public List<Person> findAll(){
        return service.findAll();
    }
}
