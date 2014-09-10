package mod04.ex02a.domain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import mod04.ex02a.dao.IPersonDAO;
import mod04.ex02a.domain.IPersonService;
import mod04.ex02a.model.Person;


public class PersonService implements IPersonService {
	private IPersonDAO dao;
	
	@Inject
	public PersonService(@Named("TXT") IPersonDAO dao) {
		this.dao = dao;
	}

	@Override
	public void savePersons(Person... persons) {
		dao.savePersons(persons);
	}

	@Override
	public void savePersons(List<Person> persons) {
		dao.savePersons(persons);
	}

	@Override
	public List<Person> getTeenagers() {
		List<Person> persons = dao.getPersons();
		return getPersonsAgedInBetween(persons, 11, 19);
	}

	@Override
	public List<Person> getAdults() {
		List<Person> persons = dao.getPersons();
		return getPersonsAgedInBetween(persons, 18, Integer.MAX_VALUE);
	}

	private List<Person> getPersonsAgedInBetween(List<Person> persons, int min,
			int max) {
		List<Person> result = new ArrayList<>();
		for (Person p : persons) {
			int age = p.getAge();
			if (age >= min && age <= max) {
				result.add(p);
			}
		}
		return result;
	}

}
