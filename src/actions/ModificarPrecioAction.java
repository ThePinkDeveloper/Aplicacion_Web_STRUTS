package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.negocio.GestionLibreria;

import actionforms.*;

public class ModificarPrecioAction extends Action {
	
	private List<Libro> libros;
	
	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
Libro libro = null;
		
		GestionLibreria conexionBD = new GestionLibreria();
		
		int errorValidacion = 0;
		String atributo = null;
		Object objeto = null;
		String redireccionamiento;
		
		// 0 RECOGIDA DE DATOS DEL FILTRO
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		
		// 1 RECOGIDA DE DATOS DEL FORM-BEAN
		String isbn = request.getParameter("isbn");
		String precioString = request.getParameter("precio");
		double precio = 0;
		
		// 2 CONVERSIÓN DE DATOS
		try {
			precio = Double.parseDouble(precioString);
		} catch (Exception e) {
			errorValidacion = 1;
		}
		
		// 3 VALIDACIÓN
		
		if (isbn.equals("")) errorValidacion = 2;
		if (precioString.equals("")) errorValidacion = 3;

		
		// 4 LÓGICA (REGISTRO EN LA BASE DE DATOS)
		if (errorValidacion == 0) {
			
			if (conexionBD.modificarPrecio(isbn, precio)) {
				atributo = "libro";
				objeto = conexionBD.consultarISBN(isbn);
			} else {
				errorValidacion = 4;
			}
		}
		
		if (errorValidacion != 0) {
			atributo = "errorValidacion";
			switch (errorValidacion) {
				case 1: 	objeto = "No se ha introducido un precio válido.";
							break;
				case 2: 	objeto = "El campo correspondiente al ISBN no puede estar vacío.";
							break;
				case 3: 	objeto = "El campo correspondiente al Precio no puede estar vacío.";
							break;
				case 4: 	objeto = "El ISBN introducido no corresponde a ningún Libro de nuestra Base de Datos.";
							break;
			}
		}	
		
		// 5 ATRIBUTOS AÑADIDOS
		request.setAttribute(atributo, objeto);	
		
		long finPeticion = System.currentTimeMillis();
		int tiempoPeticion = (int) (finPeticion - inicioPeticion);
		request.setAttribute("tiempoPeticion", tiempoPeticion);
		
		// 6 REDIRECCIONAMIENTO DE LA VISTA
		
		if (errorValidacion == 0) {
			redireccionamiento = "ok";
		} else {
			redireccionamiento = "error";
		}
		
		return mapping.findForward(redireccionamiento);
	}
}
