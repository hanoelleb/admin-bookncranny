package com.bookstore.adminportal.service;

import java.util.List;

import com.bookstore.adminportal.models.Book;

public interface IBookService {
	Book save(Book book);
	
	List<Book> findAll();
}
