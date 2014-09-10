package mod04.ex03a.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import mod04.ex03a.model.Person;


public class PersonTxtFileDAO extends PersonFileDAO {
	private Path path;

	public PersonTxtFileDAO(String filename) {
		this.path = Paths.get(filename + ".txt");
	}

	@Override
	public void savePersons(Person... persons) {
		try {
			Files.createFile(path);
		} catch (IOException e) {
		}
		try (BufferedWriter out = Files.newBufferedWriter(path,
				StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
			for (Person p : persons) {
				out.write(p.toString() + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : lines) {
			String[] fields = line.split(Person.SEPARATOR);
			Person p = new Person();
			p.setName(fields[0].trim());
			p.setSurname(fields[1].trim());
			p.setAge(Integer.parseInt(fields[2].trim()));
			persons.add(p);
		}
		return persons;
	}

}
