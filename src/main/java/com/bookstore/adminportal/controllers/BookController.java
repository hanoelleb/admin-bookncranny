package com.bookstore.adminportal.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.adminportal.models.Book;
import com.bookstore.adminportal.service.impl.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "add-book";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String createBook(@ModelAttribute("book") Book book, HttpServletRequest request) {
		
		bookService.save(book);
		
		MultipartFile bookImage = book.getBookImage();
		
		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			FileOutputStream fos = new FileOutputStream(new File("src/main/resources/static/images/books/"+name));
			BufferedOutputStream stream = new BufferedOutputStream(fos);
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:all";
	}
	
	@RequestMapping("/all") 
	public String bookList(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book-list";
	}
	
	@RequestMapping("/book-info")
	public String bookInfo(@RequestParam("id") Long id, Model model) {
		
		Optional<Book> find = bookService.findOne(id);
		
		if (find.isPresent()) {
			Book book = find.get();
			model.addAttribute("book", book);
		} else {
			model.addAttribute("empty", true);
		}
		return "book-info";
	}
	
	@RequestMapping("/edit")
	public String editBook(@RequestParam("id") Long id, Model model) {
		
	Optional<Book> find = bookService.findOne(id);
		
		if (find.isPresent()) {
			Book book = find.get();
			model.addAttribute("book", book);
		} else {
			model.addAttribute("empty", true);
		}
		
		return "update-book";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String updateBook(@ModelAttribute("book") Book book, HttpServletRequest request) {
		bookService.save(book);
		
		MultipartFile bookImage = book.getBookImage();
		
		if (!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/images/books/"+name));
				
				FileOutputStream fos = new FileOutputStream(new File("src/main/resources/static/images/books/"+name));
				BufferedOutputStream stream = new BufferedOutputStream(fos);
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/books/book-info?id="+book.getId();
	}
}
