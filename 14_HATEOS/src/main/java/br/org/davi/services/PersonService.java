package br.org.davi.services;


import br.org.davi.controllers.PersonController;
import br.org.davi.data.vo.v1.PersonVO;
import br.org.davi.exceptions.RequiredObjectIsNullException;
import br.org.davi.exceptions.ResourceNotFoundException;
import br.org.davi.mapper.DozerMapper;
import br.org.davi.model.Person;
import br.org.davi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Finding all persons");
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return persons;
    }

    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one person");

        var entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        PersonVO vo = DozerMapper.parseObject(entity,PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel()); //withselfrel: auto relacionamento (endereço para si mesmo)
        return vo;
    }

    public PersonVO create(PersonVO person) throws Exception {

        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("creating one person!");
        var entity = DozerMapper.parseObject(person,Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class); //salva e retorna novamente o VO
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()); //withselfrel: auto relacionamento (endereço para si mesmo)
        return vo;
    }
    public PersonVO update(PersonVO person) throws Exception {

        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("updating one person!");
        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()); //withselfrel: auto relacionamento (endereço para si mesmo)
        return vo;
    }
    public void delete(Long id){
        logger.info("deleting one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
