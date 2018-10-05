package serv.prog.Bookstore.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import serv.prog.Bookstore.domain.Book;
import serv.prog.Bookstore.domain.BookRepository;
import serv.prog.Bookstore.domain.CategoryRepository;

import org.springframework.ui.Model;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping("/index")
	public String hello(Model model) {
		return null;
	}
	
	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return"booklist";
	}
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> studentListRest() {
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
	
	@RequestMapping(value="/add")
	public String addBook(Model model){
		model.addAttribute("book",new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Book book){
		repository.save(book);
		return"redirect:booklist";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model){
		Optional<Book> currentBook = repository.findById(bookId);
		model.addAttribute("book", currentBook);
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
}