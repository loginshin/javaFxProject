package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemsPageController {


    private ImageView ImageView;

    private List<String> items = Arrays.asList("조이스틱", "헨드폰", "가방", "의자", "마우스", "텔레비전", "키보드", "노트북", "카메라", "시계",
            "안경", "책", "펜", "지갑", "옷", "신발", "모자", "우산", "컵", "포크",
            "스푼", "접시", "냄비", "팬", "칼", "병", "휴지", "빗", "코트", "스카프",
            "장갑", "벨트", "목걸이", "팔찌", "귀걸이", "반지", "시계", "자동차", "자전거", "모터사이클",
            "비행기", "배", "기차", "버스", "트럭", "택시", "스케이트보드", "롤러블레이드", "스키", "눈썰매",
            "헬멧", "야구공", "축구공", "농구공", "탁구공", "테니스공", "볼링공", "배드민턴 셔틀콕", "골프공", "프리스비",
            "라켓", "배트", "글러브", "스틱", "넷", "콘", "플래그", "휘슬", "트로피", "메달",
            "패달", "바퀴", "열쇠", "문", "창문", "침대", "소파", "책상", "서랍", "옷장",
            "냉장고", "오븐", "전자레인지", "식기세척기", "세탁기", "드라이버", "청소기", "에어컨", "히터", "팬",
            "라디오", "스피커", "헤드폰", "마이크", "앰프", "믹서", "프로젝터", "스크린", "프린터", "스캐너",
            "램프", "전구", "전선", "플러그", "스위치", "배터리", "충전기", "리모컨", "드론", "로봇",
            "헬스기구", "자동차", "자전거", "스쿠터", "킥보드", "트램펄린", "스케이트보드", "인라인 스케이트", "보드게임", "퍼즐",
            "독서대", "이어폰", "전자사전", "플라스크", "물병", "커피머신", "블렌더", "토스터", "전기밥솥", "가스레인지",
            "텀블러", "우유거품기", "와인오프너", "와인잔", "맥주잔", "샴페인잔", "칵테일잔", "소주잔", "막걸리잔", "사케잔",
            "주전자", "찻잔", "차주전자", "찻주전자", "머그잔", "디저트포크", "디저트스푼", "접시", "저울", "커피그라인더",
            "책갈피", "문서함", "책꽂이", "필통", "색연필", "매직", "볼펜", "연필", "지우개", "필기구세트",
            "메모지", "스테이플러", "테이프", "사무용가위", "펀치", "계산기");

    
    // 첫 시작 시 시작되는 메서드
    @FXML
    public void initialize() {
        try {
            String item = getRandomItem();
            System.out.println("출력된 동물======================================= "+ item);
            String photoUrl = getPhotoUrl(item);
            Image image = new Image(photoUrl);
            ImageView.setImage(image);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //랜덤 동물 선택 메서드
    private String getRandomItem(){
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }

    
    //unsplash api 이미지 받아오는 메서드
    private String getPhotoUrl(String item) throws IOException {
        //GET방식으로 unsplash 이미 요청 => 메서드 item string 값에 맞춰 최상단 1장만
        URL url = new URL("https://api.unsplash.com/search/photos?query="+item+"&SLI8Fs-qqVA32o0qrBUiFSNVqcaSSNuY-_7DiQnvBzs");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 버퍼리더로 값 가져오기 => reader에 담기
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            response.append(line);
        }
        reader.close();

        return null;

    }



}
