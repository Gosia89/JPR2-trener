package mod04.ex02b.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import mod04.ex02b.model.Person;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PersonJsonFileDAO extends PersonFileDAO {
	private Path path;

	@Inject
	public PersonJsonFileDAO(@Named("FILENAME") String filename) {
		this.path = Paths.get(filename + ".json");
	}

	@Override
	public void savePersons(Person... persons) {
		Gson gson = new Gson();
		try {
			Files.createFile(path);
		} catch (IOException e) {
		}
		try (BufferedWriter out = Files.newBufferedWriter(path,
				StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
			out.write(gson.toJson(persons));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> getPersons() {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Person>>() {
		}.getType();
		List<Person> persons = new ArrayList<>();
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String json : lines) {
			persons = gson.fromJson(json, type);
		}
		return persons;
	}

}
