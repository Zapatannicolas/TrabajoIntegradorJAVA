package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroLocal extends Entrega {

	private LocalTime horaEntrega;

	public RetiroLocal(int id, LocalDate fecha, boolean efectivo, LocalTime horaEntrega) {
		super(id, fecha, efectivo);
		this.horaEntrega = horaEntrega;
	}

	public RetiroLocal(int id, LocalDate fecha, boolean efectivo) {
		super(id, fecha, efectivo);
	}

	public LocalTime getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(LocalTime horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public boolean equals(RetiroLocal r) {
		return ((horaEntrega == r.getHoraEntrega()));
	}

	@Override
	public String toString() {
		return "Retiro de local: \nHora de la entrega= " + horaEntrega + ", ID= " + id + ", Fecha= " + fecha
				+ ", Efectivo= " + efectivo;
	}

}
