package etiquetas;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.negocio.GestionLibreria;

public class DesplegarListEnJSP extends SimpleTagSupport {
	
	private List<Libro> libros = null;
	private GestionLibreria conexionBD = new GestionLibreria();
	
	@Override
	public void doTag() throws JspException, IOException {
		
		//System.out.println(contexto_peticion.getAttribute("inicioPeticion"));
		
		// Obtenemos el List de Libros y extraemos el contenido
		String datos = "<tr><th>Id</th><th>Título</th><th>ISBN</th><th>Año Publicacion</th><th>Precio</th><th></th></tr>";
		
		libros = conexionBD.consultarTodos();
		
		for (Libro l: libros) {
			datos += "<tr><td>" + l.getIDLibro() + "</td><td>" + l.getTitulo() + "</td><td>" + l.getIsbn() + "</td><td>" + l.getPublicacion() + "</td><td>" + l.getPrecio() + "</td><td class='last'><a id='" + l.getIDLibro() + "' href='./gestionCompra.do?gestion=" +l.getIDLibro() + "'>Añadir</a></td></tr>\n";
		}
		
		datos += "<tr><td></td><td></td><td></td><td></td><td></td><td><a id='comprar' href='./finalizarCompra.do?gestion=0'>COMPRAR</a></td></tr>\n";
		
		// ESCRIBIR EL HTML PARA LA PAGINA JSP
		try {
			this.getJspContext().getOut().append(datos);
		} catch (IOException e) {
			// ERROR EN PROCESO DE SALIDA
			e.printStackTrace();
		}
	}
}
