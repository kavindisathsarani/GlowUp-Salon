package lk.ijse.backend.dto;

import java.sql.Timestamp;

public class InventoryDTO {
    private int id;
    private String itemName;
    private int quantity;
    private int minQuantity;
//    private double unitPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public InventoryDTO() {
    }

    public InventoryDTO(int id, String itemName, int quantity, int minQuantity, double unitPrice, Timestamp createdAt, Timestamp updatedAt) {
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

  /*  public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }*/

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
