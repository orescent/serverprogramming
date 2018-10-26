package serv.prog.Bookstore.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findById(long id);
	List<Book> findByTitle(String title);
}