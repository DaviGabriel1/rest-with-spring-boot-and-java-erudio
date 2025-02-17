package br.org.davi.controllers;

import br.org.davi.model.Person;
import br.org.davi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/,
            consumes = MediaType.APPLICATION_JSON_VALUE/*indica que consome json*/)
    public Person create(@RequestBody Person person) throws Exception{
        return service.create(person);
    }

    @RequestMapping(method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/,
            consumes = MediaType.APPLICATION_JSON_VALUE/*indica que consome json*/)
    public Person update(@RequestBody Person person) throws Exception{
        return service.update(person);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable(value = "id") String id){
        service.delete(id);
    }

}
