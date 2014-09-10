package mod04.ex02a.dao;

import java.util.List;

import mod04.ex02a.model.Person;

public interface IPersonDAO {
	public void savePersons(Person... persons);

	public void savePersons(List<Person> persons);

	public List<Person> getPersons();
}