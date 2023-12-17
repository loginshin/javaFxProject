package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.javafxproject.controller.HelloController.bookService;

public class BookListController implements Initializable {

    @FXML
    private ImageView goMainBtn;
    @FXML
    private ImageView novelBtn;
    @FXML
    private ImageView societyBtn;

    @FXML
    private ImageView itBtn;
    @FXML
    private ImageView economyBtn;
    @FXML
    private ImageView historyBtn;
    @FXML
    private ImageView scienceBtn;
    @FXML
    private ImageView engineeringBtn;
    @FXML
    private ImageView magazineBtn;
    @FXML
    private ImageView comicBtn;
    @FXML
    private ImageView indicationBtn;
    
    @FXML
    private HBox indicationBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    //home버튼 눌러 홈으로 돌아가기
    @FXML
    public void goMainBtnFunc(){
        System.out.println("goMainBtnFunc를 실행합니다.");
        Stage newStage = new Stage();
        MainMenuController controller;
        try {
            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/mainMenuPage.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) goMainBtn.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//↓↓↓↓↓↓↓↓↓ 리스트 정리 메서드 들↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    @FXML
    public void novelBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("소설");
    }
    @FXML
    public void societyBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("정치/사회");
    }
    @FXML
    public void itBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("컴퓨터/IT");
    }


    @FXML
    public void economyBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("경제/경영");
    }
    @FXML
    public void historyBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("역사/문화");
    }
    @FXML
    public void scienceBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("과학");
    }
    @FXML
    public void engineeringBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("기술/공학");
    }
    @FXML
    public void magazineBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
        filter("잡지");
    }
    @FXML
    public void comicBtnClick(){
        indicationBox.getChildren().clear(); // 비워주는 코드 소설인 책들만 넣기
       filter("만화");
    }

    public void filter(String filterName){
        List<Book> books = bookService.getBookList();
        for(Book book : books){
            if(book.getCategoy().equals(filterName)){
                indicationBook(book);
            }else {
                continue;
            }
        }
    }
    
    
    
    
    //책 표시 메서드
    public void indicationBook(Book book){
        ImageView imageView = new ImageView(new Image(book.getImgUrl()));
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);

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
        indicationBox.getChildren().add(vbox);
    }


}
