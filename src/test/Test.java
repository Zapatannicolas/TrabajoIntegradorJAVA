package test;

import java.time.LocalDate;
import java.time.LocalTime;
import modelo.*;

public class Test {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		try {
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);
			Ubicacion ubicacion2 = new Ubicacion(-34.6083, -58.3712);

			Contacto contacto0 = new Contacto("nikesportoficial@nikearg.com", "1125894578", ubicacion2);
			Contacto contacto1 = new Contacto("nahuel@gmail.com", "1154786546", ubicacion1);

			// CREAMOS EL COMERCIO
			Comercio comercio1 = new Comercio(1, contacto0, "Nike SportWear", 20423763222L, 300.0, 2.0, 1, 30, 20);

			// AGREGAMOS DIAS DE RETIRO
			comercio1.agregarDiaRetiro(1, LocalTime.parse("09:00"), LocalTime.parse("18:30"), 30);
			comercio1.agregarDiaRetiro(2, LocalTime.parse("09:00"), LocalTime.parse("18:30"), 30);
			comercio1.agregarDiaRetiro(3, LocalTime.parse("09:00"), LocalTime.parse("18:30"), 30);
			comercio1.agregarDiaRetiro(4, LocalTime.parse("09:00"), LocalTime.parse("18:30"), 30);
			comercio1.agregarDiaRetiro(5, LocalTime.parse("09:00"), LocalTime.parse("18:30"), 30);
			comercio1.agregarDiaRetiro(6, LocalTime.parse("09:00"), LocalTime.parse("13:00"), 30);

			// AGREGAMOS ARTICULOS AL COMERCIO
			comercio1.agregarArticulo("Remera Oversize talle L", "1234567895550", 1600.0);
			comercio1.agregarArticulo("Bermuda Cargo talle M", "1234567891132", 2200.0);
			comercio1.agregarArticulo("Campera Rompevientos Black Talle L", "1234567891231", 3000.0);
			comercio1.agregarArticulo("Medias Futbol", "1456789456894", 600);
			comercio1.agregarArticulo("Short Deportivo Talle S", "7546852463257", 1500);
			comercio1.agregarArticulo("Riñonera OceanBlue", "4587985201258", 1750);
			comercio1.agregarArticulo("Zapatillas Old School 44", "1859740256777", 5200);

			// CLIENTE CON EL PRIMER CARRITO
			Cliente cliente1 = new Cliente(contacto1, "Alaniz", "Nahuel", 12345678);

			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("DATOS DEL COMERCIO:");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			
			System.out.println("\n" + comercio1);

			LocalTime horaDesde = LocalTime.parse("10:00"); // HORAS EN LAS QUE SE REALIZAN ENVIOS
			LocalTime horaHasta = LocalTime.parse("19:00");
			LocalDate fecha1 = LocalDate.parse("2020-10-09");// FECHAS EN LAS QUE LOS CLIENTES RETIRAN EL PRODUCTO
			LocalDate fecha2 = LocalDate.parse("2020-10-12");

			// AGREGAMOS EL CARRITO AL COMERCIO
			comercio1.agregarCarrito(fecha1, LocalTime.of(13, 15), cliente1);
			
			
			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println("IMPRIMIR LISTA ARTICULOS");
			comercio1.mostrarListaArticulo();
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			
			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println("IMPRIMIR LISTA CARRITO");
			comercio1.mostrarListaCarrito();
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			
			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println("IMPRIMIR LISTA DIAS DE RETIRO");
			comercio1.mostrarListaDiaRetiro();
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			
			Carrito carrito1 = comercio1.traerCarrito(comercio1.getLstCarrito().get(0).getId());

			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println("TEST CARRITO CON ENVIO Y DESCUENTO EN EFECTIVO Y POR DIA(HACE EL MAYOR):");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("Fecha: " + carrito1.getFecha());
			System.out.println("\n" + comercio1.getLstCarrito().get(0).getCliente());

			// AGREGAMOS ARTICULOS AL CARRITO
			carrito1.agregar(comercio1.traerArticulo(1), 2);
			carrito1.agregar(comercio1.traerArticulo(2), 3);
			carrito1.agregar(comercio1.traerArticulo(2), 1);
			carrito1.agregar(comercio1.traerArticulo(1), 5);
			carrito1.sacarDelCarrito(comercio1.traerArticulo(2), 1); // ACA PROBAMOS SACAR UN ARTICULO

