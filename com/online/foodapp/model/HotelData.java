package com.online.foodapp.model;

public class HotelData{
     private String resName;
     private String ownerName;
     private Long resNum;
     private String location;
     private String address;
     private int hotelId;
     private String fssai;
     private String gst;
     private String bank;
     private Long accNum;
     private String ifsc;
     private double delFee;
     
   public void setResName(String resName){
       this.resName = resName;
   }
   public String getResName(){
       return resName;
   }
   public void setOwnerName(String ownerName){
       this.ownerName = ownerName;
   }
   public String getOwnerName(){
       return ownerName;
   }
   public void setResNum(Long resNum){
     if(resNum < 6000000000L || resNum >9999999999L){
        System.out.println("Sorry!! Given Phone Number is Not Valid");
        return;
     }
       this.resNum = resNum;
   }
   public Long getResNum(){
       return resNum;
   }
   public void setLocation(String location){
       this.location = location;
   }
   public String getLocation(){
       return location;
   }
   public void setAddress(String address){
       this.address = address;
   }
   public String getAddress(){
       return address;
   }
   public void setHotelId(int hotelId){
       this.hotelId = hotelId;
   }
   public int getHotelId(){
       return hotelId;
   }
   public void setFssai(String fssai){
        this.fssai = fssai;
   }
   public String getFssai(){
       return fssai;
   }
    public void setGst(String gst){
        this.gst = gst;
   }
   public String getGst(){
       return gst;
   }
   public void setBank(String bank){
       this.bank = bank;
   }
   public String getBank(){
       return bank;
   }
   public void setAccnum(Long accNum){
        this.accNum = accNum;
   }
   public Long getAccnum(){
        return accNum;
   }
   public void setIfsc(String ifsc){
       this.ifsc = ifsc;
   }
   public String getIfsc(){
        return ifsc;
   }
   public void setDelFee(Double delFee){
       this.delFee = delFee;
   }
   public Double getDelFee(){
        return delFee;
   }
}
