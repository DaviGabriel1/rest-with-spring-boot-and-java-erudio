package br.org.davi.controllers;

import br.org.davi.data.vo.v1.BookVO;
import br.org.davi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import  br.org.davi.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public BookVO findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public List<BookVO> findAll(){
        return service.findAll();
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public BookVO create(@RequestBody BookVO book){
        return service.create(book);
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public BookVO update(@RequestBody BookVO book){
        return service.update(book);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
