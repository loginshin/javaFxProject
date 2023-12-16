package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

import static com.example.javafxproject.config.configGptKey;

public class BookInfoController implements Initializable {

    //책 데이터 dto 받아오기
    Book book;
    @FXML
    private ImageView closeBtn;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookInfo;

    @FXML private ImageView buyBtn;

    public void setBook(Book book) {
        this.book = book;
        indicationImage(this.book.getImgUrl());
        indicationText(this.book.getInfo());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //이미지 데이터가 있으면 이미지 가져와서 바꾸기

//gpt에 값 넣어보기
//        System.out.println("wellcome gpt test page");
//
//        System.out.println(chatGPT("마흔에 읽는 쇼펜하우어 책에 대해 설명해줘"));
    }

    //이미지 최신화
    public void indicationImage(String imagUrl){
        Image newImage = new Image(imagUrl);
        bookImage.setImage(newImage);
    }

    public void indicationText(String text){
        bookInfo.setText(text);
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


 // gpt 호출
    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = configGptKey;
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
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String extractMessageFromJSONResponse(String response){
        int start = response.indexOf("content")+11;
        int end = response.indexOf("\"",start);
        return response.substring(start, end);


    }

}
