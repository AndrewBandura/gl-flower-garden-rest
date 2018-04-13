package com.flowergarden.service.impl;

import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.FetchMode;
import com.flowergarden.model.bouquet.Bouquet;
import com.flowergarden.model.flowers.Flower;
import com.flowergarden.model.flowers.GeneralFlower;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.service.BouquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("bouquetService")
public class BouquetServiceImpl implements BouquetService {

    @Autowired
    BouquetDao bouquetDao;

    @Override
    public List<Bouquet> getBouquets() {
        return bouquetDao.findAll(FetchMode.EAGER);
    }

    @Override
    public float getPrice(int bouquetId) {

        float price = 0f;

        Optional<Bouquet> bouquetOpt = bouquetDao.read(bouquetId, FetchMode.EAGER);
        if(bouquetOpt.isPresent()){
            Bouquet bouquet = bouquetOpt.get();
            price = bouquet.getPrice();
        }

        return price;
    }

    @Override
    public Map<Integer, String> reduceFreshness(int bouquetId) {

        Bouquet bouquet = null;

        Map<Integer, String> responseMap = new HashMap<>();

        Optional<Bouquet> bouquetOpt = bouquetDao.read(bouquetId, FetchMode.EAGER);
        if(bouquetOpt.isPresent()){
            bouquet = bouquetOpt.get();
            for(Object flower: bouquet.getFlowers()){
                int freshness = ((GeneralFlower)flower).getFreshness().getFreshness();
                if(freshness > 0){
                    ((GeneralFlower)flower).setFreshness(new FreshnessInteger(--freshness));
                    responseMap.put(((GeneralFlower) flower).getId(), "ok");}
                else {
                    responseMap.put(((GeneralFlower) flower).getId(), "not processed. zero or negative value");}
                }
            }
            bouquetDao.update(bouquet);

        return responseMap;
    }

    @Override
    public List<GeneralFlower> getFlowers(int bouquetId) {
        Optional<Bouquet> bouquetOpt = bouquetDao.read(bouquetId, FetchMode.EAGER);
        if(bouquetOpt.isPresent()){
            return (List<GeneralFlower>)bouquetOpt.get().getFlowers();
        }
        return null;
    }
}
