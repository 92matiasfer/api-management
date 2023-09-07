package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Person;
import uy.com.stronghold.apimanagement.repositories.IPersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepository repository;
	
	@Override
	public Person getPerson(int id) throws ValidationException {
		Person person = repository.getPerson(id);
		if(person == null) throw new ValidationException(Errores.PERSON_NOT_FOUND);
		return person;
	}

	@Override
	public List<Person> getPersons(String name) throws ValidationException {
		List<Person> persons = null;
		persons = repository.getPersons(name);
		if(persons == null || persons.isEmpty()) throw new ValidationException(Errores.PERSON_NOT_FOUND);
		return persons;
	}

	@Override
	public void savePerson(Person person) throws ValidationException {
		if(repository.save(person) == null) throw new ValidationException(Errores.ERROR_SAVE_PERSON);
	}

	@Override
	public void updatePerson(Person person) throws ValidationException {
		if(repository.save(person) == null) throw new ValidationException(Errores.ERROR_UPDATE_PERSON);
	}

	@Override
	public void deletePerson(Person person) {
		repository.delete(person);
	}

}
