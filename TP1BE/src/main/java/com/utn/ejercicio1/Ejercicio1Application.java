package com.utn.ejercicio1;


import com.utn.ejercicio1.entidades.*;
import com.utn.ejercicio1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;



@SpringBootApplication
public class Ejercicio1Application {
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1Application.class, args);


	}
	@Bean
	CommandLineRunner init(RubroRepository rubroRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
		return args -> {


			Producto Cocacola = Producto.builder()
					.tipo("Cocacola 500ml")
					.tiempoEstimadoCocina(0)
					.denominacion("bebidaMediana")
					.precioCompra(500)
					.stockActual(5)
					.stockMinimo(40)
					.unidadMedida("ml")
					.receta("no tiene")
					.build();
			Producto Sprite = Producto.builder()
					.tipo("Sprite 500ml")
					.tiempoEstimadoCocina(0)
					.denominacion("bebidaMediana")
					.precioCompra(500)
					.stockActual(5)
					.stockMinimo(40)
					.unidadMedida("ml")
					.receta("no tiene")
					.build();
			Producto Fanta = Producto.builder()
					.tipo("Fanta 500ml")
					.tiempoEstimadoCocina(0)
					.denominacion("bebidaMediana")
					.precioCompra(500)
					.stockActual(5)
					.stockMinimo(40)
					.unidadMedida("ml")
					.receta("no tiene")
					.build();

			Rubro bebidas = Rubro.builder()
					.denominacion("bebidas")
					.build();

			bebidas.agregarproductos(Cocacola);
			bebidas.agregarproductos(Sprite);
			bebidas.agregarproductos(Fanta);
			Cocacola.setRubro(bebidas);
			Fanta.setRubro(bebidas);
			Sprite.setRubro(bebidas);
			rubroRepository.save(bebidas);

					Cliente cliente1 = Cliente.builder()
							.nombre("Giovanni")
							.apellido("Cirrincione")
							.edad(2)
							.build();
					Cliente cliente2 = Cliente.builder()
							.nombre("Ignacio")
							.apellido("Ambrogetti")
							.edad(21)
							.build();
					Cliente cliente3 = Cliente.builder()
							.nombre("Theo")
							.apellido("pelegrina")
							.edad(20)
							.build();
					Cliente cliente = Cliente.builder()
							.nombre("Pedro")
							.apellido("donnarumma")
							.edad(21)
							.build();
					Domicilio domicilio = Domicilio.builder()
							.calle("LosPeralitos")
							.localidad("Lujan de cuyo")
							.Numero(2226)
							.build();
					Domicilio domicilio1 = Domicilio.builder()
							.calle("Viedma")
							.localidad("Lujan de cuyo")
							.Numero(313)
							.build();
					Domicilio domicilio2 = Domicilio.builder()
							.calle("Viedma")
							.localidad("Lujan de cuyo")
							.Numero(234)
							.build();
					Domicilio domicilio3 = Domicilio.builder()
							.calle("Don carlos")
							.localidad("vistalba")
							.Numero(200)
							.build();
					Domicilio domicilio4 = Domicilio.builder()
							.calle("Giñazu")
							.localidad("Lujan de cuyo")
							.Numero(1026)
							.build();

							cliente.agregardomicilios(domicilio);//Agregar el domicilio a las personas, cuando guardas a una persona la guardas con su domicilio
							cliente1.agregardomicilios(domicilio4);
							cliente1.agregardomicilios(domicilio1);
							cliente2.agregardomicilios(domicilio2);
							cliente3.agregardomicilios(domicilio3);

							clienteRepository.save(cliente);//guardar los clientes

							clienteRepository.save(cliente1);
							clienteRepository.save(cliente2);
							clienteRepository.save(cliente3);



					Pedido pedido = Pedido.builder()
							.Estado("Iniciado")
							.fecha(LocalDateTime.now())
							.tipoEnvio("delivery")

							.build();

					DetallePedido detalle = DetallePedido.builder()
							.cantidad(5)

							.build();
							detalle.setEsDeUn(Fanta);
							detalle.setSubtotal(detalle.calcularSubtotal());//calcular el subtotal del detalle

					DetallePedido detalle1 = DetallePedido.builder()
							.cantidad(3)

							.build();
							detalle1.setEsDeUn(Cocacola);
							detalle1.setSubtotal(detalle1.calcularSubtotal());
					//detallePedidoRepository.save(detalle);
					//detallePedidoRepository.save(detalle1);



					pedido.generarDetalles(detalle1);
					pedido.generarDetalles(detalle);
					pedido.setLoPidio(cliente);
					pedido.setTotal(pedido.calcularTotal());
					Factura factura = Factura.builder()
							.numero(1)
							.descuento(10.0)
							.fecha(LocalDateTime.now())
							.formapago("Efectivo")

							.build();
					pedido.setFactura(factura);
					factura.setPedido(pedido);
					factura.setTotal(factura.calcularMontoTotal());
					pedidoRepository.save(pedido);//y luego se van creando los pedidos y se les asignan a un cliente









			Rubro rubrorecuperado = rubroRepository.findById(bebidas.getId()).orElse(null);
			if (rubrorecuperado!=null){
				System.out.println("HOLA");
			}
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado!=null){
				System.out.println("Nombre: "+clienteRecuperado.getNombre());
				System.out.println("Apellido: "+clienteRecuperado.getApellido());
				System.out.println("Edad: "+clienteRecuperado.getEdad());
				clienteRecuperado.mostrardomicilios();
			}


		};


	}

}

