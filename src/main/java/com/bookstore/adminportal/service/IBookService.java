package com.bookstore.adminportal.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.adminportal.models.Book;

public interface IBookService {
	Book save(Book book);
	
	List<Book> findAll();
	
	Optional<Book> findOne(Long id);
}
