package com.flowergarden.service;

import com.flowergarden.model.bouquet.Bouquet;
import com.flowergarden.model.flowers.GeneralFlower;

import java.util.List;
import java.util.Map;

public interface BouquetService {

    List<Bouquet> getBouquets();
    float getPrice(int bouquetId);
    Map<Integer, String> reduceFreshness(int bouquetId);
    List<GeneralFlower> getFlowers(int bouquetId);
}
