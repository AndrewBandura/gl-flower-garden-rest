package com.flowergarden.service.impl;

import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.FetchMode;
import com.flowergarden.model.bouquet.Bouquet;
import com.flowergarden.model.flowers.GeneralFlower;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.service.BouquetService;
import com.flowergarden.service.ReduceFreshnessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<ReduceFreshnessResponse> reduceFreshness(int bouquetId) {

        List<ReduceFreshnessResponse> responseList = new ArrayList<>();

        Bouquet bouquet = null;

        Optional<Bouquet> bouquetOpt = bouquetDao.read(bouquetId, FetchMode.EAGER);
        if(bouquetOpt.isPresent()){
            bouquet = bouquetOpt.get();
            for(Object flower: bouquet.getFlowers()){
                int freshness = ((GeneralFlower)flower).getFreshness().getFreshness();
                if(freshness > 0){
                    ((GeneralFlower)flower).setFreshness(new FreshnessInteger(--freshness));
                    responseList.add(new ReduceFreshnessResponse(((GeneralFlower) flower).getId(), true, ""));
                    }
                else {
                    responseList.add(new ReduceFreshnessResponse(((GeneralFlower) flower).getId(), false, ReduceFreshnessResponse.ERROR_MSG));
                    }
                }
            }
            bouquetDao.update(bouquet);

        return responseList;
    }

    @Override
    public List<GeneralFlower> getFlowers(int bouquetId) {
        Optional<Bouquet> bouquetOpt = bouquetDao.read(bouquetId, FetchMode.EAGER);
        if(bouquetOpt.isPresent()){
            return (List<GeneralFlower>)bouquetOpt.get().getFlowers();
        }
        return new ArrayList<>();
    }
}
