package etiquetas;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RecomendacionDia implements ServletContextListener{
	
	private String recomendacion;
	private ServletContext aplicacion;	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		aplicacion = sce.getServletContext();
		recomendacion = (String) aplicacion.getInitParameter("Recomendacion");
		System.out.println(recomendacion);
		aplicacion.setAttribute("ad", recomendacion);
		
	}
	
}
