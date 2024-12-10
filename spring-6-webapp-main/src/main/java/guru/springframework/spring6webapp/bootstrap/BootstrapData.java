package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        ericSaved.getBooks().add(dddSaved);
        ahmadSaved.getBooks().add(nojavaBeanSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(ahmadSaved);

        System.out.println("bootspring");
        System.out.println("authors: " + authorRepository.count());
        System.out.println("books: " + bookRepository.count()); 
    }

}
