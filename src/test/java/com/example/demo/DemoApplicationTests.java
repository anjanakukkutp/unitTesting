package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.bean.Employee;
import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;
import com.example.demo.service.TestServiceImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = {AccessingDataMysqlApplication.class})
@Transactional
class DemoApplicationTests {


	private static MockHttpServletRequest request;
@PersistenceContext
private EntityManager entityManager;

	@Autowired
	ApplicationContext context;

	@MockBean
	BookRepository bookRepository;

	@Autowired
	TestServiceImpl testService;

	@Test
	void contextLoads() {

		 //Employee test = context.getBean("Employee", Employee.class);
		Book book = new Book();
		book.setId(200);book.setName("Mandaa");book.setEmail("ra@rara.com");
		List<Book> bookList = List.of(book);
		when(bookRepository.findAll()).thenReturn(bookList).thenThrow(new RuntimeException());

		Iterable<Book> mybooks = testService.getAllBooks();
		Optional<Book> mybook = testService.getBook(123);
		assertEquals("Mandaa", mybooks.iterator().next().getName());

		verify(bookRepository).findById(123);

		System.out.println(mybooks);


	}

}
