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

    /**
     * This is a method that makes a stock object with all the variables defined as parameters
     * @param id the int to set the id to
     * @param stockName the string to set the stock name to
     * @param category the string to set the category to
     * @param perishable the boolean to set perishable to
     * @param cost the double to set the cost to
     * @param stock the int to set the stock to
     * @param sellPrice the double to set the sell price to
     */
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

    /**
     * Sets the ID of an object to the parameter passed to it, usually though the id is auto generated
     * @param id sets the int passed to it to the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the id of the object
     * @return an int representing the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the name of the stock object
     * @param stockName a String to set as the stock name
     */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    /**
     * Returns the stock objects name
     * @return a String of the stocks name
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * Sets the category of the object
     * @param category a String of the category to set it to
     */
    public void setcategory(String category) {
        this.category = category;
    }

    /**
     * Returns the objects category
     * @return a String  of the category
     */
    public String category() {
        return category;
    }

    /**
     * Sets if the objct is perishable or not
     * @param perishable a boolean to represent if its perishable
     */
    public void setperishable(Boolean perishable) {
        this.perishable = perishable;
    }

    /**
     * Returns if the object is perishable or not
     * @return returns boolean true if perishable false if not
     */
    public Boolean perishable() {
        return perishable;
    }

    /**
     * Returns the cost of the object
     * @return a double of the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of the object
     * @param cost the cost of the stock object to the business
     */
    public void  setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Returns the amount of stock of an object
     * @return returns an int of the amount of stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the amount of stock of an object
     * @param stock an integer representing the amount of the object the business has
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the price you sell the object for
     * @return returns an int of the sell price
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     * Sets the sell price of an object
     * @param sellPrice a double representing the sell price
     */
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