			// LE ASIGNAMOS LA ENTREGA AL CARRITO
			Entrega entrega = new Envio(fecha1, true, horaDesde, horaHasta, cliente1.getContacto().getUbicacion()); // EL
																													// ID
																													// DE
																													// LA
																													// ENTREGA
																													// ES
																													// IGUAL
																													// AL
																													// DEL
																													// CARRITO
			carrito1.setEntrega(entrega);
			((Envio) carrito1.getEntrega()).setCosto(comercio1.getContacto().getUbicacion(), comercio1.getCostoFijo(),
					comercio1.getCostoPorKm());

			// IMPRIMIMOS TOTALES Y DESCUENTOS
			System.out.println("\n" + carrito1.getLstItemCarrito());
			System.out.println("\nEl total del carrito es: " + carrito1.calcularTotalCarrito());
			System.out.println("\nEl descuento por dia es: " + carrito1.calcularDescuentoDia(
					LocalDate.now().getDayOfWeek().getValue(), comercio1.getPorcentajeDescuentoDia()));
			System.out.println("\nEl descuento por abonar en efectivo es: "
					+ carrito1.calcularDescuentoEfectivo(comercio1.getPorcentajeDescuentoEfectivo()));
			carrito1.calcularDescuentoCarrito(comercio1.getDiaDescuento(), comercio1.getPorcentajeDescuentoDia(),
					comercio1.getPorcentajeDescuentoEfectivo());
			System.out.println("\nEl total a pagar es: " + carrito1.totalAPagarCarrito());
			System.out.println("\nEl costo de envio es: " + (int) ((Envio) carrito1.getEntrega()).getCosto()); // LO
																												// CASTEO
																												// A INT
																												// PARA
																												// REDONDEAR

			// CERRAMOS EL CARRITO
			carrito1.setCerrado(true);

			// NUEVO CARRITO
			comercio1.agregarCarrito(fecha2, LocalTime.now(),
					new Cliente(new Contacto("juanperez@gmail.com", "1578962547", new Ubicacion(-34.7667, -58.4)),
							"Juan", "Perez", 40589412));
			Carrito carrito2 = comercio1.traerCarrito(2);

			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println("TEST CARRITO CON RETIRO EN LOCAL Y DESCUENTO EN EFECTIVO:");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("\nFecha: " + carrito2.getFecha());
			System.out.println("\nCliente: " + carrito2.getCliente());

			// AGREGAMOS ARTICULOS AL CARRITO
			carrito2.agregar(comercio1.traerArticulo(3), 1);
			carrito2.agregar(comercio1.traerArticulo(6), 1);

			// LE ASIGNAMOS LA ENTREGA
			Entrega entrega2 = new RetiroLocal(carrito2.getId(), fecha2, true);
			carrito2.setEntrega(entrega2);

			// IMPRIMIMOS TOTALES Y DESCUENTOS DEL SEGUNDO CARRITO
			System.out.println("\n" + carrito2.getLstItemCarrito());
			System.out.println("\nEl total del carrito es: " + carrito2.calcularTotalCarrito());
			System.out.println("\nEl descuento por abonar en efectivo es: "
					+ carrito2.calcularDescuentoEfectivo(comercio1.getPorcentajeDescuentoEfectivo()));
			carrito2.calcularDescuentoCarrito(comercio1.getDiaDescuento(), comercio1.getPorcentajeDescuentoDia(),
					comercio1.getPorcentajeDescuentoEfectivo());
			System.out.println("\nEl total a pagar es: " + carrito2.totalAPagarCarrito());
			((RetiroLocal) carrito2.getEntrega()).setHoraEntrega(LocalTime.parse("09:30"));// LE ASIGNAMOS UNA HORA DE
																							// RETIRO

			// CERRAMOS EL CARRITO
			carrito2.setCerrado(true);

			// GENERAMOS LA LISTA DE TURNOS
			comercio1.generarTurnosLibres(fecha2);

