package com.bookstore.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.adminportal.models.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
