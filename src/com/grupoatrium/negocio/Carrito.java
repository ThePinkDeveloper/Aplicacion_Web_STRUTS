package com.grupoatrium.negocio;

import java.util.ArrayList;
import java.util.List;

import com.grupoatrium.modelo.Libro;

public class Carrito {
	
	private List<Integer> carrito = new ArrayList<Integer>();
	private GestionLibreria conexionBD = new GestionLibreria();
	private int size;
	
	public Carrito() {
		size = 0;
	}
	
	public void agregarLibro(int id) {
		carrito.add(id);
		size = carrito.size();
	}
	
	public double verImporte() {
		
		double total = 0;
		
		for (int i : carrito) {
			total += conexionBD.consultarId(i).getPrecio();
		}
		
		return total;
	}
	
	public void eliminarLibro(int id) {
		
		carrito.remove(new Integer(id));

		
		if (carrito != null) {
			size = carrito.size();
		} else {
			size = 0;
		}
	}

	public List<Integer> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<Integer> carrito) {
		this.carrito = carrito;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
