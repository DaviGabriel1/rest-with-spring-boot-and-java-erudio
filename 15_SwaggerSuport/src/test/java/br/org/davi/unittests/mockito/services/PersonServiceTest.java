package br.org.davi.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import br.org.davi.data.vo.v1.PersonVO;
import br.org.davi.exceptions.RequiredObjectIsNullException;
import br.org.davi.model.Person;
import br.org.davi.repositories.PersonRepository;
import br.org.davi.services.PersonService;
import br.org.davi.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks //injetando um mock para acessar o repository
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    public void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAll() throws Exception {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14,people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        System.out.println(personOne.toString());
        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1",personOne.getAddress());
        assertEquals("First Name Test1",personOne.getFirstName());
        assertEquals("Last Name Test1",personOne.getLastName());
        assertEquals("Female",personOne.getGender());

        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        System.out.println(personFour.toString());
        assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Addres Test4",personFour.getAddress());
        assertEquals("First Name Test4",personFour.getFirstName());
        assertEquals("Last Name Test4",personFour.getLastName());
        assertEquals("Male",personFour.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        System.out.println(personSeven.toString());
        assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Addres Test7",personSeven.getAddress());
        assertEquals("First Name Test7",personSeven.getFirstName());
        assertEquals("Last Name Test7",personSeven.getLastName());
        assertEquals("Female",personSeven.getGender());
    }
    @Test
    public void findById() throws Exception {
        Person entity = input.mockEntity();
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());

    }
    @Test
    public void create() throws Exception {
        Person entity = input.mockEntity();

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }
    @Test
    public void testCreateWithNullPerson() throws Exception {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> {
            service.create(null);
        });
        String expectedMessage = "it is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void update() throws Exception {
        Person entity = input.mockEntity();
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }
    @Test
    public void testUpdateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> {
           service.update(null);
        });
        String expectedMessage = "it is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
     void delete(){
        Person entity = input.mockEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}
