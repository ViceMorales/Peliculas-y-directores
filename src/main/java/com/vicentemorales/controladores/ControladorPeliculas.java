package com.vicentemorales.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController 
@RequestMapping("/peliculas")
public class ControladorPeliculas {
	
	private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();
	public String getTitulo;
	public ControladorPeliculas() {
		listaPeliculas.put("Winnie the Pooh", "Don Hall");	
		listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
		listaPeliculas.put("Tarzán", "Kevin Lima");		
		listaPeliculas.put("Mulán", "Barry Cook");
		listaPeliculas.put("Oliver", "Kevin Lima");	
		listaPeliculas.put("Big Hero 6", "Don Hall");	
	}
	
	@GetMapping("")
    public List<Pelicula> obtenerTodasLasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        Set<String> nombres = listaPeliculas.keySet();
        for (String clave : nombres) {
            peliculas.add(new Pelicula(clave, listaPeliculas.get(clave)));
        }

        return peliculas;
    }
	

	@GetMapping("/{nombre}")
	public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
		String director = listaPeliculas.get(nombre);
		    if(director != null) {
		    	return "La pelicula " + nombre + " fue dirigida por " + director;
		   } else {
			   return "La pelicula  no se encuentra en nuestra lista";
		   }
		}
	
	@GetMapping("/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        List<String> peliculas = new ArrayList<>();

        for (Map.Entry<String, String> entry : listaPeliculas.entrySet()) {
            if (entry.getValue().equals(nombre)) {
                peliculas.add(entry.getKey());
            }
        }

        if (!peliculas.isEmpty()) {
            return "Películas dirigidas por " + nombre + ": " + String.join(" - ", peliculas);
        } else {
            return "No contamos con películas con ese director en nuestra lista.";
        }
    }


	public class Pelicula {
	    private String titulo;
	    private String director;
	
	   
	    public Pelicula(String titulo, String director) {
	        this.titulo = titulo;
	        this.director = director;
	    }
	    
	public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

	}
}