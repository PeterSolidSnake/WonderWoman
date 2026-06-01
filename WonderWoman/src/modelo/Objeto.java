package modelo;


public class Objeto
{
    private String  nombre;
    private String  tipo;
    private String  efecto;
    private boolean disponible;

    // Cuánto reduce la fuerza del enemigo al usar el Lazo (solo aplica
    // a objetos de tipo "Arma"); el Escudo tiene efecto en WonderWoman
    private int reduccionFuerza;

    public Objeto(String nombre, String tipo, String efecto, int reduccionFuerza)
    {
        this.nombre          = nombre;
        this.tipo            = tipo;
        this.efecto          = efecto;
        this.disponible      = true;
        this.reduccionFuerza = reduccionFuerza;
    }

    // Constructor de compatibilidad para objetos sin reducción de fuerza
    public Objeto(String nombre, String tipo, String efecto)
    {
        this(nombre, tipo, efecto, 0);
    }

    public void usar()
    {
        if (disponible)
        {
            System.out.println("Se usa: " + nombre + " -> " + efecto);
            disponible = false;
        }
        else
        {
            System.out.println(nombre + " no está disponible.");
        }
    }

    public String getDescripcion()
    {
        return nombre + " (" + tipo + "): " + efecto;
    }

    // Getters
    public boolean estaDisponible()    { return disponible;      }
    public String  getNombre()         { return nombre;          }
    public String  getTipo()           { return tipo;            }
    public String  getEfecto()         { return efecto;          }
    public int     getReduccionFuerza(){ return reduccionFuerza; }
}
