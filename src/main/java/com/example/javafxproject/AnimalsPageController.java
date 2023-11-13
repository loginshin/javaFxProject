package com.example.javafxproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnimalsPageController {
    @FXML
    private ImageView imageView;
    private String json;
    private List<String> animals = Arrays.asList("개", "고양이", "말", "소", "돼지", "양", "닭", "오리", "토끼", "햄스터",
            "거북이", "물고기", "조류", "파충류", "곤충", "원숭이", "사자", "호랑이", "표범", "치타",
            "코끼리", "기린", "코뿔소", "캥거루", "코알라", "펭귄", "하이에나", "늑대", "여우",
            "곰", "토끼", "다람쥐", "비버", "바다표범", "바다사자", "돌고래", "고래", "상어", "문어",
            "가재", "게", "개구리", "도마뱀", "뱀", "악어", "앵무새", "참새", "비둘기", "까치",
            "갈매기", "독수리", "매", "부엉이", "뱁새", "노루", "사슴", "쥐", "박쥐", "다람쥐",
            "흰머리독수리", "오리너구리", "두더지", "가오리", "고슴도치", "멧돼지", "산양", "펠리칸", "홍학", "두루미",
            "황새", "알바트로스", "펭귄", "북극곰", "물소",  "카멜레온", "아나콘다",  "프레리도그",
            "아르마딜로", "라마", "알파카", "카피바라", "스컹크", "라쿤", "바다코끼리",
            "낙타", "해파리", "멍게", "해마", "오징어", "불가사리", "가오리","개미핧기");

    //첫 시작시 실행되는 메서드
    @FXML
    public void initialize() {
        new Thread(() -> {
            try {
                String animal = getRandomAnimal();
                System.out.println("출력된 동물======================================= "+ animal);
                String photoUrl = getPhotoUrl(animal);
                Image image = new Image(photoUrl);
                Platform.runLater(() -> imageView.setImage(image));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    //랜덤 동물 선택 메서드
    private String getRandomAnimal(){
        Random random = new Random();
        String animal = animals.get(random.nextInt(animals.size()));
        return animal;
    }

    //use unsplash API
    private String getPhotoUrl(String animal) throws IOException {
        URL url = new URL("https://api.unsplash.com/search/photos?query=" + animal + "&client_id=SLI8Fs-qqVA32o0qrBUiFSNVqcaSSNuY-_7DiQnvBzs&per_page=1");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getFile());

        System.out.println("====================================");

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("response = "+line);
        }


        // 모든 JSON을 사용할 수 없었다.
        // google Gson, JSONObject class 내부 외부 라이브러를 사용할 수 없었고, jar 파일을 다운로드해서 인식시켜도 사용할 수 없었다.


        Pattern pattern = Pattern.compile("\"full\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String fullUrl = matcher.group(1);
            System.out.println(fullUrl);
        }



//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null){
//            response.append(line);
//        }
//        reader.close();


//        System.out.println(response);
        // Parse the JSON response with Gson



        return "imageUrl";

    }

}
