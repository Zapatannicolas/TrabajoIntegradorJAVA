package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Comercio extends Actor {

	private String nombreComercio;
	private long cuit;
	private double costoFijo;
	private double costoPorKm;
	private int diaDescuento;
	private int porcentajeDescuentoDia;
	private int porcentajeDescuentoEfectivo;
	private List<DiaRetiro> lstDiaRetiro = new ArrayList<DiaRetiro>();
	private List<Carrito> lstCarrito = new ArrayList<Carrito>();
	private List<Articulo> lstArticulo = new ArrayList<Articulo>();

	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo) throws Exception {
		super(id, contacto);
		this.nombreComercio = nombreComercio;
		this.setCuit(cuit);
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.diaDescuento = diaDescuento;
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;

	}

	public void mostrarListaCarrito() {
		Iterator<Carrito> iterador = lstCarrito.iterator();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}

	}

	public void mostrarListaDiaRetiro() {
		Iterator<DiaRetiro> iterador = lstDiaRetiro.iterator();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}

	}

	public void mostrarListaArticulo() {
		Iterator<Articulo> iterador = lstArticulo.iterator();
		while (iterador.hasNext()) {
			System.out.println(iterador.next());
		}

	}

	public int asignarIdDiaRetiro() {
		int id = 1;
		if (lstDiaRetiro.size() != 0) {
			id = lstDiaRetiro.size() + 1;
		}
		return id;
	}

	public int asignarIdCarrito() {
		int id = 1;
		if (lstCarrito.size() != 0) {
			id = lstCarrito.size() + 1;
		}
		return id;
	}

	public int asignarIdArticulo() {
		int id = 1;

		if (lstArticulo.size() != 0) {
			id = lstArticulo.size() + 1;
		}
		return id;
	}

	public int asignarIdCliente() {
		int id = 0;
		Iterator<Carrito> iterador = lstCarrito.iterator();
		if (lstCarrito.size() != 0) {
			while (iterador.hasNext()) {
				Carrito carritoAux = iterador.next();
				if (id < carritoAux.getCliente().getId()) {
					id = carritoAux.getCliente().getId();
				}
			}
		}
		return id + 1;
	}

	public int asignarIdEntrega() {
		int id = 0;
		Iterator<Carrito> iterador = lstCarrito.iterator();
		if (lstCarrito.size() != 0) {
			while (iterador.hasNext()) {
				Carrito carritoAux = iterador.next();
				if (id < carritoAux.getEntrega().getId()) {
					id = carritoAux.getEntrega().getId();
				}
			}
		}
		return id + 1;
	}

	public boolean diaRetiroExiste(DiaRetiro dia) {
		Iterator<DiaRetiro> iterador = lstDiaRetiro.iterator();
		boolean existe = false;

		while (iterador.hasNext()) {
			DiaRetiro diaRetiroAux = iterador.next();
			if (dia.equals(diaRetiroAux)) {
				existe = true;
			}
		}
		return existe;
	}

	public DiaRetiro traerDiaRetiro(int id) {

		DiaRetiro diaRetiro = null;
		Iterator<DiaRetiro> iterador = lstDiaRetiro.iterator();

		while (iterador.hasNext()) {
			DiaRetiro diaRetiroAux = iterador.next();
			if (diaRetiroAux.getId() == id) {
				diaRetiro = iterador.next();
			}
		}
		return diaRetiro;
	}

	public DiaRetiro traerDiaRetiro(LocalDate fecha) {

		DiaRetiro diaRetiro = null;
		Iterator<DiaRetiro> iterador = lstDiaRetiro.iterator();
		int diaSemana = fecha.getDayOfWeek().getValue();

		while (iterador.hasNext()) {
			DiaRetiro diaRetiroAux = iterador.next();
			if (diaRetiroAux.getDiaSemana() == diaSemana) {
				diaRetiro = iterador.next();
			}
		}
		return diaRetiro;
	}

	public List<LocalTime> traerHoraRetiro(LocalDate fecha) throws Exception {
		List<LocalTime> lstHoraRetiro = new ArrayList<LocalTime>();
		List<Turno> lstTurnoLibre = traerTurnosLibres(fecha);

		for (Turno turno : lstTurnoLibre) {

			lstHoraRetiro.add(turno.getHora());
		}
		return lstHoraRetiro;
	}

	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo)
			throws Exception {
		DiaRetiro dia = new DiaRetiro(asignarIdDiaRetiro(), diaSemana, horaDesde, horaHasta, intervalo);
		if (diaRetiroExiste(dia) == true)
			throw new Exception("ERROR: El dia de retiro ya existe");

		return (lstDiaRetiro.add(dia));
	}

	public boolean eliminarDiaRetiro(int id) throws Exception {
		if (traerDiaRetiro(id) == null)
			throw new Exception("Error: El dia de retiro no existe");
		return (lstDiaRetiro.remove(traerDiaRetiro(id)));
	}

	public void modificarDiaRetiro(int id, int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo)
			throws Exception {
		if (traerDiaRetiro(id) == null)
			throw new Exception("Error: El dia de retiro no existe");

		traerDiaRetiro(id).setDiaSemana(diaSemana);
		traerDiaRetiro(id).setHoraDesde(horaDesde);
		traerDiaRetiro(id).setHoraHasta(horaHasta);
		traerDiaRetiro(id).setIntervalo(intervalo);
	}

	public boolean articuloExiste(Articulo articulo) {
		boolean existe = false;
		Iterator<Articulo> iterador = lstArticulo.iterator();
		while (iterador.hasNext()) {
			Articulo articuloAux = iterador.next();
			if (articuloAux.equals(articulo)) {
				existe = true;
			}
		}
		return existe;
	}

	public Articulo traerArticulo(int id) {
		Articulo articulo = null;
		Iterator<Articulo> iterador = lstArticulo.iterator();
		while (iterador.hasNext()) {
			Articulo articuloAux = iterador.next();
			if (articuloAux.getId() == id) {
				articulo = articuloAux;
			}
		}
		return articulo;
	}

	public boolean agregarArticulo(String nombre, String codBarras, double precio) throws Exception {

		Articulo articulo = new Articulo(asignarIdArticulo(), nombre, codBarras, precio);
		if (articuloExiste(articulo))
			throw new Exception("Error: El artículo que desea agregar ya existe");

		return lstArticulo.add(articulo);
	}

	public boolean eliminarArticulo(int id) throws Exception {
		if (traerArticulo(id) == null)
			throw new Exception("Error: El artículo que desea eliminar no existe");

		return lstArticulo.remove(traerArticulo(id));
	}

	public void modificarArticulo(String nombre, String codBarras, double precio) throws Exception {
		if (traerArticulo(id) == null)
			throw new Exception("Error: El artículo a modificar no existe");

		traerArticulo(id).setNombre(nombre);
		traerArticulo(id).setCodBarras(codBarras);
		traerArticulo(id).setPrecio(precio);
	}

	public Carrito traerCarrito(int id) {
		Carrito carrito = null;
		Iterator<Carrito> iterador = lstCarrito.iterator();

		while (iterador.hasNext()) {
			Carrito auxCarrito = iterador.next();
			if (auxCarrito.getId() == id) {
				carrito = auxCarrito;
			}
		}
		return carrito;
	}

	public List<Carrito> traerCarritosRetiroLocal(LocalDate fecha) {

		List<Carrito> lstCarritoRetiroLocal = new ArrayList<Carrito>();
		Iterator<Carrito> iterador = lstCarrito.iterator();

		while (iterador.hasNext()) {
			Carrito carritoAux = iterador.next();
			if (carritoAux.getFecha() == fecha) {
				if (carritoAux.getEntrega() instanceof RetiroLocal) {
					lstCarritoRetiroLocal.add(carritoAux);
				}
			}
		}
		return lstCarritoRetiroLocal;
	}

	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente, Entrega entrega) throws Exception {
		Carrito carrito = new Carrito(asignarIdCarrito(), fecha, hora, cliente);
		if (traerCarrito(carrito.getId()) != null)
			throw new Exception("Error: El carrito ya existe");
		cliente.setId(asignarIdCliente());

		if (entrega instanceof RetiroLocal) {
			if (traerDiaRetiro(fecha).getDiaSemana() == 7)
				entrega.setFecha(entrega.getFecha().plusDays(1));
		}
		return lstCarrito.add(carrito);
	}

	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception {
		Carrito carrito = new Carrito(asignarIdCarrito(), fecha, hora, cliente);
		if (traerCarrito(carrito.getId()) != null)
			throw new Exception("Error: El carrito ya existe");
		cliente.setId(asignarIdCliente());
		return lstCarrito.add(carrito);
	}

	public boolean eliminarCarrito(int id) throws Exception {
		if (traerCarrito(id) == null)
			throw new Exception("Error: El carrito no existe");
		return lstCarrito.remove(traerCarrito(id));
	}

	public List<Turno> traerTurnosOcupado(LocalDate fecha) throws Exception {
		List<Turno> lstTurno = generarAgenda(fecha);
		List<Turno> lstTurnoLibre = new ArrayList<Turno>();
		Iterator<Turno> iterador = lstTurno.iterator();

		while (iterador.hasNext()) {
			Turno turnoAux = iterador.next();
			if (turnoAux.isOcupado() == true) {
				lstTurnoLibre.add(turnoAux);
			}
		}
		return lstTurnoLibre;
	}

	public List<Turno> traerTurnosLibres(LocalDate fecha) throws Exception {
		List<Turno> lstTurno = generarAgenda(fecha);
		List<Turno> lstTurnoLibre = new ArrayList<Turno>();
		Iterator<Turno> iterador = lstTurno.iterator();

		while (iterador.hasNext()) {
			Turno turnoAux = iterador.next();
			if (turnoAux.isOcupado() == false) {
				lstTurnoLibre.add(turnoAux);
			}
		}
		return lstTurnoLibre;
	}

	public List<Turno> generarTurnosLibres(LocalDate fecha) throws Exception {
		if (lstDiaRetiro.size() == 0)
			throw new Exception("Error: no existen dias de retiro disponibles");

		DiaRetiro diaRetiro = traerDiaRetiro(fecha);
		int minutosCant = (int) ChronoUnit.MINUTES.between(diaRetiro.getHoraDesde(), diaRetiro.getHoraHasta());
		int turnosCant = minutosCant / diaRetiro.getIntervalo();
		LocalTime primerTurno = diaRetiro.getHoraDesde();
		List<Turno> lstTurno = new ArrayList<Turno>();

		for (int i = 0; i < turnosCant; i++) {

			lstTurno.add(new Turno(fecha, primerTurno, false));
			primerTurno = primerTurno.plusMinutes((long) diaRetiro.getIntervalo());
		}
		return lstTurno;
	}

	public List<Turno> generarAgenda(LocalDate fecha) throws Exception {

		List<Turno> lstTurno = generarTurnosLibres(fecha);
		RetiroLocal entrega = null;
		List<Carrito> carritoRetiroLocal = traerCarritosRetiroLocal(fecha);

		for (Carrito carrito : carritoRetiroLocal) {

			entrega = (RetiroLocal) carrito.getEntrega();

			for (Turno turno : lstTurno) {
				if ((entrega.getHoraEntrega() != null) && (entrega.getHoraEntrega().equals(turno.getHora()))) {

					turno.setOcupado(true);
				}
			}
		}
		return lstTurno;
	}

	protected boolean validarIdentificadorUnico(long cuit) {

		String cuitS = Long.toString(cuit);
		if (cuitS.length() != 11) {
			return false;
		}
		String[] cuitArray = cuitS.split("");
		int[] serie = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int aux = 0;
		for (int i = 0; i < 10; i++) {
			aux = aux + Integer.valueOf(cuitArray[i]) * serie[i];
		}

		aux = 11 - (aux % 11);

		if (aux == 11) {
			aux = 0;
		} else if (aux == 10) {
			aux = 9;
		}

		return Objects.equals(Integer.valueOf(cuitArray[10]), aux);

	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) throws Exception {

		if (validarIdentificadorUnico(cuit) == false) {
			throw new Exception("Error: El cuit ingresado no es válido");
		} else
			this.cuit = cuit;
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		this.diaDescuento = diaDescuento;
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	public List<DiaRetiro> getLstDiaRetiro() {
		return lstDiaRetiro;
	}

	public void setLstDiaRetiro(List<DiaRetiro> lstDiaRetiro) {
		this.lstDiaRetiro = lstDiaRetiro;
	}

	public List<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(List<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}

	public List<Articulo> getLstArticulo() {
		return lstArticulo;
	}

	public void setLstArticulo(List<Articulo> lstArticulo) {
		this.lstArticulo = lstArticulo;
	}

	public boolean equals(Object obj) {

		boolean auxiliar = false;
		Comercio comercio = (Comercio) obj;

		if (cuit == comercio.getCuit()) {
			auxiliar = true;
		}
		return auxiliar;
	}

	@Override
	public String toString() {
		return "COMERCIO: \nNombre= " + nombreComercio + "\nCuit= " + cuit + "\nEmail= " + contacto.getEmail()
				+ "\nCelular= " + contacto.getCelular() + "\nUbicacion= " + contacto.getUbicacion() + "\nCosto fijo= "
				+ costoFijo + "\nCosto por km= " + costoPorKm + "\nDía de descuento= " + diaDescuento
				+ "\nPorcentaje de descuento(día)= " + porcentajeDescuentoDia + "\nPorcentaje de descuento(efectivo)= "
				+ porcentajeDescuentoEfectivo;
	}
}
