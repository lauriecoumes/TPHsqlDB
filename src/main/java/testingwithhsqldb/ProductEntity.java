/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb;

/**
 *
 * @author pedago
 */
public class ProductEntity {

    int productID;
    String name;
    float price;

    public ProductEntity() {}

    ProductEntity(int productID, String name, float price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }
    
    public void setProductId(int id) {
        this.productID = id;
    }
    
    public int getProductId() {
        return productID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public float getPrice() {
        return price;
    }
}
