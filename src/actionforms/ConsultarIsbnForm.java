package actionforms;

import org.apache.struts.action.ActionForm;

public class ConsultarIsbnForm extends ActionForm{
	
	// Atributos
	
	private String isbn;

	// Getters y Setters
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
		

}