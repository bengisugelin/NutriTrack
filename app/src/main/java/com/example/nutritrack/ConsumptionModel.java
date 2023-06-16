package com.example.nutritrack;

public class ConsumptionModel {

    String consumptionName;
    int image;


    public ConsumptionModel(String consumptionName, int image) {
        this.consumptionName = consumptionName;
        this.image = image;
    }

    public String getConsumptionName() {
        return consumptionName;
    }

    public void setConsumptionName(String consumptionName) {
        this.consumptionName = consumptionName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
