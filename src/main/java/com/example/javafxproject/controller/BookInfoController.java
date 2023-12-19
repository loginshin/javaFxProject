package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;


import static com.example.javafxproject.config.gptKey;

public class BookInfoController implements Initializable {

    //책 데이터 dto 받아오기
    Book book;

    public String prompt;

    @FXML
    private ImageView closeBtn;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookInfo;

    @FXML
    private ImageView loadingImageView;

    @FXML
    private Label gptText;


    @FXML private ImageView buyBtn;

    @FXML private ImageView rentBtn;



    public void setBook(Book book) {
        this.book = book;

        // 책 이미지와 설명 표시
        indicationImage(this.book.getImgUrl());
        indicationText(this.book.getInfo());

        System.out.println(this.book.getInfo());


        String prompt = "너는 책을 판매하는 키오스크야. 책에대한 설명을 간단하게 제공해줄테니까 333자 내외로 책을 팔수 있는 문구를 작성해줘" +
                "조건은 1. 사람들이 구매욕을 자극할만한 키워드를 넣을것" +
                "2. 개조식으로 3개정도 어떤사람에게 이책이 좋은지 적어줄것" +
                "3. 책을 만든 작자의 의도를 파악할것" +
                "4. 한국말로 말해줄것  " +
                " 책내용 → "+ this.book.getInfo(); // ←←←←←←←←←← 여기에 gpt에게 요청

        // gpt답변을 받지 않아도 들어올 수 있도록 Task 생성 → 쓰레드 사용 → 익명함수로 실행문 바로 실행
        Task<String> gptTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                return chatGPT(prompt);
            }
        };

        // gptTask가 완료되면 실행되는 콜백
        gptTask.setOnSucceeded(event -> {
            //Gson을 사용하여 Json데이터 파싱
            JsonObject jsonResponse = JsonParser.parseString(gptTask.getValue()).getAsJsonObject();
            // JSON 형식 중 답변 받는 부분만 프린트해서 출력
            String gptResponse = jsonResponse.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

            Platform.runLater(() ->{
                System.out.println(gptResponse);

                loadingImageView.setVisible(false);

                // 추가적인 작업 수행 ↓↓↓↓
                // loading Animation Off 하면서 텍스트 표시 메서드 실행하면서 매개변수로 보내기
                indicationGptText(gptResponse);



            });
        });


        // 페이지 들어오면 로딩 애니메이션 표시
        loadingImageView.setVisible(true);
        //Task 실행
        new Thread(gptTask).start(); // Task 지정해줬으니 이제 실행해준다.

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    //이미지 최신화
    public void indicationImage(String imagUrl){
        Image newImage = new Image(imagUrl);
        bookImage.setImage(newImage);
    }

    public void indicationText(String text){
        bookInfo.setText(text);
    }

    //gpt Text 표시하기
    public void indicationGptText(String gptText){
        this.gptText.setText(gptText);
    }


    @FXML
    public void closeFunc(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void buyBtnClickEvent() {
        Stage newStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/purchaseCompletedPage.fxml"));
            Parent root = loader.load();

            // 컨트롤러 가져오고 책 설정
            PurchaseCompletedController controller = loader.getController();
            controller.setBook(book);

            Stage currentStage = (Stage) buyBtn.getScene().getWindow();

            Scene scene = new Scene(root);
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // btn 4 클릭시
    @FXML
    private void rentBtnClickEvent() {
        System.out.println("rentBtn click event");
        System.out.println("RentEmailAuthPage로 이동합니다");
        Stage newStage = new Stage();


        try {

            // 새로운 FXML 파일 로드
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxproject/fxml/admin/RentEmailAuthPage.fxml"));
            Parent root = loader.load();

            RentEmailAuthController controller = loader.getController();
            controller.setBook(book);

//            System.out.println("send data => " + bookService);

            Stage currentStage = (Stage) rentBtn.getScene().getWindow();
            // 새로운 Scene 생성 후 현재 Stage에 설정
            Scene scene = new Scene(root);
            currentStage.setScene(scene);





        } catch (IOException e) {
            e.printStackTrace();
        }
    }



 // gpt api사용 메서드
    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = gptKey; // config파일로 따로 관리 gitegnore에서 필터링
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            //request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            System.out.println(body);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGpt
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return response.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    public static String extractMessageFromJSONResponse(String response){
//        int start = response.indexOf("content")+11;
//        int end = response.indexOf("\"",start);
//        return response.substring(start, end);
//
//
//    }

}
