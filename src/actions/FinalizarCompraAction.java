package actions;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.negocio.Carrito;

public class FinalizarCompraAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
		Carrito carrito;
		
		HttpSession sesion = request.getSession();
		carrito = (Carrito) sesion.getAttribute("carrito");
		
		int addid = 0;
		int removeid = 0;
		boolean agregar = false;
		boolean eliminar = false;
		
		// 1 RECOGIDA DE DATOS DEL REQUEST
		
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		String addidString = (String) request.getParameter("addid");
		String removeidString = (String) request.getParameter("removeid");
		System.out.println(addidString);
		System.out.println(removeidString);
		
		// 2 CONVERSIÓN DE DATOS
		
		if (addidString != null) addid = Integer.parseInt(addidString);
		if (removeidString != null) removeid = Integer.parseInt(removeidString);
		System.out.println(addid);
		System.out.println(removeid);
		
		// 3 VALIDACIÓN
		
		if (addid != 0) agregar = true;
		if (removeid != 0) eliminar = true;
		System.out.println(agregar);
		System.out.println(eliminar);
		
		// 4 LÓGICA (REGISTRO EN LA BASE DE DATOS)
		if (agregar) {
			carrito.agregarLibro(addid);
		}
		
		if (eliminar) {
			carrito.eliminarLibro(removeid);
		}
	
		// MEDICION TIEMPO PETICION
		
		long finPeticion = System.currentTimeMillis();
		int tiempoPeticion = (int) (finPeticion - inicioPeticion);
		request.setAttribute("tiempoPeticion", tiempoPeticion);

					
		// 5 SALIDA DE RESPUESTA AL NAVEGADOR
		
		// Formatear la salida
		DecimalFormat formateador = new DecimalFormat("####.##");
		
		// Se añade el precio total como atributo a la sesion
		sesion.setAttribute("preciototal", formateador.format(carrito.verImporte()));
	
		return mapping.findForward("modificarCantidad");

	}
}
