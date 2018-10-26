package serv.prog.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import serv.prog.Bookstore.domain.Book;
import serv.prog.Bookstore.domain.BookRepository;
import serv.prog.Bookstore.domain.Category;



@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Animal Farm");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Murder on the Orient Express", "Agatha Christie", 1934, "9788497571838", 14.50, new Category("Crime"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    
    @Test
    public void deleteBook() {
    	assertThat(repository.findByTitle("Animal Farm").get(0).getId()).isNotNull();
    	assertThat((List<Book>) repository.findAll()).hasSize(2);
    	Book book = repository.findByTitle("Animal Farm").get(0);
    	repository.deleteById(book.getId());
    	assertThat((List<Book>) repository.findAll()).hasSize(1);
    }

}