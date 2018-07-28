package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
		
	}
	
	
	
	private void initData(){
		
		Author eric = new Author("Eric", "Evan");
		Publisher pub = new Publisher("Apress", "USA");
		Book ddd = new Book("Domain Drivan Design", "12345", pub);
		eric.getBook().add(ddd);
		ddd.getAuthors().add(eric);
		
		publisherRepository.save(pub);
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		
		Author rod = new Author("Rod", "Jonson");
		Publisher p = new Publisher("worx" , "England");
				
		Book noEJB = new Book("J2EE Development without EJB", "23444", p);
		
		publisherRepository.save(p);
		rod.getBook().add(ddd);
		authorRepository.save(rod);
		bookRepository.save(noEJB);
		
		
	}

	

}
