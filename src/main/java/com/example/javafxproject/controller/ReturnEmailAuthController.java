package com.example.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class ReturnEmailAuthController implements Initializable {

    @FXML
    private ImageView goMainBtn;
    @FXML
    private TextField emailTF;

    @FXML
    private TextField codeTF;
    @FXML
    private ImageView emailSendBtn;

    @FXML
    private ImageView codeCheckBtn;

    private String verificationCode;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // 받는 사람의 네이버 메일은 POP3/SMTP 설정을 켜야함

    // 이메일 전송 버튼 클릭 시 랜덤한 인증 코드를 생성하고 해당 이메일 주소로 전송하는 함수
    @FXML
    public void emailSendBtnFunc() {
        System.out.println("emailSendBtnFunc를 실행합니다.");

        // 랜덤한 인증 코드 생성 (사용자 정의 로직으로 대체 가능)
        verificationCode = generateRandomCode();

        // 생성된 인증 코드를 제공된 이메일 주소로 전송
        sendVerificationCode(emailTF.getText(), verificationCode);
    }

    // 인증 코드 확인 버튼 클릭 시 입력된 코드와 생성된 코드를 비교하고 결과에 따라 알림을 표시하는 함수
    @FXML
    public void codeCheckBtnFunc() {
        System.out.println("codeCheckBtnFunc를 실행합니다.");

        // 입력된 코드와 생성된 코드를 비교하여 인증 여부 확인
        if (codeTF.getText().equals(verificationCode)) {
            // 인증 성공
            showAlert("인증 성공", "이메일 인증이 성공적으로 완료되었습니다!");
        } else {
            // 인증 실패
            showAlert("인증 실패", "유효하지 않은 인증 코드입니다. 다시 시도해주세요.");
        }
    }

    // JavaMail API를 사용하여 이메일 주소로 인증 코드를 전송하는 함수
    private void sendVerificationCode(String toEmail, String code) {
        // 메일 속성 설정
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.enable", "true"); // SSL 사용 시
        properties.put("mail.smtp.port", "465"); // 또는 "587"를 사용할 수 있음
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        // 인증을 포함한 세션 생성
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("shim010418@gmail.com", "qkwp rabz kivn cerd"); // <- 이 부분에서 일치하지 않다고 오류가 생김
            }
        });

        try {
            // MimeMessage 객체 생성
            Message message = new MimeMessage(session);

            // 발신자와 수신자 주소 설정
            message.setFrom(new InternetAddress("username"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // 이메일 제목과 내용 설정
            message.setSubject("인텔리북스 이메일 인증 코드");
            message.setText("인증 코드: " + code + "입니다.");

            // 메시지 전송
            Transport.send(message);

            showAlert("이메일 전송", "인증 코드가 이메일로 전송되었습니다.");

        } catch (MessagingException e) {
            e.printStackTrace();
            showAlert("이메일 전송 오류", "인증 코드 전송에 실패했습니다. 다시 시도해주세요.");
        }
    }

    // 랜덤한 인증 코드 생성 (사용자 정의 로직으로 대체 가능)
    private String generateRandomCode() {
        // 생성할 인증 코드의 길이 설정
        int codeLength = 6;

        // 랜덤한 숫자로 구성된 인증 코드 생성
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            int digit = random.nextInt(10); // 0부터 9까지의 난수 생성
            code.append(digit);
        }

        return code.toString();
    }


    // 알림 창 표시 함수
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
}
