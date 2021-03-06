package com.bookstore.adminportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bookstore.adminportal.models.Book;
import com.bookstore.adminportal.repository.BookRepository;
import com.bookstore.adminportal.service.IBookService;

@Service
public class BookService implements IBookService {
	@Autowired
	BookRepository bookRepository;
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Optional<Book> findOne(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public void delete(Book book) {
		bookRepository.delete(book);
	}
}