			// IMPRIMIMOS LISTA DE TURNOS, TURNOS LIBRES Y TURNOS OCUPADOS
			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println("LISTA DE TURNOS DE LA FECHA: " + ((RetiroLocal) carrito2.getEntrega()).getFecha());
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.print("\n" + comercio1.generarAgenda(fecha2));
			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println(
					"LISTA DE TURNOS OCUPADOS DE LA FECHA: " + ((RetiroLocal) carrito2.getEntrega()).getFecha());
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("\n" + comercio1.traerTurnosOcupado(fecha2));
			System.out.println(
					"\n\n-----------------------------------------------------------------------------------------------");
			System.out.println(
					"LISTA DE TURNOS DISPONIBLES DE LA FECHA: " + ((RetiroLocal) carrito2.getEntrega()).getFecha());
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("\n" + comercio1.traerTurnosLibres(fecha2));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(
				"\n\n-----------------------------------------------------------------------------------------------");
		System.out.println("PRUEBA DE EXCEPCIONES:");
		System.out.println(
				"-----------------------------------------------------------------------------------------------");

		System.out.println("\nVALIDACIONES:");
		try {
			// LANZA EXCEPCION SI EL DNI CONTIENE ALGUN CARACTER QUE NO SEA UN NUMERO O QUE
			// ESTE FUERA DE RANGO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@outlook.es", "1154786546", ubicacion1);

			Cliente cliente1 = new Cliente(contacto1, "Alaniz", "Nahuel", 0); // PROBAMOS UN DNI INVALIDO

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// LANZA EXCEPCION SI EL LARGO DEL CUIT NO ES 11 Y SI EL ULTIMO DIGITO ES
			// INVALIDO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@hotmail.com", "1154786546", ubicacion1);

			Comercio comercio1 = new Comercio(1, contacto1, "Nike SportWear", 20423763224L, 300.0, 2.0, 1, 30, 20);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// LANZA EXCEPCION SI EL DIGITO VERIFICADOR DEL CODIGO DE BARRAS ES INVALIDO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail.com", "1154786546", ubicacion1);

			Comercio comercio1 = new Comercio(1, contacto1, "Nike SportWear", 20423763222L, 300.0, 2.0, 1, 30, 20);

			comercio1.agregarArticulo("Remera Oversize talle L", "1234567895559", 1600.0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// LANZA EXCEPCION SI EL CELULAR INGRESADO ES INVALIDO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail.com", "11547ada86546", ubicacion1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// LANZA EXCEPCION SI EMAIL INGRESADO ES INVALIDO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail", "1154786546", ubicacion1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// LANZA EXCEPCION SI EL PRECIO DEL ARTICULO ES INCORRECTO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail.com", "1154786546", ubicacion1);

			Comercio comercio1 = new Comercio(1, contacto1, "Nike SportWear", 20423763222L, 300.0, 2.0, 1, 30, 20);
			
			comercio1.agregarArticulo("Campera Rompevientos Black Talle L", "1234567891231", -3000.0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			// LANZA EXCEPCION SI EL ARTICULO A ELIMINAR NO EXISTE
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail.com", "1154786546", ubicacion1);

			Comercio comercio1 = new Comercio(1, contacto1, "Nike SportWear", 20423763222L, 300.0, 2.0, 1, 30, 20);

			comercio1.eliminarArticulo(7);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			// LANZA EXCEPCION SI EL ARTICULO QUE SE DESEA AGREGAR YA EXISTE
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail.com", "1154786546", ubicacion1);

			Comercio comercio1 = new Comercio(1, contacto1, "Nike SportWear", 20423763222L, 300.0, 2.0, 1, 30, 20);
			
			comercio1.agregarArticulo("Campera Rompevientos Black Talle L", "1234567891231", 3000.0);
			
			comercio1.agregarArticulo("Campera Rompevientos Black Talle L", "1234567891231", 3000.0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nELIMINAR CARRITO:");

		try {
			// LANZA EXCEPCION SI EL CARRITO NO EXISTE EN LA LISTA DE CARRITOS DEL COMERCIO
			Ubicacion ubicacion1 = new Ubicacion(-34.9214, -57.9544);

			Contacto contacto1 = new Contacto("nahuel@gmail.com", "1154786546", ubicacion1);

			Comercio comercio1 = new Comercio(1, contacto1, "Nike SportWear", 20423763222L, 300.0, 2.0, 1, 30, 20);

			comercio1.eliminarCarrito(4);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
