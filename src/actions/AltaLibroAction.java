package actions;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.modelo.Autor;
import com.grupoatrium.modelo.Direccion;
import com.grupoatrium.modelo.Editorial;
import com.grupoatrium.modelo.Libro;
import com.grupoatrium.negocio.GestionLibreria;

import actionforms.*;

public class AltaLibroAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
		Libro nuevoLibro = null;
		Editorial nuevaEditorial = null;
		Direccion nuevaDireccion = null;
		Set<Autor> autores  = null;
		
		GestionLibreria conexionBD = new GestionLibreria();
		
		int errorValidacion = 0;
		String atributo = null;
		Object objeto = null;
		String redireccionamiento;
		
		// 0 SE RECOGE EL DATO DEL FILTRO
		
		long inicioPeticion = (long) request.getAttribute("inicioPeticion");
		
		// 1 RECOGIDA DE DATOS DEL FORM-BEAM
		
		String titulo = ((AltaLibroForm)form).getTitulo();
		String isbn = ((AltaLibroForm)form).getIsbn();
		String publicacionString = ((AltaLibroForm)form).getPublicacion();
		int publicacion = 0;
		String precioString = ((AltaLibroForm)form).getPrecio();
		double precio = 0;
		String descripcion = ((AltaLibroForm)form).getDescripcion();
		String nombreEditorial = ((AltaLibroForm)form).getNombreEditorial();
		String nifEditorial = ((AltaLibroForm)form).getNifEditorial();
		String calleEditorial = ((AltaLibroForm)form).getCalleEditorial();
		String numeroEditorialString = ((AltaLibroForm)form).getNumeroEditorial();
		int numeroEditorial = 0;
		String poblacionEditorial = ((AltaLibroForm)form).getPoblacionEditorial();
		String cpEditorialString = ((AltaLibroForm)form).getCpEditorial();
		int cpEditorial = 0;
		String provinciaEditorial = ((AltaLibroForm)form).getProvinciaEditorial();
		
		String[] nombreAutor = new String[4];
		String[] nacionalidadAutor = new String[4];
		String[] comentarioAutor = new String[4];
		
		nombreAutor[0] = ((AltaLibroForm)form).getNombreAutor1();
		nacionalidadAutor[0] = ((AltaLibroForm)form).getNacionalidadAutor1();
		comentarioAutor[0] = ((AltaLibroForm)form).getComentarioAutor1();
		nombreAutor[1] = ((AltaLibroForm)form).getNombreAutor2();
		nacionalidadAutor[1] = ((AltaLibroForm)form).getNacionalidadAutor2();
		comentarioAutor[1] = ((AltaLibroForm)form).getComentarioAutor2();
		nombreAutor[2] = ((AltaLibroForm)form).getNombreAutor3();
		nacionalidadAutor[2] =((AltaLibroForm)form).getNacionalidadAutor3();
		comentarioAutor[2] = ((AltaLibroForm)form).getComentarioAutor3();
		nombreAutor[3] = ((AltaLibroForm)form).getNombreAutor4();
		nacionalidadAutor[3] = ((AltaLibroForm)form).getNacionalidadAutor4();
		comentarioAutor[3] = ((AltaLibroForm)form).getComentarioAutor4();
		
		// 2 CONVERSIÓN DE DATOS
		try {
			numeroEditorial = Integer.parseInt(numeroEditorialString);
		} catch (Exception e) {
			errorValidacion = 1;
		}
		
		try {
			cpEditorial = Integer.parseInt(cpEditorialString);
		} catch (Exception e) {
			errorValidacion = 2;
		}
		
		try {
			publicacion = Integer.parseInt(publicacionString);
		} catch (Exception e) {
			errorValidacion = 3;
		}
		
		try {
			precio = Double.parseDouble(precioString);
		} catch (Exception e) {
			errorValidacion = 4;
		}
		
		// 3 VALIDACIÓN
		
		if (titulo.equals("")) errorValidacion = 5;
		if (nombreEditorial.equals("")) errorValidacion = 6; 
		if (nombreAutor[0].equals("") && nombreAutor[1].equals("") && 
			nombreAutor[2].equals("") && nombreAutor[3].equals(""))
				errorValidacion = 7; 
		
		// 4 LÓGICA (REGISTRO EN LA BASE DE DATOS)
		if (errorValidacion == 0) {
			autores = new HashSet<Autor>();
			Autor[] arrayAutores = new Autor[4];
			for (int i = 0; i < arrayAutores.length; i++) {
				arrayAutores[i] = new Autor(nombreAutor[i], nacionalidadAutor[i], comentarioAutor[i]);
				if (!arrayAutores[i].getNombre().equals("")) autores.add(arrayAutores[i]);
			}
			nuevaDireccion = new Direccion(calleEditorial, numeroEditorial, poblacionEditorial, cpEditorial, provinciaEditorial);
			nuevaEditorial = new Editorial(nombreEditorial, nifEditorial, nuevaDireccion);
			nuevoLibro = new Libro(titulo, autores, nuevaEditorial, isbn, publicacion, precio, descripcion);
			
			if (conexionBD.altaLibro(nuevoLibro)) {
				atributo = "libro";
				objeto = nuevoLibro;
			} else {
				errorValidacion = 8;
			}
		} 

		if (errorValidacion != 0) {
			atributo = "errorValidacion";
			switch (errorValidacion) {
				case 1: 	objeto = "No se ha introducido un número de calle correcto.";
							break;
				case 2: 	objeto = "No se ha introducido un código postal correcto.";
							break;
				case 3: 	objeto = "No se ha introducido un año de publicación correcto.";
							break;
				case 4: 	objeto = "No se ha introducido un precio correcto.";
							break;
				case 5: 	objeto = "El campo del título del libro no puede estar vacío.";
							break;
				case 6: 	objeto = "El campo del nombre de la editorial no puede estar vacío .";
							break;
				case 7: 	objeto = "Se debe introducir al menos el nombre de un autor.";
							break;
				case 8: 	objeto = "Se ha producido un error al dar de alta el libro en la base de datos.";
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
