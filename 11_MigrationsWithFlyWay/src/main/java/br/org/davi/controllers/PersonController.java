package br.org.davi.controllers;

import br.org.davi.data.vo.v1.PersonVO;
import br.org.davi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/person/v1") //mapping a nivel de controller
public class PersonController{

    @Autowired //fará a instanciação do service em tempo de execução (private PersonService service = new PersonService())
    private PersonService service;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/)
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return service.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/)
    public List<PersonVO> findAll(){
        return service.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/,
            consumes = MediaType.APPLICATION_JSON_VALUE/*indica que consome json*/)
    public PersonVO create(@RequestBody PersonVO person) throws Exception{
        return service.create(person);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE/*indica que produz json*/,
            consumes = MediaType.APPLICATION_JSON_VALUE/*indica que consome json*/)
    public PersonVO update(@RequestBody PersonVO person) throws Exception{
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //retorna o status code 204 no content, ideal para delete
    }

}
