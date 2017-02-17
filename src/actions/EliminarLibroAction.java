package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.negocio.GestionLibreria;

import actionforms.*;

public class EliminarLibroAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
		GestionLibreria conexionBD = new GestionLibreria();
		
		int errorValidacion = 0;
		String atributo = null;
		Object objeto = null;
		String redireccionamiento = null;
		
		// 0 SE RECOGE EL DATO DEL FILTRO
		
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		
		// 1 RECOGIDA DE DATOS DEL FORM-BEAN
		String idString = ((EliminarLibroForm)form).getId();
		int id = 0;
		
		// 2 CONVERSI�N DE DATOS
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			errorValidacion = 1;
		}
		
		// 3 VALIDACI�N
		
		if (idString.equals("")) errorValidacion = 2;

		
		// 4 L�GICA (ELIMINACION DEL REGISTRO EN LA BASE DE DATOS)
		if (errorValidacion == 0) {
			
			if (conexionBD.eliminarLibro(id)) {
				atributo = "libro";
				objeto = id;
			} else {
				errorValidacion = 3;
			}
		} 
		
		if (errorValidacion != 0) {
			atributo = "errorValidacion";
			switch (errorValidacion) {
				case 1: 	objeto = "No se ha introducido un ID v�lido.";
							break;
				case 2: 	objeto = "El campo del ID no puede estar vac�o.";
							break;
				case 3: 	objeto = "No hay ning�n libro con ese ID en la base de datos.";
							break;
			}
		}
				
		// 5 ATRIBUTOS A�ADIDOS
		
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
