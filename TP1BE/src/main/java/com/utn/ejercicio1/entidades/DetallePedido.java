package com.utn.ejercicio1.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido extends BaseEntidad{
    private int cantidad;
    private double subtotal;
    @ManyToOne()
    @JoinColumn(name="Producto_id")
   private Producto esDeUn;
    public void asginarPedido(Producto prod){
        this.esDeUn = prod;
    }

    public Double calcularSubtotal() {
        return this.cantidad * esDeUn.getPrecioCompra();
    }
}
