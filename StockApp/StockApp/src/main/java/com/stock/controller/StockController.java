package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.StockDto;
import com.stock.service.StockService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/zenstockapp")
@OpenAPIDefinition(info = @Info(description = " <h1> Stock Application</h1>", version = "1.0.0"))
@Tag(description = "- This controller helps in logging in to the application for any stock", name = "Stock Controller")
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@PostMapping(path="/stock", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StockDto> createNewStock(@RequestBody StockDto stockDto) {
		stockDto = stockService.createNewStock(stockDto);
		return new ResponseEntity<StockDto>(stockDto, HttpStatus.CREATED);
	}
	
	@PutMapping(path="/stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDto> updateStock(@RequestBody StockDto stockDto, @PathVariable("id")int stockId) {
		StockDto dbStockDto = stockService.updateStock(stockId, stockDto);
		return new ResponseEntity<StockDto>(dbStockDto, HttpStatus.OK);
	}
	
	@GetMapping(path="/stocks", produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<StockDto>> getAllStocks() {
		List<StockDto> stockDtoList = stockService.getAllStocks();
		return new ResponseEntity<List<StockDto>>(stockDtoList, HttpStatus.OK);
	}
	
	@Operation(summary = "Reads stock by id", description = "This endpoint returns stock by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Stock id not found") })
	@GetMapping(path="/stock/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDto> getStockById(@PathVariable("id")int stockId) {
		StockDto stockDto = stockService.getStockById(stockId);
		return new ResponseEntity<StockDto>(stockDto, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/stock/{id}")
	public ResponseEntity<Boolean> deleteStockById(@PathVariable("id")int stockId) {
		boolean result = stockService.deleteStockById(stockId);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}


	@GetMapping(path="/stock/name/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getStockByName(@PathVariable("name")String stockName) {
		List<StockDto> stockDtoList = stockService.getStockByName(stockName);
		return new ResponseEntity<List<StockDto>>(stockDtoList, HttpStatus.OK);
	}

	@GetMapping(path="/stock/name/like/{namePattern}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getStockByNamePattern(@PathVariable("namePattern")String stockNamePattern) {
		List<StockDto> stockDtoList = stockService.getStockByNamePattern(stockNamePattern);
		return new ResponseEntity<List<StockDto>>(stockDtoList, HttpStatus.OK);
	}

	@GetMapping(path="/stock/sort/name/{sortType}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getSortedStocksByName(@PathVariable("sortType")String sortType) {
		List<StockDto> stockDtoList = stockService.getSortedStocksByName(sortType);
		return new ResponseEntity<List<StockDto>>(stockDtoList, HttpStatus.OK);
	}


	@GetMapping(path="/stocks/pagination/{startIndex}/{records}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDto>> getStocksByPagination(@PathVariable("startIndex")int startIndex,
			@PathVariable("records")int records) {
		List<StockDto> stockDtoList = stockService.getStocksByPagination(startIndex, records);
		return new ResponseEntity<List<StockDto>>(stockDtoList, HttpStatus.OK);
	}
















}
