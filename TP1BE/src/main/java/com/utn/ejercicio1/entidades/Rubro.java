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
public class Rubro extends BaseEntidad{
    private String denominacion;
    @OneToMany(mappedBy = "rubro",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @Builder.Default

    private List<Producto> productos = new ArrayList<>();
    public void agregarproductos(Producto prod){
        productos.add(prod);
    }
}
