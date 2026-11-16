package com.phantom.pe.reststockverde.service;

import com.phantom.pe.reststockverde.model.Stockverde;
import com.phantom.pe.reststockverde.repository.IStockverde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StockverdeService {

    @Autowired
    private IStockverde iStockverde;

    public List<Stockverde> listarStk() { return iStockverde.findAll(); }

    public Optional<Stockverde> buscarStockverde(String sku) {
        return iStockverde.findById(sku);
    }

    public String updateStock(Stockverde product) {
        if (iStockverde.existsById(product.getSku())) {
            iStockverde.save(product);
            return "Stock Actualizado exitoso para SKU: " + product.getSku();
        } else {
            return "El SKU " + product.getSku() + " no existe en la base de datos";
        }
    }

    public String deleteStock(Stockverde stockverde) {
        if (iStockverde.existsById(stockverde.getSku())) {
            iStockverde.deleteById(stockverde.getSku());
            return "Eliminar stock exitoso para SKU: " + stockverde.getSku();
        } else {
            return "El SKU " + stockverde.getSku() + " no existe para eliminar";
        }
    }

    public String insertStock(Stockverde stockverde) {
        if (!iStockverde.existsById(stockverde.getSku())) {
            iStockverde.save(stockverde);
            return "Stock Insertado exitoso para SKU: " + stockverde.getSku();
        } else {
            return "El SKU " + stockverde.getSku() + " ya existe, usa actualizar";
        }
    }
}