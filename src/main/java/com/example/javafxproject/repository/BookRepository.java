package com.example.javafxproject.repository;

import com.example.javafxproject.dto.Book;
import com.example.javafxproject.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

//책 데이터를 관리하는 db역할로 사용
public class BookRepository {
    //책을 담는곳
    ObservableList<Book> bookList;

    public BookRepository(){
        bookList = FXCollections.observableArrayList();
    }

    //책 모든 데이터를 가져다준다.
    public ObservableList<Book> getBookList(){
        return bookList;
    }


    // 책 리스트를 저장
    public void setBookList(ObservableList<Book> bookList){
        this.bookList=bookList;
    }

    //책 한권 저장 삭제
    public void addBook(Book book) {
        bookList.add(book);
    }

    public void deleteBook(Book book) {
        bookList.remove(book);
    }

}
