package com.stock.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stock.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer>{

	List<StockEntity> findByName(String stockName);
	List<StockEntity> findByMarket(String market);
	List<StockEntity> findByNameAndMarket(String stockName, String market);
	
	@Query(value="SELECT * FROM STOCKS WHERE NAME LIKE '%stockNamePattern%'", nativeQuery = true)
	List<StockEntity> getStocksByNameLikeUsingSQL(String stockNamePattern);
	@Query(value="SELECT se FROM StockEntity se WHERE se.name LIKE %:stockNamePattern%")//JPQL
	List<StockEntity> getStocksByNameLike(String stockNamePattern);
	List<StockEntity> findByNameContaining(String stockNamePattern);
	List<StockEntity> findByNameIsContaining(String stockNamePattern);
	List<StockEntity> findByNameContains(String stockNamePattern);

	List<StockEntity> findByOrderByNameAsc();
	List<StockEntity> findByOrderByNameDesc();
	
}
