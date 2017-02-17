package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.modelo.Libro;
import com.grupoatrium.negocio.GestionLibreria;

import actionforms.*;

public class ConsultarTituloAction extends Action {
	
	private List<Libro> libros;
	
	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
		GestionLibreria conexionBD = new GestionLibreria();
		
		int errorValidacion = 0;
		String atributo = null;
		Object objeto = null;
		String redireccionamiento;
		
		// 0 RECOGIDA DE DATOS DEL FILTRO
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		
		// 1 RECOGIDA DE DATOS DEL FORM-BEAN
		String titulo = ((ConsultarTituloForm)form).getTitulo();
		
		// 2 CONVERSIÓN DE DATOS
		// No es necesario en este caso
		
		// 3 VALIDACIÓN
		
		if (titulo.equals("")) errorValidacion = 1;

		
		// 4 LÓGICA (REGISTRO EN LA BASE DE DATOS)
		if (errorValidacion == 0) {
			
			atributo = "titulo";
			libros = conexionBD.consultarTitulo(titulo);
			objeto = titulo;
			
			if (libros == null) {
				errorValidacion = 2;
			}
		} 
		
		if (errorValidacion != 0) {
			atributo = "errorValidacion";
			switch (errorValidacion) {
				case 1: 	objeto = "El campo del Título no puede estar vacío.";
							break;
				case 2: 	objeto = "El Título introducido no pertenece a ningún Libro de nuestra base de datos.";
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
