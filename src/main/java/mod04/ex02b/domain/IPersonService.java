package mod04.ex02b.domain;

import java.util.List;

import mod04.ex02b.model.Person;

public interface IPersonService {
	public void savePersons(Person... persons);

	public void savePersons(List<Person> persons);

	public List<Person> getTeenagers();

	public List<Person> getAdults();
}
