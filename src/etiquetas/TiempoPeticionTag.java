package etiquetas;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TiempoPeticionTag extends SimpleTagSupport {
	
	private String tiempoInicio;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		long inicio = Long.parseLong(tiempoInicio);
		
		long fin = System.currentTimeMillis();
		
		int tiempoPeticion = (int) (fin-inicio);
		Integer objetoTiempoPeticion = tiempoPeticion;
		
		
		// ESCRIBIR EL HTML PARA LA PAGINA JSP
		try {
			this.getJspContext().getOut().append(objetoTiempoPeticion.toString());
		} catch (IOException e) {
			// ERROR EN PROCESO DE SALIDA
			e.printStackTrace();
		}
		
	}

	public void setTiempoInicio(String tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
}
