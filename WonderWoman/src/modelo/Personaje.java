package modelo;

// ============================================================
//  MODELO - Personaje.java
//  Clase abstracta base para todos los personajes del juego
// ============================================================

public abstract class Personaje
{
    protected String nombre;
    protected int    salud;
    protected int    nivel;

    public Personaje(String nombre, int salud, int nivel)
    {
        this.nombre = nombre;
        this.salud  = salud;
        this.nivel  = nivel;
    }

    public abstract void atacar();

    public void mover(String direccion)
    {
        System.out.println(nombre + " se mueve hacia: " + direccion);
    }

    public String getEstado()
    {
        return nombre + " | Salud: " + salud + " | Nivel: " + nivel;
    }

    public boolean estaVivo()
    {
        return salud > 0;
    }

    public void recibirDanio(int cantidad)
    {
        salud -= cantidad;
        if (salud < 0)
        {
            salud = 0;
        }
    }

    // Getters
    public String getNombre() { return nombre; }
    public int    getSalud()  { return salud;  }
    public int    getNivel()  { return nivel;  }
}
