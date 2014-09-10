package mod04.ex01.dao;

import java.util.List;

import mod04.ex01.model.Person;

public interface IPersonDAO {
	public void savePersons(Person... persons);

	public void savePersons(List<Person> persons);

	public List<Person> getPersons();
}
