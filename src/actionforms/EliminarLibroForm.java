package actionforms;

import org.apache.struts.action.ActionForm;

public class EliminarLibroForm extends ActionForm{
	
	// Atributos
	
	private String id;
	
	// Getters y Setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}