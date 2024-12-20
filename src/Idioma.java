/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author raull
 */
public enum Idioma {
    Espanol ("Español"), Ingles("Inglés"), Catalan("Catalán"), Euskera("Esukera");
    
    private String nombre;
    
    private Idioma(String nombre){
        this.nombre = nombre;
    }
    
    public String getIdioma(){
        return this.nombre;
    }
    
    public int equalsIdioma(String idioma){
        return this.nombre.compareTo(idioma);
    }
    
}
