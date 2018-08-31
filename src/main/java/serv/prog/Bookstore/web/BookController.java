package serv.prog.Bookstore.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class BookController {
	
	@RequestMapping("/index")
	public String hello(Model model) {
		return null;
	}
}