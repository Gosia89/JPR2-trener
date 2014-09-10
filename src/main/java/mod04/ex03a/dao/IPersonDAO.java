package mod04.ex03a.dao;

import java.util.List;

import mod04.ex03a.model.Person;


public interface IPersonDAO {
	public void savePersons(Person... persons);

	public void savePersons(List<Person> persons);

	public List<Person> getPersons();
}