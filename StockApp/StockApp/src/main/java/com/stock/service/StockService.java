package com.stock.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.stock.dto.StockDto;

public interface StockService {

	StockDto createNewStock(StockDto stockDto);
	StockDto updateStock(int stockId, StockDto stockDto);
	List<StockDto> getAllStocks();
	StockDto getStockById(int stockId);
	boolean deleteStockById(int stockId);
	
	List<StockDto> getStockByName(String stockName);
	List<StockDto> getStockByNamePattern(String stockNamePattern);
	
	List<StockDto> getSortedStocksByName(String sortType);
	
	List<StockDto> getStocksByPagination(int startIndex, int records);
}
