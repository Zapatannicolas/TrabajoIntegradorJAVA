package modelo;

public class Articulo {

	private int id;
	private String nombre;
	private String codBarras;
	private double precio;

	public Articulo(int id, String nombre, String codBarras, double precio) throws Exception {

		this.id = id;
		this.nombre = nombre;
		this.setCodBarras(codBarras);
		this.setPrecio(precio);
	}

	public boolean validarCodBarras(String codBarras) {

		boolean valido = false;

		String barras = codBarras.replaceAll(" ", "");

		if (barras.matches("^[0-9]{13}$")) {

			int numeros[] = barras.chars().map(Character::getNumericValue).toArray();

			int sumImpares = numeros[1] + numeros[3] + numeros[5] + numeros[7] + numeros[9] + numeros[11];
			int sumPares = numeros[0] + numeros[2] + numeros[4] + numeros[6] + numeros[8] + numeros[10];

			int resultado = sumPares + sumImpares * 3;
			int digitoVerificador = 10 - resultado % 10;

			if (digitoVerificador == 10) {
				digitoVerificador = 0;
			}
			if (digitoVerificador == numeros[12]) {
				valido = true;
			}
		}

		return valido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) throws Exception {
		if (validarCodBarras(codBarras) == false) {
			throw new Exception("Error: El codigo de barra no es válido");
		} else {
			this.codBarras = codBarras;
		}

	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) throws Exception {
		if (precio < 0) {
			throw new Exception("Error: El precio ingresado no es válido");
		} else
			this.precio = precio;
	}

	public boolean equals(Articulo a) {
		return ((id == a.getId()) && (nombre == a.getNombre()) && (codBarras == a.getCodBarras())
				&& (precio == a.getPrecio()));
	}

	@Override
	public String toString() {
		return "\nID=" + id + ", Nombre= " + nombre + ", Codigo de barras= " + codBarras + ", Precio= " + precio;
	}

}