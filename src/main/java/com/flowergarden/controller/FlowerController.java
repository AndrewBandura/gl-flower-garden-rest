package com.flowergarden.controller;

import com.flowergarden.model.flowers.GeneralFlower;
import com.flowergarden.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowerController {

	@Autowired
	FlowerService flowerService;

	@RequestMapping(value = "/flower/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<GeneralFlower> process(@PathVariable("id") int id)
		{
			GeneralFlower flower= flowerService.getFlower(id);
			if(flower==null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity(flower, HttpStatus.OK);
		}
}