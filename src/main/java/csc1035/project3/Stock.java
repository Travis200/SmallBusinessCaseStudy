package csc1035.project3;
import sun.jvm.hotspot.debugger.SymbolLookup;

import javax.persistence.*;
@Entity
@Table(name = "STOCK")
public class Stock {
    //id,name,category,perishable,cost,stock,sell_price
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "stockName")
    private String stockName;

    @Column(name = "category")
    private String category;

    @Column(name = "perishable")
    private Boolean perishable;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "sellPrice")
    private Long sellPrice;

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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }
}
