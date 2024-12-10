package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("eric");
        eric.setLastName("john");
        
        Book ddd = new Book();
        ddd.setTitle("domain driven design");
        ddd.setIsbn("1234567890");

        Author ahmad = new Author();
        ahmad.setFirstName("khubaib");
        ahmad.setLastName("ahmad");

        Book nojavaBean = new Book();
        nojavaBean.setTitle("no title book");
        nojavaBean.setIsbn("098765432");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author ahmadSaved = authorRepository.save(ahmad);
        Book nojavaBeanSaved = bookRepository.save(nojavaBean);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("publisher ahmad");
        publisher.setAddress("Tokyo");
        Publisher savedPublisher = publisherRepository.save(publisher);

        dddSaved.setPublisher(savedPublisher);
        nojavaBeanSaved.setPublisher(savedPublisher);

        ericSaved.getBooks().add(dddSaved);
        ahmadSaved.getBooks().add(nojavaBeanSaved);
        dddSaved.getAuthors().add(ericSaved);
        nojavaBeanSaved.getAuthors().add(ahmadSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(ahmadSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(nojavaBeanSaved);

        System.out.println("bootspring");
        System.out.println("authors: " + authorRepository.count());
        System.out.println("books: " + bookRepository.count()); 
        System.out.println("publisher: " + publisherRepository.count());
    }

}
