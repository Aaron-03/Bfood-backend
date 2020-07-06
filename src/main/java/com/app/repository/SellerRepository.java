package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

	/* CRUD */
}
