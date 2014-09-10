package mod04.ex03b.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import mod04.ex03b.model.Person;


public class PersonXmlFileDAO extends PersonFileDAO {
	private Path path;

	@Inject
	public PersonXmlFileDAO(@Named("FILENAME") String filename) {
		this.path = Paths.get(filename + ".xml");
	}

	@Override
	public void savePersons(Person... persons) {
		try {
			Files.createFile(path);
		} catch (IOException e) {
		}
		try (XMLEncoder out = new XMLEncoder(Files.newOutputStream(path,
				StandardOpenOption.WRITE))) {
			out.writeObject(persons);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> getPersons() {
		Person[] persons = null;
		try (XMLDecoder out = new XMLDecoder(Files.newInputStream(path,
				StandardOpenOption.READ))) {
			persons = (Person[]) out.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Person>(Arrays.asList(persons));
	}

}
