package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.negocio.Carrito;

public class GestionCompraAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
		int id = 0;
		boolean salidaPagina = false;
		
		HttpSession sesion = request.getSession();
		Carrito carrito = (Carrito) sesion.getAttribute("carrito");
		
		
		// Si el carrito está vacío creamos un carrito y lo añadimos como
		// atributo a la sesion.
		if (carrito == null) {
		carrito = new Carrito();
		sesion.setAttribute("carrito", carrito);
		}
		
		System.out.println("Sesion creada");
		
		// 1 RECOGIDA DE DATOS DEL REQUEST
		String idString = request.getParameter("gestion");
		
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		
		System.out.println(idString);

		// 2 CONVERSIÓN DE DATOS
		if (idString != null) { // Si no se viene de la página index
			id = Integer.parseInt(idString);
		}
		
		// 3 VALIDACIÓN
		// No es necesario
		
		// 4 LÓGICA (REGISTRO EN LA BASE DE DATOS)
		if (idString != null) { // Si no se viene de la página index
			if (id > 0) {
				((Carrito)sesion.getAttribute("carrito")).agregarLibro(id);
				System.out.println("Libro añadido");
			} else if (id < 0) {
				((Carrito)sesion.getAttribute("carrito")).eliminarLibro(id * -1);
			} else {
				if (idString != null) {
					salidaPagina = true;
				}
			}
		}
		
		System.out.println(salidaPagina);
		
		// MEDICION TIEMPO PETICION
		long finPeticion = System.currentTimeMillis();
		int tiempoPeticion = (int) (finPeticion - inicioPeticion);
		request.setAttribute("tiempoPeticion", tiempoPeticion);

					
		// 5 SALIDA DE RESPUESTA AL NAVEGADOR
		
		// Si se dio al botón COMPRAR
		
		if (salidaPagina != true) {
			return mapping.findForward("anadir");
		} else {
			return mapping.findForward("finalizar");
		}
	}

}
