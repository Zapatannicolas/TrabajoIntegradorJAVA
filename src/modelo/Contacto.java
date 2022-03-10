package modelo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacto {
	
	private String email;
	private String celular;
	private Ubicacion ubicacion;
	
	public Contacto(String email, String celular, Ubicacion ubicacion) throws Exception{
		
		this.setEmail(email);
		this.setCelular(celular);
		this.ubicacion = ubicacion;
	}
	
	public int validarEmail(String email) {
		
		int aux = 0;
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile(emailPattern);
		
		if (email != null) {
		     Matcher matcher = pattern.matcher(email);
		     if (matcher.matches()) {
		       aux = 1;
		   }
		}
		
		return aux;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		
		if (validarEmail(email) == 0) {
			throw new Exception("Error: El email ingresado no es válido");
		}else
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) throws Exception{
		
		if (celular.matches("[0-9]*") == false || celular.length() != 10){
			throw new Exception ("Error: El celular ingresado no es válido");
		}else
			this.celular = celular;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public boolean equals(Contacto c) {
		return((email==c.getEmail())&&(celular==c.getCelular())&&(ubicacion==c.getUbicacion()));
	}

	@Override
	public String toString() {
		return "Contacto: \nEmail= " + email + ", Celular= " + celular + ", Ubicacion= " + ubicacion;
	}
	
}
