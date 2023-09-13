package com.utn.ejercicio1.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntidad{
    private String tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id")
    @Builder.Default

    private List<DetallePedido> productos = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name="rubro_id")
    private Rubro rubro;
}


