package com.flowergarden.service.impl;

import com.flowergarden.dao.FetchMode;
import com.flowergarden.dao.FlowerDao;
import com.flowergarden.model.flowers.GeneralFlower;
import com.flowergarden.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    FlowerDao flowerDao;

    @Override
    public GeneralFlower getFlower(int id) {
        Optional<GeneralFlower> flowerOpt= flowerDao.read(id, FetchMode.LAZY);
        if(flowerOpt.isPresent()){
            return  flowerOpt.get();
        }
        return null;
    }

}
