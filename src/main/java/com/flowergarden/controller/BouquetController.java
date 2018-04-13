package com.flowergarden.controller;

import com.flowergarden.model.bouquet.Bouquet;
import com.flowergarden.model.flowers.GeneralFlower;
import com.flowergarden.service.BouquetService;
import com.flowergarden.service.ReduceFreshnessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BouquetController {

	@Autowired
	BouquetService bouquetService;

	/*
	 * http://localhost:8080/api/bouquets
	 */
	@RequestMapping(value = "/bouquets",
			produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Bouquet>> getBouquet() {
		List<Bouquet> bouquetList = bouquetService.getBouquets();
		if(bouquetList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity(bouquetList, HttpStatus.OK);
	}

	@RequestMapping(value = "/bouquet/{id}/flowers",
			produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<GeneralFlower>> getFlowers(@PathVariable("id") int id) {
		List<GeneralFlower> flowerList = bouquetService.getFlowers(id);
		if (flowerList.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(flowerList, HttpStatus.OK);
	}

	@RequestMapping(value = "/bouquet/{id}/price",
			produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Float> getPrice(@PathVariable("id") int id) {
		return new ResponseEntity(bouquetService.getPrice(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/bouquet/{id}/unfresh",
			produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<Bouquet> reduceFreshness(@PathVariable("id") int id) {
		List<ReduceFreshnessResponse> responseList = bouquetService.reduceFreshness(id);
		if(responseList.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		return  new ResponseEntity(responseList, HttpStatus.OK);
	}

}