package csc1035.project3;


import javax.persistence.*;
@Entity(name = "Stock")
public class Stock {
    //id,name,category,perishable,cost,stock,sell_price
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String stockName;

    @Column
    private String category;

    @Column
    private Boolean perishable;

    @Column
    private double cost;

    @Column
    private int stock;

    @Column
    private double sellPrice;


    public Stock(int id, String stockName, String category, Boolean perishable, double cost, int stock, double sellPrice) {
        this.id = id;
        this.stockName = stockName;
        this.category = category;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sellPrice = sellPrice;
    }

    public Stock(){

    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setcategory(String category) {
        this.category = category;
    }

    public String category() {
        return category;
    }

    public void setperishable(Boolean perishable) {
        this.perishable = perishable;
    }

    public Boolean perishable() {
        return perishable;
    }

    public double getCost() {
        return cost;
    }

    public void  setCost(double cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
