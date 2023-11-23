package com.example.javafxproject.dto;

public class Book {
    //이름, 가격, 정보, 만든이,분류 , 대여자 번호, 이미지
    private String name;
    private int price;
    private String info;

    private String Author;

    private String categoy;

    private String rentPhoneNumber;

    private String imgUrl;


    public  Book(){

    }


    public Book(String name){
        this.name=name;
    }

    public Book(String name, int price, String info, String maker, String categoy,  String imgUrl) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.Author = maker;
        this.categoy = categoy;
        this.imgUrl = imgUrl;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String maker) {
        this.Author = maker;
    }

    public String getCategoy() {
        return categoy;
    }

    public void setCategoy(String categoy) {
        this.categoy = categoy;
    }

    public String getRentPhoneNumber() {
        return rentPhoneNumber;
    }

    public void setRentPhoneNumber(String rentPhoneNumber) {
        this.rentPhoneNumber = rentPhoneNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
