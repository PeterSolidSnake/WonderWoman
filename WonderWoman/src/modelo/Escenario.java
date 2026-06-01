package modelo;

// ============================================================
//  MODELO - Escenario.java
//  Representa los lugares donde se desarrolla el juego
// ============================================================

public class Escenario
{
    private String    nombre;
    private String    ubicacion;
    private Enemigo[] enemigos;
    private int       cantidadEnemigos;

    public Escenario(String nombre, String ubicacion)
    {
        this.nombre           = nombre;
        this.ubicacion        = ubicacion;
        this.enemigos         = new Enemigo[10];
        this.cantidadEnemigos = 0;
    }

    public void cargar()
    {
        System.out.println("Escenario cargado: " + nombre + " en " + ubicacion);
    }

    public void agregarEnemigo(Enemigo enemigo)
    {
        if (cantidadEnemigos < enemigos.length)
        {
            enemigos[cantidadEnemigos] = enemigo;
            cantidadEnemigos++;
        }
    }

    // Getters
    public Enemigo[] getEnemigos()         { return enemigos;         }
    public String    getNombre()           { return nombre;           }
    public String    getUbicacion()        { return ubicacion;        }
    public int       getCantidadEnemigos() { return cantidadEnemigos; }
}
