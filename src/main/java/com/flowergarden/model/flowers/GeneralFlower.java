package com.flowergarden.model.flowers;

import com.flowergarden.model.bouquet.Bouquet;
import com.flowergarden.properties.FreshnessInteger;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter

public class GeneralFlower implements Flower<Integer>, Comparable<GeneralFlower> {

    private int id;

    String name;

    FreshnessInteger freshness;

    float price;

    int lenght;

    Bouquet bouquet;

    public void setFreshness(FreshnessInteger fr) {
        freshness = fr;
    }

    @Override
    public FreshnessInteger getFreshness() {
        return freshness;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public int getLenght() {
        return lenght;
    }

    @Override
    public int compareTo(GeneralFlower compareFlower) {
        int compareFresh = compareFlower.getFreshness().getFreshness();
        return this.getFreshness().getFreshness() - compareFresh;
    }

}
