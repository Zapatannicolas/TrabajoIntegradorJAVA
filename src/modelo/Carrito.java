package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrito {

	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean cerrado;
	private double descuento = 0;
	private Cliente cliente;
	private List<ItemCarrito> lstItemCarrito = new ArrayList<ItemCarrito>();
	private Entrega entrega;

	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			Entrega entrega) {

		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
		this.cliente = cliente;
		lstItemCarrito = new ArrayList<ItemCarrito>();
		this.entrega = entrega;
	}

	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente) {

		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
		this.cliente = cliente;
	}

	public Carrito(int id, LocalDate fecha, LocalTime hora, Cliente cliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cliente = cliente;
	}

	public void mostrarListaItemCarrito() {
		Iterator<ItemCarrito> iterador = lstItemCarrito.iterator();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}

	}

	public ItemCarrito traerItemCarrito(Articulo articulo) {

		Iterator<ItemCarrito> iterador = lstItemCarrito.iterator();
		ItemCarrito item = null;
		while (iterador.hasNext()) {
			ItemCarrito item2 = iterador.next();
			if (item2.getArticulo().equals(articulo)) {
				item = item2;
			}
		}
		return item;
	}

	public boolean agregar(Articulo articulo, int cantidad) {

		if (traerItemCarrito(articulo) == null) {

			lstItemCarrito.add(new ItemCarrito(articulo, cantidad));
		} else {

			traerItemCarrito(articulo).setCantidad(traerItemCarrito(articulo).getCantidad() + cantidad);
		}
		return true;
	}

	public boolean sacarDelCarrito(Articulo articulo, int cantidad) {

		if (traerItemCarrito(articulo).getArticulo().equals(articulo)) {
			if (traerItemCarrito(articulo).getCantidad() <= cantidad) {
				lstItemCarrito.remove(traerItemCarrito(articulo));
			} else {
				traerItemCarrito(articulo).setCantidad(traerItemCarrito(articulo).getCantidad() - cantidad);
			}
		}
		return true;
	}

	public double calcularTotalCarrito() {

		double total = 0;
		Iterator<ItemCarrito> iterador = lstItemCarrito.iterator();

		while (iterador.hasNext()) {
			ItemCarrito item = iterador.next();
			total += item.getCantidad() * item.getArticulo().getPrecio();
		}
		return total;
	}

	public double calcularDescuentoDia(int diaDescuento, double porcentajeDia) {

		double descuento = 0;
		double descuentoTotal = 0;
		double cantidad = 0;
		int diaString = fecha.getDayOfWeek().getValue();

		if (diaDescuento == diaString) {
			Iterator<ItemCarrito> iterador = lstItemCarrito.iterator();
			while (iterador.hasNext()) {
				ItemCarrito item = iterador.next();
				if (item.getCantidad() == 1) {
					cantidad = 0;
				} else {
					cantidad = item.getCantidad() / 2;
					descuento = (int) cantidad * item.getArticulo().getPrecio() * (porcentajeDia / 100);
				}
				descuentoTotal = descuentoTotal + descuento;
			}
		}
		return descuentoTotal;
	}

	public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) throws Exception {
		if (entrega.isEfectivo() == false)
			throw new Exception("Error: no es posible hacer el descuento en efectivo");

		double total = 0;
		double DescuentoEfectivo = 0;

		total = calcularTotalCarrito();
		if (entrega.isEfectivo()) {
			DescuentoEfectivo = total * (porcentajeDescuentoEfectivo / 100);
		} else {
			throw new Exception("El pago no se realizo en efectivo");
		}
		return DescuentoEfectivo;
	}

	public void calcularDescuentoCarrito(int diaDescuento, double porcentajeDescuento,
			double porcentajeDescuentoEfectivo) throws Exception {

		double descuentoDia = calcularDescuentoDia(diaDescuento, porcentajeDescuento);
		double descuentoEfectivo = calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
		if (descuentoDia >= descuentoEfectivo) {
			descuento = descuentoDia;
		} else {
			descuento = descuentoEfectivo;
		}
	}

	public double totalAPagarCarrito() {
		double total = calcularTotalCarrito();
		double totalAPagar = total - descuento;
		return totalAPagar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}

	public void setLstItemCarrito(List<ItemCarrito> lstItemCarrito) {
		this.lstItemCarrito = lstItemCarrito;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public boolean equals(Carrito c) {
		return (id == c.getId() && fecha == c.getFecha() && hora == c.getHora() && descuento == c.getDescuento()
				&& cliente == c.getCliente() && lstItemCarrito == c.getLstItemCarrito() && entrega == c.getEntrega());
	}

	@Override
	public String toString() {
		return "Carrito: \nID= " + id + ", Fecha= " + fecha + ", Hora= " + hora + ", Cerrado= " + cerrado
				+ ", Descuento= " + descuento + ", Cliente= " + cliente + ", Listado items carrito= " + lstItemCarrito
				+ ", Entrega= " + entrega;
	}

}