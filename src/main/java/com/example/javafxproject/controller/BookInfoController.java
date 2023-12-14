package com.example.javafxproject.controller;

import com.example.javafxproject.dto.Book;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

import static com.example.javafxproject.Config.configGptKey;

public class BookInfoController implements Initializable {

    //책 데이터 dto 받아오기
    Book book;
    @FXML
    private ImageView closeBtn;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookInfo;

    @FXML
    private ProgressBar loadingProgressBar;


    public void setBook(Book book) {
        this.book = book;
        indicationImage(this.book.getImgUrl());
        indicationText(this.book.getInfo());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 페이지 들어오면 로딩 애니메이션 표시
        loadingProgressBar.setVisible(true);

        String response = chatGPT("sayHelloWold");

        //Gson을 사용하여 Json데이터 파싱
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        // JSON 형식 중 답변 받는 부분만 프린트해서 출력
        String gptResponse = jsonResponse.getAsJsonArray("choices")
                .get(0).getAsJsonObject()
                .getAsJsonObject("message")
                .get("content").getAsString();

        Platform.runLater(() -> {
            // 값 표시
            System.out.println(gptResponse);

            //로딩 애니메이션 감추기
            loadingProgressBar.setVisible(false);

            // ui 업데이트 작업 ↓↓ gpt 값 fxml에 추가하기
            // indicationText(gptResponse);
            

        });




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


 // gpt api사용 메서드
    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = configGptKey; // config파일로 따로 관리 gitegnore에서 필터링
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
