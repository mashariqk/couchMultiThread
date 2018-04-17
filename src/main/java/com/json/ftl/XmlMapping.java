package com.json.ftl;

public class XmlMapping {

    private String allowFulfillment;
    private String storeCostOfGoods;
    private String status;
    private String backordered;
    private String quantityAvailable;
    private String fulfillingLocation;

    public XmlMapping() {
    }

    public XmlMapping(String allowFulfillment, String storeCostOfGoods, String status, String backordered, String quantityAvailable, String fulfillingLocation) {
        this.allowFulfillment = allowFulfillment;
        this.storeCostOfGoods = storeCostOfGoods;
        this.status = status;
        this.backordered = backordered;
        this.quantityAvailable = quantityAvailable;
        this.fulfillingLocation = fulfillingLocation;
    }

    public String getAllowFulfillment() {
        return allowFulfillment;
    }

    public void setAllowFulfillment(String allowFulfillment) {
        this.allowFulfillment = allowFulfillment;
    }

    public String getStoreCostOfGoods() {
        return storeCostOfGoods;
    }

    public void setStoreCostOfGoods(String storeCostOfGoods) {
        this.storeCostOfGoods = storeCostOfGoods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackordered() {
        return backordered;
    }

    public void setBackordered(String backordered) {
        this.backordered = backordered;
    }

    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getFulfillingLocation() {
        return fulfillingLocation;
    }

    public void setFulfillingLocation(String fulfillingLocation) {
        this.fulfillingLocation = fulfillingLocation;
    }

    @Override
    public String toString() {
        return "XmlMapping{" +
                "allowFulfillment='" + allowFulfillment + '\'' +
                ", storeCostOfGoods='" + storeCostOfGoods + '\'' +
                ", status='" + status + '\'' +
                ", backordered='" + backordered + '\'' +
                ", quantityAvailable='" + quantityAvailable + '\'' +
                ", fulfillingLocation='" + fulfillingLocation + '\'' +
                '}';
    }
}
