package com.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
	/**
	 *   
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name = "Price")
	Double price;
	@Column(name = "Quantity")
	Integer quantity;
	@ManyToOne 
	@JoinColumn(name = "Productid")
	Product product;
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Order order;
	
	public OrderDetail(Double price, Integer quantity, Product product, Order order) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
