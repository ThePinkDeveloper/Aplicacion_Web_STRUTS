package etiquetas;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.grupoatrium.negocio.Carrito;
import com.grupoatrium.negocio.GestionLibreria;

public class DesplegarListCompra extends SimpleTagSupport {
		
	@Override
	public void doTag() throws JspException, IOException {
		
		JspContext context = this.getJspContext();
		GestionLibreria conexionBD = new GestionLibreria();
		
		Carrito carrito = (Carrito) context.findAttribute("carrito");
		
		// Obtenemos el List de Libros y extraemos el contenido
		String datos = "<tr><th>Id</th><th>Título</th><th></th><th></th><th>Precio</th><th>Cantidad</th><th>Subtotal</th></tr>";
		
		List<Integer> libros = carrito.getCarrito();
		
		Map<Integer,Integer> libros_cantidad = new HashMap<Integer,Integer>();

		for (int l : libros) {
			int clave = l;
			if (!libros_cantidad.containsKey(clave)) {
				int valor = 1;
				libros_cantidad.put(clave, valor);
			} else {
				int valor = libros_cantidad.get(clave) + 1;
				libros_cantidad.put(clave, valor);
			}
			
		}
		
		System.out.println(libros);
		
		DecimalFormat formateador = new DecimalFormat("####.##");
		
		for (Entry<Integer, Integer> l_c : libros_cantidad.entrySet()) {
			datos += "<tr><td>" + conexionBD.consultarId(l_c.getKey()).getIDLibro() + "</td>" +
						 "<td>" + conexionBD.consultarId(l_c.getKey()).getTitulo() +  "</td>" + 
						 "<td><a href='finalizarCompra.do?addid="+ conexionBD.consultarId(l_c.getKey()).getIDLibro() + "'>Añadir</a></td>" +
						 "<td><a href='finalizarCompra.do?removeid="+ conexionBD.consultarId(l_c.getKey()).getIDLibro() + "'>Eliminar</a></td>" + 
						 "<td>" + conexionBD.consultarId(l_c.getKey()).getPrecio() + "</td>" +
						 "<td>" + l_c.getValue() + "</td>" + 
						 "<td>" + formateador.format(conexionBD.consultarId(l_c.getKey()).getPrecio() * l_c.getValue()) + "</td>" +
					 "</tr>";
		}
		
		datos += "<tr><td></td><td></td><td></td><td></td><td></td><td>TOTAL</td><td>" + formateador.format(carrito.verImporte()) + "</td></tr>";
		
		datos += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td><a href ='./mostrartodo/comprar/aTPV.jsp'>FINALIZAR COMPRA</a></td></tr>";
		
		// ESCRIBIR EL HTML PARA LA PAGINA JSP
		try {
			this.getJspContext().getOut().append(datos);
		} catch (IOException e) {
			// ERROR EN PROCESO DE SALIDA
			e.printStackTrace();
		}
		
	}
	
}
