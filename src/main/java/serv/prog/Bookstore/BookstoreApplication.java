package serv.prog.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import serv.prog.Bookstore.domain.CategoryRepository;
import serv.prog.Bookstore.domain.Book;
import serv.prog.Bookstore.domain.BookRepository;
import serv.prog.Bookstore.domain.Category;

@SpringBootApplication
public class BookstoreApplication {
	
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return(args) -> {
			Category category1 = new Category ("Fantasy");
			Category category2 = new Category ("Science-fiction");
			Category category3 = new Category ("Thriller");
			
			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);
			
			Book book1 = new Book ("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 20, category2);
			Book book2 = new Book ("Animal Farm", "George Orwell", 1945, "22123343-5", 15, category1);
			
			repository.save(book1);
			repository.save(book2);
		};
	}
}