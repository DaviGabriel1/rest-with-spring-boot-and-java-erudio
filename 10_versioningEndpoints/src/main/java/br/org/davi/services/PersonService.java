package br.org.davi.services;


import br.org.davi.data.vo.v1.PersonVO;
import br.org.davi.data.vo.v2.PersonVOV2;
import br.org.davi.exceptions.ResourceNotFoundException;
import br.org.davi.mapper.DozerMapper;
import br.org.davi.mapper.custom.PersonMapper;
import br.org.davi.model.Person;
import br.org.davi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll(){
        logger.info("Finding all persons");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){
        logger.info("Finding one person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("creating one person!");
        var entity = DozerMapper.parseObject(person,Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class); //salva e retorna novamente o VO
        return vo;
    }
    public PersonVOV2 createV2(PersonVOV2 person){
        logger.info("creating one person with V2!");
        var entity = mapper.convertVoToEntity(person);
        var vo =mapper.convertEntityToVO(repository.save(entity));
        return vo;
    }
    public PersonVO update(PersonVO person){
        logger.info("updating one person!");
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }
    public void delete(Long id){
        logger.info("deleting one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
