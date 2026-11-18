package com.phantom.pe.reststockverde.controller;

import com.phantom.pe.reststockverde.model.Stockverde;
import com.phantom.pe.reststockverde.service.StockverdeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/stockverde")
public class StockverdeController {


    private final StockverdeService stockverdeService;

    @GetMapping("/testRest")
    public String testRest() {
        return "testRest exitoso";
    }

    @GetMapping("/listRest")
    public List<Stockverde> listarRest() {
        return stockverdeService.listarStk();
    }

    @PostMapping("/action")
    public String actualizarRest(@RequestBody List<Stockverde> listaStockverde) {
        if (listaStockverde == null || listaStockverde.isEmpty()) {
            return "No se recibieron datos.";
        }

        StringBuilder res = new StringBuilder();

        for (Stockverde stockverde : listaStockverde) {
            // Ignorar filas sin estado o que ya estén procesadas (OK)
            if (stockverde.getEstado() == null || "OK".equalsIgnoreCase(stockverde.getEstado())) {
                continue;
            }

            // Convertir a minúsculas para ignorar diferencias entre 'Actualizar' y 'actualizar'
            String accion = stockverde.getEstado().trim().toLowerCase();

            if ("actualizar".equals(accion)) {
                res.append(stockverdeService.updateStock(stockverde)).append("\n");
            } else if ("eliminar".equals(accion)) {
                res.append(stockverdeService.deleteStock(stockverde)).append("\n");
            } else if ("insertar".equals(accion) || "nuevo".equals(accion)) {
                res.append(stockverdeService.insertStock(stockverde)).append("\n");
            }
        }

        return res.length() > 0 ? res.toString() : "Sin cambios pendientes por procesar.";
    }
}