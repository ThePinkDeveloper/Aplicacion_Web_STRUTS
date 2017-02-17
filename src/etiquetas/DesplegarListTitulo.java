package etiquetas;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.negocio.GestionLibreria;

public class DesplegarListTitulo extends SimpleTagSupport {
	
	private List<Libro> libros = null;
	private GestionLibreria conexionBD = new GestionLibreria();
	
	private String tituloLibro;
	
	@Override
	public void doTag() throws JspException, IOException {
		

		
		// Obtenemos el List de Libros y extraemos el contenido
		String datos = "<tr><th>Id</th><th>Título</th><th>ISBN</th><th>Editorial</th><th>Año Publicacion</th><th>Precio</th></tr>";
		libros = conexionBD.consultarTitulo(tituloLibro);
		
		for (Libro l: libros) {
			datos += "<tr><td>" + l.getIDLibro() + "</td><td>" + l.getTitulo() + "</td><td>" + l.getIsbn() + "</td><td>" + l.getEditorial().getNombre() + "</td><td>" + l.getPublicacion() + "</td><td>" + l.getPrecio() + "</td></tr>";
		}

		// ESCRIBIR EL HTML PARA LA PAGINA JSP
		try {
			this.getJspContext().getOut().append(datos);
		} catch (IOException e) {
			// ERROR EN PROCESO DE SALIDA
			e.printStackTrace();
		}
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

}
