package com.online.foodapp.model;

public class UserData{
      private String name;
      private String emailId;
      private Long phoneNum;
      private String address;
      private String password;
      private int userId;
      
   public void setName(String name){
      this.name = name;
   }
   public String getName(){
      return name;
   }
   public void setEmail(String emailId){
      this.emailId = emailId;
   }
   public String getEmail(){
      return emailId;
   }
   public void setPhoneNum(Long phoneNum){
      this.phoneNum = phoneNum;
   }
   public Long getPhoneNum(){
      return phoneNum;
   }
   public void setAddress(String address){
      this.address = address;
   }
   public String getAddress(){
      return address;
   }
   public void setPassword(String password){
      this.password = password;
   }
   public String getPassword(){
      return password;
   }
   public void setUserId(int userId){
      this.userId = userId;
   }
   public int getUserId(){
      return userId;
   }
}

