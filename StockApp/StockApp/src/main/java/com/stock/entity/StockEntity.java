package com.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="STOCKS")
public class StockEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stock_id")
	private int id;
	
	@Column(name="stock_name")
	private String name;

	@Column(name="stock_market")
	private String market;
	
	@Column(name="stock_price")
	private double price;
	
	public StockEntity() {}
	public StockEntity(String name, String market, double price) {
		super();
		this.name = name;
		this.market = market;
		this.price = price;
	}
	public StockEntity(int id, String name, String market, double price) {
		super();
		this.id = id;
		this.name = name;
		this.market = market;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", name=" + name + ", market=" + market + ", price=" + price + "]";
	}
}
