package com.online.foodapp.model;

public class FoodData{
     private String foodCategory;
     private String foodName;
     private double price;
     private int foodListId;
   
   public void setFoodCategory(String foodCategory){
       this.foodCategory = foodCategory;
   }
   public String getFoodCategory(){
       return foodCategory;
   } 
   public void setFoodName(String foodName){
       this.foodName = foodName;
   }
   public String getFoodName(){
       return foodName;
   }
   public void setPrice(Double price){
       this.price = price;
   }
   public Double getPrice(){
       return price;
   }
   public void setFoodListId(int foodListId){
       this.foodListId = foodListId;
   }
   public int getFoodListId(){
       return foodListId;
   }
}
