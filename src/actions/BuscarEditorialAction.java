package actions;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.grupoatrium.modelo.Autor;
import com.grupoatrium.modelo.Editorial;
import com.grupoatrium.modelo.Libro;
import com.grupoatrium.negocio.GestionLibreria;

import actionforms.*;

public class BuscarEditorialAction extends Action {
	

	@Override
	public ActionForward execute(ActionMapping mapping, 
								 ActionForm form, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		
    	long ini = (long) request.getAttribute("inicioPeticion");
    	int tiempoPeticion;
    	
    	// Se indica que el tipo de respuesta que se devolverá será código HTML
    	// en formato UTF-8
    	response.setContentType("text/html;charset=UTF-8");
    	
    	// Se almacena la clave de la respuesta en un objeto BufferedReader  
    	BufferedReader bf = request.getReader();
    	// Se convierte el objeto BufferedReader a String
    	String str = bf.readLine();
    	
    	// Variable auxiliar para el conteo de autores en la BDD
    	int n = 0;
    	
    	// Variables de tipo String auxiliares que almacenarán el código html
    	String combo = "";
    	String datosXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n";
    	String separador = "#####"; // Separador
    	String respuesta = "";

    	// Se crea un objeto de tipo GestionLibreria (fachada) para realizar
    	// las gestiones contra la BDD 
		GestionLibreria commBDD = new GestionLibreria();
		// Ejecutamos el método consutarEditoriales() de la clase 
		// GestionLibreria para optener una colección de todos los objetos 
		// Editorial (Hibernate se encarga de la conversión registro / objeto).
		// Almacenamos los objetos Editorial devueltos por la consulta 
		// en una variable de tipo List
		List<Editorial> editoriales = commBDD.consultarEditoriales();
		
    	// Si la clave del objeto XMLHttpRerquest es igual a "inicio" hay que
		// crear el código html para crear el combo y aprovechamos para
		// obtener los datos del primer autor de la lista
    	if (str.equals("inicio")) {

    		// Un combo se crea con las etiquetas
    		// <select>
    		//		<option> </option>
    		// </select>
    		combo = combo + "<div id = \"divcombo\"><select id = \"combo\" name = \"Editoriales\" onchange=javascript:llamadaADatosEditoriales();>";
    		
    		// Recorremos todo el List editoriales, para obtener el nombre de 
    		// todas las editoriales en la base de datos
    		for (Editorial e: editoriales) {
    			String nombre = e.getNombre();
    			// Añadimos el nombre de la editorial al combo
    			combo = combo + "<option value = \"" + nombre + "\">" + nombre + "</option>";
    			
    			// Si además es el primer nombre del List, consultamos el resto
    			// de datos de esta editorial para generar la web 
    			if (n == 0) {
    				// Utilizamos el objeto de la clase GestionLibreria creado
    				// anteriormente para obtener también los libros publicados
    				// por esta editorial gracias a su métedo
    				// consultarLibrosPorEditorial(Editorial). Almacenamos los 
    				// objetos Libro en una variable List de nombre libros
    				List<Libro> libros = commBDD.consultarLibrosPorEditorial(e);
    				
    				// Almacenamos los datos de la editorial
    				String nif = e.getNif();
    				String dirNombreCalle = e.getDireccion().getCalle();
    				String dirNumero = e.getDireccion().getNumero().toString();
    				String dirPoblacion = e.getDireccion().getPoblacion();
    				String dirProvincia = e.getDireccion().getProvincia();
    				String dirCP = e.getDireccion().getCp().toString();
    				
    				// Generamos el código XML
    				datosXML += "<editorial>" + "\n";
    					datosXML += "<nombre>" + nombre + "</nombre>" + "\n";
    					datosXML += "<nif>" + nif + "</nif>" + "\n";
    					datosXML += "<direccion>" + "\n";
    						datosXML += "<nombreCalle>" + dirNombreCalle + "</nombreCalle>" + "\n";
    						datosXML += "<numeroCalle>" + dirNumero + "</numeroCalle>" + "\n";
    						datosXML += "<poblacion>" + dirPoblacion + "</poblacion>" + "\n";
    						datosXML += "<provincia>" + dirProvincia + "</provincia>" + "\n";
    						datosXML += "<cp>" + dirCP + "</cp>" + "\n";
    					datosXML += "</direccion>" + "\n";
    					datosXML += "<libros>" + "\n";
						for (Libro l : libros) {
							datosXML += "<libro>" + l.getTitulo() + "</libro>" + "\n";
						}
						datosXML += "</libros>" + "\n";
					datosXML += "</editorial>" + "\n";
    			}
    			
    			// Una vez obtenidos todos necesarios de la primera editorial
    			// de la coleccion, incrementamos el valor de referencia para 
    			// que no se sobreescriban los datos obtenidos con el resto 
    			// de editoriales
    			n++;
    		}
    		
    		// Cuando ya hemos obtenido el nombre de todos los autores de la
    		// BDD podemos cerrar el combo
    		combo = combo + "</select></div>";
    		
    		// Creamos un único String sumando el código html del combo, el
    		// separador y el código xml.
        	respuesta = combo + separador + datosXML;
        	
        	
        	// Se vuelve a incluir el separador de datos
    		respuesta += separador;
    		
    		// obtenemos la hora actual y hallamos la diferencia
    		long fin = System.currentTimeMillis();
    		tiempoPeticion = (int) (fin - ini);
    		
    		// añadimos el tiempo de la peticion a la respuesta como un
    		// script para luego posicionarlo donde queramos dentro de la
    		// página
    		respuesta += tiempoPeticion;
        	
    		// Lo añadimos al objeto HttpServletResponse
        	response.getWriter().write(respuesta);
 
    	// Si la clave del objeto XMLHttpRerquest es igual al nombre de una
    	// editorial no es necesario crear el combo y sólo hay que obtener los
    	// datos necesarios de esa editorial.
    	} else {
    		
    		// Recorremos la colección de editoriales de la base de datos 
    		// buscando la coincidencia entre el valor de la clave del objeto
    		// XMLHttpRequest y el nombre de la editorial
    		for (Editorial e: editoriales) {
    			
    			// Cuando se produce la coincidencia obtenemos sus datos
    			if (str.equals(e.getNombre())) {
    				
    				// Guardamos los datos de la editorial en Strings
    				String nombre = e.getNombre();
    				String nif = e.getNif();
    				String dirNombreCalle = e.getDireccion().getCalle();
    				String dirNumero = e.getDireccion().getNumero().toString();
    				String dirPoblacion = e.getDireccion().getPoblacion();
    				String dirProvincia = e.getDireccion().getProvincia();
    				String dirCP = e.getDireccion().getCp().toString();
    				
    				// Creamos una colección de objetos Libros publicados por
    				// por la editorial.
    				List<Libro> libros = commBDD.consultarLibrosPorEditorial(e);
    				
    				// Generamos el código xml para las tablas con los datos
    				// de la editorial
    				datosXML += "<editorial>" + "\n";
					datosXML += "<nombre>" + nombre + "</nombre>" + "\n";
					datosXML += "<nif>" + nif + "</nif>" + "\n";
					datosXML += "<direccion>" + "\n";
						datosXML += "<nombreCalle>" + dirNombreCalle + "</nombreCalle>" + "\n";
						datosXML += "<numeroCalle>" + dirNumero + "</numeroCalle>" + "\n";
						datosXML += "<poblacion>" + dirPoblacion + "</poblacion>" + "\n";
						datosXML += "<provincia>" + dirProvincia + "</provincia>" + "\n";
						datosXML += "<cp>" + dirCP + "</cp>" + "\n";
					datosXML += "</direccion>" + "\n";
					datosXML += "<libros>" + "\n";
					for (Libro l : libros) {
						datosXML += "<libro>" + l.getTitulo() + "</libro>" + "\n";
					}
					datosXML += "</libros>" + "\n";
					datosXML += "</editorial>" + "\n";
				
    	    		// Una vez hemos encontrado la coincidencia en la colección
    	    		// ya no es necesario seguir recorriendo la colección.
					break;
    			}
    		}
    		
    		// Como ya se ha tenido que haber generado el código html de la
    		// página web, en este caso sólo enviamos código xml
    		response.setContentType("text/xml;charset=UTF-8");
    		
        	// Se vuelve a incluir el separador de datos
    		datosXML += separador;
    		
    		// obtenemos la hora actual y hallamos la diferencia
    		long fin = System.currentTimeMillis();
    		tiempoPeticion = (int) (fin - ini);
    		
    		// añadimos el tiempo de la peticion a la respuesta como un
    		// script para luego posicionarlo donde queramos dentro de la
    		// página
    		datosXML += tiempoPeticion;
    		
    		// Añadimos el código XML generado al objeto HttpServletResponse
    		response.getWriter().write(datosXML);
    	}    	
    	
    	return null;
	}
	
}
