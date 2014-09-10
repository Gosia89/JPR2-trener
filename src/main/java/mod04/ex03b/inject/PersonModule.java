package mod04.ex03b.inject;

import mod04.ex03b.dao.IPersonDAO;
import mod04.ex03b.domain.IPersonService;
import mod04.ex03b.domain.PersonService;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPersonService.class).to(PersonService.class);
		bind(String.class).annotatedWith(Names.named("FILENAME")).toInstance("persons");
		bind(String.class).annotatedWith(Names.named("FORMAT")).toInstance("TXT");

		bind(IPersonDAO.class).toProvider(PersonProvider.class);
	}

}
