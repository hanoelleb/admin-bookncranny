package com.bookstore.adminportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.adminportal.models.Book;

@Controller
@RequestMapping("/books")
public class BookController {

	@RequestMapping("/add")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "add-book";
	}
}
