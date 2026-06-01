package modelo;


public class Enemigo extends Personaje
{
    private String tipo;
    private int    fuerza;
    private int    recompensa;

    public Enemigo(String nombre, String tipo, int salud, int fuerza, int recompensa)
    {
        super(nombre, salud, 1);
        this.tipo       = tipo;
        this.fuerza     = fuerza;
        this.recompensa = recompensa;
    }

    @Override
    public void atacar()
    {
        System.out.println(nombre + " [" + tipo + "] ataca con fuerza " + fuerza + "!");
    }

    public void morir()
    {
        System.out.println(nombre + " ha sido derrotado. Recompensa: " + recompensa + " puntos.");
    }

    // Reduce la fuerza del enemigo (usado por el Lazo de la Verdad)
    public void reducirFuerza(int cantidad)
    {
        fuerza -= cantidad;
        if (fuerza < 0) fuerza = 0;
    }

    // Getters
    public String getTipo()       { return tipo;       }
    public int    getFuerza()     { return fuerza;     }
    public int    getRecompensa() { return recompensa; }
}
