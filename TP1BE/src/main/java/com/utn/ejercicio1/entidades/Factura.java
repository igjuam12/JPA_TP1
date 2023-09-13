package com.utn.ejercicio1.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad{
    private int numero;
    private LocalDateTime fecha;
    private Double descuento;
    private String formapago;
    private int total;
    @OneToOne(mappedBy = "factura")
    private Pedido pedido;

    public int calcularMontoTotal(){
        return (int) (pedido.getTotal()-(pedido.getTotal()*this.descuento*0.01));
    }
}
