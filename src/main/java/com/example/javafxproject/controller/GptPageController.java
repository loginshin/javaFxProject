package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class GptPageController {
    @FXML
    private ImageView imageView;
    private String json;


    //첫 시작시 실행되는 메서드
    @FXML
    public void initialize() {
        new Thread(() -> {
            System.out.println("wellcome gpt test page");

            System.out.println(chatGPT("say hello word"));
        }).start();
    }



    //클리어 메서드
    public void onBtnClearChatPressed(){

    }



    // 메시지 보내기
    public void onSendPrompt(){
        
    }

    //질문 보안 프로토콜, 맨앞페이지 한말들(찾기어려움등),

    //gpt test
    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
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
