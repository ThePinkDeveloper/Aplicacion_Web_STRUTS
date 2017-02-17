package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.negocio.GestionLibreria;

import actionforms.*;

public class ConsultarIsbnAction extends Action {
	
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
		
		// 0 SE RECOGE EL DATO DEL FILTRO
		
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		
		// 1 RECOGIDA DE DATOS DEL FORM-BEAN
		
		String isbn = ((ConsultarIsbnForm)form).getIsbn();
		System.out.println(isbn);
		// 2 CONVERSIÓN DE DATOS
		// No es necesario en este caso
		
		// 3 VALIDACIÓN
		
		if (isbn.equals("")) errorValidacion = 1;

		
		// 4 LÓGICA (REGISTRO EN LA BASE DE DATOS)
		if (errorValidacion == 0) {
			
			atributo = "libro";
			objeto = conexionBD.consultarISBN(isbn);
			
			if (objeto == null) {
				errorValidacion = 2;
			}
		} 
		
		if (errorValidacion != 0) {
			atributo = "errorValidacion";
			switch (errorValidacion) {
				case 1: 	objeto = "El campo del ISBN no puede estar vacío.";
							break;
				case 2: 	objeto = "El ISBN introducido no pertenece a ningún libro de nuestra base de datos.";
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
