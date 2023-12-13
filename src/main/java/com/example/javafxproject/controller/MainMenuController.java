package com.example.javafxproject.controller;


import com.example.javafxproject.dto.Book;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.example.javafxproject.service.BookService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.javafxproject.controller.HelloController.bookService;

public class MainMenuController implements Initializable {

    // fxml 태그에 아이디 연결
    @FXML
    private ImageView btn1;

    @FXML
    private ImageView btn2;

    @FXML
    private ImageView btn3;

    @FXML
    private ImageView btn4;

    @FXML
    private ImageView btn5;


    @FXML
    private HBox bookImages;

    //책 데이터
//    private BookService bookService;
    

//    HelloController helloController = new HelloController();



//    public void setBookService(BookService bookService){
//        System.out.println("받은데이터");
//        this.bookService = bookService;
//    }


    
    //페이지 들어오면 실행하는 메서드
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 책 데이터 static 들어왔나 확인 코드
        System.out.println(bookService.getBookList().get(0).getName());

        // 책 데이터있다면 Hbox에 띄우기
        addBottomBook();
    }


    @FXML
    private void btn1ClickEvent() {
        System.out.println("btn1 click event");
        System.out.println("bookListPage로 이동합니다");

        Stage newStage = new Stage();
        //BookListController controller;

        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/bookListPage.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btn1.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // btn 2 클릭시
    @FXML
    private void btn2ClickEvent() {
        System.out.println("btn2 click event");
        System.out.println("feedbackPage 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/feedBackPage.fxml"));
            Parent root = loader.load();

            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn2.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // btn 3 클릭시
    @FXML
    private void btn3ClickEvent() {
        System.out.println("btn3 click event");
        System.out.println("phoneAuthPage 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/phoneAuthPage.fxml"));
            Parent root = loader.load();

//            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn3.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // btn 4 클릭시
    @FXML
    private void btn4ClickEvent() {
        System.out.println("btn4 click event");
        System.out.println("phoneAuthPage로 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/phoneAuthPage.fxml"));
            Parent root = loader.load();

//            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn4.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // btn 5 클릭시
    @FXML
    private void btn5ClickEvent() {
        System.out.println("btn5 click event");
        System.out.println("adminAuthPage로 이동합니다");
        Stage newStage = new Stage();
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/adminAuthPage.fxml"));
            Parent root = loader.load();

            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) btn5.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
      //인기책 component 생성
    public void addBottomBook(){
            List<Book> books = bookService.getBookList();
            int count = 0;
            for(Book book : books){
//                System.out.println("populer 띄울 이미지는"+book.getImgUrl());
                //책 이미지 표시 ImageView
                ImageView imageView = new ImageView(new Image(book.getImgUrl()));
                imageView.setFitHeight(300);
                imageView.setFitWidth(250);

                // 이미지 뷰 클릭시 이벤트 처리
                imageView.setOnMouseClicked(event -> {
                    try {
                        // 새로운 FXML 파일 로드
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/bookInfoPage.fxml"));
                        Parent root = loader.load();

                        // 컨트롤러에 책 데이터 전달
                        BookInfoController controller = loader.getController();
                        controller.setBook(book);

                        // 새로운 Scene 생성 후 현재 Stage에 설정 => 기존창에 바꾸기
//                        Stage currentStage = (Stage) imageView.getScene().getWindow();
//                        Scene scene = new Scene(root);
//                        currentStage.setScene(scene);

                        Stage newStage = new Stage();

                        //새 창 띄우기
                        Scene scene = new Scene(root);
                        newStage.setScene(scene);

                        newStage.show();

                    }catch (IOException e){
                        e.printStackTrace();
                    }

                });


                // 책의 제목과 가격을 표시하는 Label 생성
                Label titleLabel = new Label(book.getName());
                Label priceLabel = new Label(String.valueOf(book.getPrice())); //int값이기 때문에 string으로 전환

                // Vbox에 ImageView와 Label 추가
                VBox vbox = new VBox(imageView, titleLabel,priceLabel);

                // HBox에 VBox 추가 어디에 추가할지
                bookImages.getChildren().add(vbox);


                count++;
                if(count >10){
                    return;
                }

            }
    }
}





