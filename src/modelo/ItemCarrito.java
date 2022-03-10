package modelo;

public class ItemCarrito {

	private Articulo articulo;
	private int cantidad;

	public ItemCarrito(Articulo articulo, int cantidad) {

		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public double calcularSubtotalItem() {
		double subTotal = articulo.getPrecio() * cantidad;
		return subTotal;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean equals(ItemCarrito i) {
		return ((articulo == i.getArticulo()) && (cantidad == i.getCantidad()));
	}

	@Override
	public String toString() {
		return "\n\nProducto" + articulo + ", cantidad=" + cantidad;
	}

}
