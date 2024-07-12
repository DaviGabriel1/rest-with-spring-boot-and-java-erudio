package br.org.davi.unittests.mapper.mocks;

import br.org.davi.data.vo.v1.PersonVO;
import br.org.davi.model.Person;
import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity(){
        return mockEntity(0);
    }

    public PersonVO mockVO(){
        return mockVO(0);
    }

    public List<Person> mockEntityList(){
        List<Person> persons = new ArrayList<Person>();
        for(int i = 0;i< 14;i++){
            persons.add(mockEntity(i));
        }
        return persons;
    }
    public List<PersonVO> mockVOList(){
        List<PersonVO> persons = new ArrayList<PersonVO>();
        for(int i = 0;i< 14;i++){
            persons.add(mockVO(i));
        }
        return persons;
    }

    public Person mockEntity(Integer number){
        Person person = new Person();
        person.setAddress("Address test"+number);
        person.setFirstName("First name test"+number);
        person.setLastName("Test Last Name"+number);
        person.setId(number.longValue());
        person.setGender((number % 2 == 0) ? "Male" : "Female");
        return person;
    }
    public PersonVO mockVO(Integer number){
        PersonVO person = new PersonVO();
        person.setAddress("Address test"+number);
        person.setFirstName("First name test"+number);
        person.setLastName("Test Last Name"+number);
        person.setId(number.longValue());
        person.setGender((number % 2 == 0) ? "Male" : "Female");
        return person;
    }
}
