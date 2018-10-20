package com.ant.entity.PC;

import java.util.Map;

public class PCUser {

    private Integer id;

    private String phone;

    private String password;

    private String salt;

    private String name;

    private String email;

    /*头像链接*/
    private String imgUrl;

    /*身份证号码*/
    private String IDCardNo;

    /*身份证图片链接*/
    private Map<String,String> IDCardImg;

    private Integer userStart;

    private String RandomCode;

    private String Roles;

    private Map<String,String> Wallet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRandomCode() {
        return RandomCode;
    }

    public void setRandomCode(String randomCode) {
        RandomCode = randomCode;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public Map getWallet() {
        return Wallet;
    }

    public void setWallet(Map wallet) {
        Wallet = wallet;
    }

    public Map getIDCardImg() {
        return IDCardImg;
    }

    public void setIDCardImg(Map IDCardImg) {
        this.IDCardImg = IDCardImg;
    }

    public String getIDCardNo() {
        return IDCardNo;
    }

    public void setIDCardNo(String IDCardNo) {
        this.IDCardNo = IDCardNo;
    }

    public Integer getUserStart() {
        return userStart;
    }

    public void setUserStart(Integer userStart) {
        this.userStart = userStart;
    }
}
