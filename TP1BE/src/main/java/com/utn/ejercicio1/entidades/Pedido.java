package com.utn.ejercicio1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad {
    private String Estado;
    private LocalDateTime fecha;
    private String tipoEnvio;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="pedido_id")
    @Builder.Default

    private List <DetallePedido> detalles = new ArrayList<>();
    //@ManyToOne()
    //private DetallePedido detallePedido;
    @ManyToOne()
    @JoinColumn(name="Cliente_id")
    private Cliente loPidio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Factura factura;

    public void generarDetalles(DetallePedido detalle){
        detalles.add(detalle);

    }
    public Double calcularTotal() {

        double Total = (double) 0;
        for (int i = 0; i < detalles.size(); i++) {

            DetallePedido detalle = detalles.get(i);
            Total = Total + detalle.getSubtotal();
        }
        return Total;
    }
}
