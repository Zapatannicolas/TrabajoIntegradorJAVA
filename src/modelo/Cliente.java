package modelo;

public class Cliente extends Actor {

	private String apellido;
	private String nombre;
	private int dni;

	public Cliente(int id, Contacto contacto, String apellido, String nombre, int dni) throws Exception {
		super(id, contacto);
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setDni(dni);
	}

	public Cliente(Contacto contacto, String apellido, String nombre, int dni) throws Exception {
		super(contacto);
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setDni(dni);
	}

	protected boolean validarIdentificadorUnico(int nroDni) {
		boolean valido = false;
		String cadenaDni = String.valueOf(nroDni);

		if (cadenaDni.matches("[0-9]*") && nroDni > 1000000 && nroDni < 50000000) {
			valido = true;
		}
		return valido;
	}

	public static boolean esCadenaLetra(String cadena) {

		return cadena.matches("[A-Z]*" + "[a-z]*");
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) throws Exception {

		if (esCadenaLetra(apellido) == false) {
			throw new Exception("El apellido ingresado no es válido");
		} else
			this.apellido = apellido;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws Exception {

		if (esCadenaLetra(nombre) == false) {

			throw new Exception("El nombre ingresado no es válido");

		} else
			this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) throws Exception {
		if (validarIdentificadorUnico(dni) == false) {

			throw new Exception("Error: El dni ingresado no es válido");
		} else {
			this.dni = dni;
		}

	}

	public boolean equals(Cliente c) {
		return ((apellido == c.getApellido()) && (nombre == c.getNombre()) && (dni == c.getDni()));
	}

	@Override
	public String toString() {
		return "Cliente:" + "  ID= " + id + ",  Apellido= " + apellido + ", Nombre= " + nombre + ", DNI= " + dni;
	}

}
