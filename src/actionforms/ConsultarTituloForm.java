package actionforms;

import org.apache.struts.action.ActionForm;

public class ConsultarTituloForm extends ActionForm{
	
	// Atributos
	
	private String titulo;

	// Getters y Setters
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}