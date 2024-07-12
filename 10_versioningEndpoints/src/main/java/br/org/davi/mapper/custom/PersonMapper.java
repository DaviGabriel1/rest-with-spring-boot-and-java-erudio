package br.org.davi.mapper.custom;

import br.org.davi.data.vo.v2.PersonVOV2;
import br.org.davi.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 entity = new PersonVOV2();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setBirthday(new Date());
        entity.setGender(person.getGender());
        return entity;
    }
    public Person convertVoToEntity(PersonVOV2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        //entity.setBirthday(new Date());
        entity.setGender(person.getGender());
        return entity;
    }
}
