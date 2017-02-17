package actionforms;

import org.apache.struts.action.ActionForm;

public class ModificarPrecioForm extends ActionForm{
	
	// Atributos
	
	private String isbn;
	private String precio;

	// Getters y Setters
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}

		

}