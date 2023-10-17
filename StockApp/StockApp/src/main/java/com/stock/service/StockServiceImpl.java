package com.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stock.dto.StockDto;
import com.stock.entity.StockEntity;
import com.stock.exception.InvalidStockIdException;
import com.stock.repo.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public StockDto createNewStock(StockDto stockDto) {

		/*
		TypeMap<StockDto, StockEntity> typeMap = 
				modelMapper.typeMap(StockDto.class, StockEntity.class);
		typeMap.addMappings((mapper)-> {
			mapper.map(source-> source.getPrice(), StockEntity::setCost);
			mapper.map(source-> source.getName(), StockEntity::setStockName);
		});
		*/
		
		StockEntity stockEntity = modelMapper.map(stockDto, StockEntity.class);
		
				//new StockEntity(stockDto.getName(), stockDto.getMarket(), stockDto.getPrice());
		stockEntity = this.stockRepository.save(stockEntity);
		stockDto = new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
		return stockDto;
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stockDto) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent()) {
			StockEntity stockEntity = opStockEntity.get();
			stockEntity.setName(stockDto.getName());
			stockEntity.setPrice(stockDto.getPrice());
			stockEntity = this.stockRepository.save(stockEntity);
			stockDto = new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
			return stockDto;
		}
		throw new InvalidStockIdException(""+stockId);
	}

	@Override
	public List<StockDto> getAllStocks() {
		List<StockEntity> stockEntityList = stockRepository.findAll();
		
		List<StockDto> stockDtoList = new ArrayList<StockDto>();
		for(StockEntity stockEntity: stockEntityList) {
			StockDto stockDto = 
					new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}

	@Override
	public StockDto getStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent()) {
			StockEntity stockEntity = opStockEntity.get();
			StockDto stockDto = 
					new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
			return stockDto;
		}
		throw new InvalidStockIdException(""+stockId);
	}

	@Override
	public boolean deleteStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent()) {
			stockRepository.deleteById(stockId);
			return true;
		}
		return false;
	}

	@Override
	public List<StockDto> getStockByName(String stockName) {
		List<StockEntity> stockEntityList = this.stockRepository.findByName(stockName);
		List<StockDto> stockDtoList = new ArrayList<StockDto>();
		for(StockEntity stockEntity: stockEntityList) {
			StockDto stockDto = 
					new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}

	@Override
	public List<StockDto> getStockByNamePattern(String stockNamePattern) {
		List<StockEntity> stockEntityList = this.stockRepository.getStocksByNameLike(stockNamePattern);
		List<StockDto> stockDtoList = new ArrayList<StockDto>();
		for(StockEntity stockEntity: stockEntityList) {
			StockDto stockDto = 
					new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}

	@Override
	public List<StockDto> getSortedStocksByName(String sortType) {
		List<StockEntity> stockEntityList = new ArrayList<>();
		if(sortType.equalsIgnoreCase("ASC")) {
			stockEntityList = stockRepository.findByOrderByNameAsc();
		}
		else {
			stockEntityList = stockRepository.findByOrderByNameDesc();
		}
		return getStockDtoListFromEntityList(stockEntityList);
	}

	@Override
	public List<StockDto> getStocksByPagination(int startIndex, int records) {
		Pageable pageable = PageRequest.of(startIndex, records);
		Page<StockEntity> stockEntityPage = stockRepository.findAll(pageable);
		List<StockEntity> stockEntityList = stockEntityPage.getContent();
		
		return getStockDtoListFromEntityList(stockEntityList);
	}

	private List<StockDto> getStockDtoListFromEntityList(List<StockEntity> stockEntityList) {
		List<StockDto> stockDtoList = new ArrayList<StockDto>();
		for(StockEntity stockEntity: stockEntityList) {
			
			StockDto stockDto = modelMapper.map(stockEntity, StockDto.class);
			//		new StockDto(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarket(), stockEntity.getPrice());
			stockDtoList.add(stockDto);
		}
		return stockDtoList;
	}
}









