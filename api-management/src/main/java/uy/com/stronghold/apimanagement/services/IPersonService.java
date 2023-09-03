package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Person;

@Service
public interface IPersonService {

	Person getPerson(int id) throws ValidationException;

	List<Person> getPersons(String name) throws ValidationException;

	void savePerson(Person person) throws ValidationException;

	void updatePerson(Person person) throws ValidationException; 

	void deletePerson(Person person) throws ValidationException;

	
}
