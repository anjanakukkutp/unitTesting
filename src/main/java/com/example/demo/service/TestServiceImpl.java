package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TestServiceImpl {
    private final BookRepository bookRepository;

    public Iterable<Book> getAllBooks() {
       return bookRepository.findAll();
    }

    public Optional<Book> getBook(int bookId) {
        return bookRepository.findById(bookId);
    }


    public Iterable<Book> save(Book test) {
        bookRepository.save(test);
          return bookRepository.findAll();
    }

}
