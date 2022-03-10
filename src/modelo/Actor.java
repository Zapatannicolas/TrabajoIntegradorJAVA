package modelo;

public abstract class Actor {

	protected int id;
	protected Contacto contacto;

	public Actor(int id, Contacto contacto) {

		this.id = id;
		this.contacto = contacto;

	}

	public Actor(Contacto contacto) {
		this.contacto = contacto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public boolean equals(Actor a) {
		return (id == a.getId());
	}

	@Override
	public String toString() {
		return "Actor: \nID= " + id + ", Contacto= " + contacto;
	}

}
