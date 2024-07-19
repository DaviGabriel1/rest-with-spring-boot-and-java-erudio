package br.org.davi.services;

import br.org.davi.controllers.BookController;
import br.org.davi.data.vo.v1.BookVO;
import br.org.davi.exceptions.RequiredObjectIsNullException;
import br.org.davi.exceptions.ResourceNotFoundException;
import br.org.davi.mapper.DozerMapper;
import br.org.davi.model.Books;
import br.org.davi.repositories.BookRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public BookVO findById(Long id){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        BookVO vo = DozerMapper.parseObject(entity,BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }
    public List<BookVO> findAll(){
        var entities = repository.findAll();
        List<BookVO> voList = DozerMapper.parseListObjects(entities, BookVO.class);
        for(BookVO vo : voList){
            vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        }
        return voList;
    }
    public BookVO create(BookVO bookVO){
        if(bookVO == null) throw new RequiredObjectIsNullException("It is not allowed to persist a null object!");
        Books book = DozerMapper.parseObject(bookVO,Books.class);
        BookVO vo = DozerMapper.parseObject(repository.save(book),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(bookVO.getId())).withSelfRel());
        return vo;
    }
    public BookVO update(BookVO book){
        if(book == null) throw new RequiredObjectIsNullException("It is not allowed to persist a null object!");
        var entity = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("book not found"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        BookVO vo = DozerMapper.parseObject(repository.save(entity),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
        return vo;
    }

    public void delete(Long id ){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("records not found for this id"));
        repository.delete(entity);
    }
}
