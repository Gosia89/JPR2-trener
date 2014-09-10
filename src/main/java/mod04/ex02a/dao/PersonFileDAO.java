package mod04.ex02a.dao;

import java.util.List;

import mod04.ex02a.model.Person;

public abstract class PersonFileDAO implements IPersonDAO {
	@Override
	public abstract void savePersons(Person... persons);

	@Override
	public void savePersons(List<Person> persons) {
		Person[] p = (Person[]) persons.toArray(new Person[0]);
		savePersons(p);
	}

	@Override
	public abstract List<Person> getPersons();

}
