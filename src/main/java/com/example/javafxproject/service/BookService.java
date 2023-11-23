package com.example.javafxproject.service;

import com.example.javafxproject.dto.Book;
import com.example.javafxproject.repository.BookRepository;
import javafx.collections.ObservableList;

//컨트롤러와 레파지토리의 중간다리 => 비즈니스 로직 짜는곳
// bookRepository - BookService클래스로 데이터 이동, 메서드를 이용해 의존성을 높이고 코드의 가독성을 높였다.
public class BookService {
    private BookRepository bookRepository;

    public BookService(){
        bookRepository = new BookRepository();

    }

    public ObservableList<Book> getBookList(){
        return bookRepository.getBookList();
    }
    public void addBook(Book book){
        bookRepository.addBook(book);
    }

    public void deleteBook(Book book){
        bookRepository.deleteBook(book);
    }





}
