package modelo;

// ============================================================
//  MODELO - WonderWoman.java
//  Personaje principal controlado por el jugador
// ============================================================

public class WonderWoman extends Personaje
{
    // Constante pública para que el controlador sepa el máximo real
    public static final int SALUD_MAX = 100;

    // Daño base y daño en furia, como constantes únicas (antes estaban
    // duplicadas y distintas entre WonderWoman.java y CombateControlador)
    public static final int DANIO_NORMAL = 15;
    public static final int DANIO_FURIA  = 30;

    private String   origen;
    private String[] habilidades;
    private String[] equipo;
    private boolean  modoFuria;

    // El escudo activa un flag que mitiga el SIGUIENTE ataque recibido
    private boolean escudoActivo;

    public WonderWoman()
    {
        super("Princesa Diana", SALUD_MAX, 10);
        this.origen       = "Themyscira";
        this.modoFuria    = false;
        this.escudoActivo = false;
        this.habilidades  = new String[]
        {
            "Vuelo",
            "Super fuerza",
            "Golpe divino"
        };
        this.equipo = new String[]
        {
            "Lazo de la Verdad",
            "Escudo de Atenea",
            "Brazaletes"
        };
    }

    @Override
    public void atacar()
    {
        int danio = modoFuria ? DANIO_FURIA : DANIO_NORMAL;
        System.out.println(nombre + " ataca con fuerza " + danio + "!");
    }

    /**
     * Recibe daño: si el escudo está activo bloquea el ataque completo
     * y desactiva el escudo; de lo contrario aplica el daño normalmente.
     * Devuelve el daño real recibido (0 si fue bloqueado).
     */
    @Override
    public void recibirDanio(int cantidad)
    {
        if (escudoActivo)
        {
            // Bloqueo total del siguiente ataque
            escudoActivo = false;
            System.out.println(nombre + " bloquea el ataque con el Escudo de Atenea!");
            // No se reduce la salud
        }
        else
        {
            super.recibirDanio(cantidad);
        }
    }

    /**
     * Versión que además informa si fue bloqueado (útil para el log).
     * Devuelve true si el escudo absorbió el golpe.
     */
    public boolean recibirDanioConInfo(int cantidad)
    {
        if (escudoActivo)
        {
            escudoActivo = false;
            return true; // bloqueado
        }
        super.recibirDanio(cantidad);
        return false;
    }

    public void activarFuria()
    {
        modoFuria = true;
        System.out.println(nombre + " entra en modo furia!");
    }

    public void activarEscudo()
    {
        escudoActivo = true;
        System.out.println(nombre + " activa el Escudo de Atenea!");
    }

    public void usarHabilidad(String nombreHabilidad)
    {
        System.out.println(nombre + " usa: " + nombreHabilidad);
    }

    public void usarObjeto(String objeto)
    {
        System.out.println(nombre + " usa el objeto: " + objeto);
    }

    // Getters
    public boolean  isModoFuria()    { return modoFuria;    }
    public boolean  isEscudoActivo() { return escudoActivo; }
    public String   getOrigen()      { return origen;       }
    public String[] getHabilidades() { return habilidades;  }
    public String[] getEquipo()      { return equipo;       }
}
