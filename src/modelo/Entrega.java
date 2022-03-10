package modelo;

import java.time.LocalDate;

public abstract class Entrega {
	
	protected int id;
	protected LocalDate fecha;
	protected boolean efectivo;
	
	public Entrega(int id, LocalDate fecha, boolean efectivo) {
		
		this.id = id;
		this.fecha = fecha;
		this.efectivo = efectivo;
	}
	
	public Entrega(LocalDate fecha, boolean efectivo) {
		
		this.fecha = fecha;
		this.efectivo = efectivo;
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

	public boolean isEfectivo() {
		return efectivo;
	}

	public void setEfectivo(boolean efectivo) {
		this.efectivo = efectivo;
	}
	
	public boolean equals(Entrega e) {
		return((id==e.getId())&&(fecha==e.getFecha())&&(efectivo==e.isEfectivo()));
	}

	@Override
	public String toString() {
		return "Entrega: ID= " + id + ", Fecha= " + fecha + ", Efectivo= " + efectivo;
	}

	public Ubicacion traerUbicacion(Entrega entrega) {
		Ubicacion ubi=null;
		if(entrega instanceof Envio) {
		  ubi=((Envio) entrega).getUbicacion();	
		}
		return ubi;
	}
	
}
