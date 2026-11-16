package com.phantom.pe.reststockverde.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Data
public class Stockverde {
    @Id
    @Column(name = "sku")
    private String sku;

    @Column(name = "desc1")
    private String desc1;

    @Column(name = "desc2")
    private String desc2;

    @Column(name = "stock")
    private int stock;

    @Column(name = "retailprice")
    private double retailprice;

    @Transient
    private String estado;

    /*public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }*/
}