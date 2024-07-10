package br.org.davi.services;

import br.org.davi.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all Persons");
        List<Person> persons = new ArrayList<>();
        for(int i = 0;i<8;i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id){
        logger.info("Finding one Person");
        Person person = new Person(); //Mock
        person.setId(counter.incrementAndGet());
        person.setFirstName("Davi");
        person.setLastName("Santos");
        person.setAdress("Itapevi - São Paulo - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person){
        logger.info("creating one person!");
        return person;
    }
    public Person update(Person person){
        logger.info("updating one person!");
        return person;
    }
    public void delete(String id){
        logger.info("deleting one person!");
    }

    private Person mockPerson(int i) {
        Person person = new Person(); //Mock
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name "+ i);
        person.setLastName("Last name "+ i);
        person.setAdress("Some adress in Brasil "+ i);
        person.setGender("Male");
        return person;
    }
}
