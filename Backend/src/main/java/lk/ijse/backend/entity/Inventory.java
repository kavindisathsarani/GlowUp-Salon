package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "min_quantity")
    private int minQuantity;

 /*   @Column(name = "unit_price", nullable = false, precision = 10)
    private double unitPrice;*/

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Inventory() {
    }

    public Inventory(int id, String itemName, int quantity, int minQuantity, double unitPrice, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
//        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
