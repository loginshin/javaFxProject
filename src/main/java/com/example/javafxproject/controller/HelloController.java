package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import com.example.javafxproject.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.example.javafxproject.dto.*;

public class HelloController implements Initializable {


    // 데이터 저장돼있는곳 ↓↓↓↓↓↓↓↓↓↓↓
    // static으로 데이터를 관리하는건 매우 위험, private으로 관리할것
    static BookService bookService = new BookService();;

    @FXML
    private Button btn;

    public  HelloController(){

    }

    public BookService getBookService(){
        return bookService;
    }




    //책 데이터를 채우는 작업 진행
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // 책 객체 생성
        Book book1 = new Book("마흔에 읽는 쇼펜하우어", 15300,
                "마흔의 마음은 복잡하다. 그동안 수많은 시험을 치르고 자리 잡기 시작했지만, 한참 남은 인생이 기대되기보다 늘 그렇듯 같은 일상이 반복될 것이라는 생각을 하게 된다. 벌써부터 웬만한 일은 재미",
                "강용수","소설","https://contents.kyobobook.co.kr/sih/fit-in/300x0/pdt/9791192300818.jpg"
        );
        Book book2 = new Book("트렌드 코리아 2024", 17100,"모든 일은 서서히 준비되고 있다가 갑작스럽게 나타난다. 챗GPT가 그랬다. 인공지능 기술과 이야기가 수도 없이 나왔지만, 챗GPT만큼 우리에게 충격을 주는 것은 없었다. 무엇이 달랐던 것일까? ‘자연어",
                "김난도","정치/사회","https://contents.kyobobook.co.kr/sih/fit-in/300x0/pdt/9788959897179.jpg");
        Book book3 = new Book("남에게 보여주려고 인생을 낭비하지 마라",15750,"철학자들의 철학자로 불리는 쇼펜하우어에게는 늘 비관론자, 비평가, 아웃사이더 등의 꼬리표가 따라다녔다. 하지만 그는 누구보다 인간적인 시선으로 삶의 진리를 추구하던 사람이었다. 1851년 출","쇼펜하우어","정치사회","https://contents.kyobobook.co.kr/sih/fit-in/300x0/pdt/9791169850445.jpg");
//        Book book4 = new Book("남에게 좋은 사람보다 나에게 좋은 사람");
//        Book book5 = new Book("5번 책");
//        Book book6 = new Book("6번 책");
//        Book book7 = new Book("7번 책");
//        Book book8 = new Book("8번 책");
//        Book book9 = new Book("9번 책");
//        Book book10 = new Book("10번 책");
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
//        bookService.addBook(book4);
//        bookService.addBook(book5);
//        bookService.addBook(book6);
//        bookService.addBook(book7);
//        bookService.addBook(book8);
//        bookService.addBook(book9);
//        bookService.addBook(book10);
        System.out.println(bookService.getBookList().get(0).getName()); // 첫번째 책 이름
    }



    //fxml을 Stage에 등록한다.
    @FXML
    private void goToMainPage() {
        changePage("/com/example/javafxproject/fxml/mainMenuPage.fxml");
    }

    //페이지 바꾸는 메서드
    private void changePage(String fxml) {
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            // 새로운 컨트롤러의 인스턴스를 가져옴
            MainMenuController controller = loader.getController();
//            controller.setBookService(bookService); // 새로운 컨트롤러에 BookService 객체 전달
            System.out.println("send data => "+bookService);
            
            
            // BookService 객체를 새로운 컨트롤러에 전달
//            controller.setBookService(bookService);

            // 현재 Stage 가져오기
            Stage currentStage = (Stage) btn.getScene().getWindow();

            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root,600,800);
            currentStage.setResizable(false);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}