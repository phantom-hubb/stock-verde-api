package com.phantom.pe.reststockverde.repository;

import com.phantom.pe.reststockverde.model.Stockverde;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStockverde extends JpaRepository<Stockverde,String> {
    Optional<Stockverde> findById(String sku);
}