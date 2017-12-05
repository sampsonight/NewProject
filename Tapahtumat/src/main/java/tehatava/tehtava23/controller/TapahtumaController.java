package tehatava.tehtava23.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tehatava.tehtava23.domain.Tapahtuma;
import tehatava.tehtava23.domain.TapahtumaRepository;


@Controller
public class TapahtumaController {
	
@Autowired
private TapahtumaRepository brepo;
	
	
	@RequestMapping(value={"","/","/index"}, method=RequestMethod.GET)
	public String greeting (Model model) {
		model.addAttribute("books",brepo.findAll());
		return "/booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long id, Model model) {
		brepo.delete(id);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/add")
		public String addBook(Model model){
		model.addAttribute("book", new Tapahtuma());
		return "/addbooks";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Tapahtuma book){
		brepo.save(book);
		return "redirect:/index";
	}
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Tapahtuma> bookListRest() {
	return (List<Tapahtuma>) brepo.findAll();
	}
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Tapahtuma findBookRest(@PathVariable("id") Long bookId){
	return brepo.findOne(bookId);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login (){
		return "login";
	}
}