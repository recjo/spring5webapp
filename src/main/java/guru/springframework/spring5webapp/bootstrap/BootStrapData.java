package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher pub1 = new Publisher("Merrit House", "123 Main St.", "", "San Jose", "CA", "92211");
        publisherRepository.save(pub1);

        Author auth1 = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "W453453534");
        auth1.getBooks().add(book1);
        book1.getAuthor().add(auth1);
        book1.setPublisher(pub1);
        pub1.getBooks().add(book1);

        //save to H2 database
        authorRepository.save(auth1);
        bookRepository.save(book1);
        publisherRepository.save(pub1);


        Author auth2 = new Author("S.E.", "Hinton");
        Book book2 = new Book("The Outsiders", "88898888");
        auth2.getBooks().add(book2);
        book2.getAuthor().add(auth2);
        book2.setPublisher(pub1);
        pub1.getBooks().add(book2);

        //save to H2 database
        authorRepository.save(auth2);
        bookRepository.save(book2);
        publisherRepository.save(pub1);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of books Publisher: " + pub1.getBooks().size());
    }
}
