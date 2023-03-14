package com.example.abc;



public class Product {
    private int id;
    private String Product_Name;
    private String Product_Qty;
    private String Product_Price;

    public Product(int id, String product_Name, String product_Qty, String product_Price) {
        this.id = id;
        Product_Name = product_Name;
        Product_Qty = product_Qty;
        Product_Price = product_Price;
    }
    public Product(int id, String product_Name, String product_Qty){//, String product_Price) {
        this.id = id;
        Product_Name = product_Name;
        Product_Qty = product_Qty;
        //Product_Price = product_Price;
    }

    public Product(String product_Name, String product_Qty)// String product_Price) {
    { Product_Name = product_Name;
        Product_Qty = product_Qty;
        //Product_Price = product_Price;
    }

    public Product(String product_Name, String product_Qty,String product_Price) {
        Product_Name = product_Name;
                Product_Qty = product_Qty;
                Product_Price = product_Price;
                }




public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getProduct_Name() {return Product_Name;}
    public void setProduct_Name(String product_Name) {Product_Name = product_Name;}
    public String getProduct_Qty() {return Product_Qty;}
    public void setProduct_Qty(String product_Qty) {Product_Qty = product_Qty;}
    public String getProduct_Price() {return Product_Price;}
    public void setProduct_Price(String product_Price) {Product_Price = product_Price;}
}