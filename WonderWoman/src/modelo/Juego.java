package modelo;

import controlador.JuegoControlador;



public class Juego
{
    // ── Salud máxima de Ares (constante del modelo) ───────────
    public static final int SALUD_MAX_ARES = 200;

    // ── Personajes ────────────────────────────────────────────
    private WonderWoman diana;
    private Ares        ares;
    private Aliado      steve;

    // ── Objetos ───────────────────────────────────────────────
    private Objeto lazo;
    private Objeto escudo;

    // ── Escenario activo ──────────────────────────────────────
    private Escenario escenario;

    // ── Estado de la partida ──────────────────────────────────
    private int     turno;
    private boolean terminado;
    private boolean victoria;


    // ── Constructor: inicializa una partida nueva ─────────────
    public Juego()
    {
        reiniciar();
    }


        // ── Getters ───────────────────────────────────────────────
    public WonderWoman getDiana()     { return diana;     }
    public Ares        getAres()      { return ares;      }
    public Aliado      getSteve()     { return steve;     }
    public Objeto      getLazo()      { return lazo;      }
    public Objeto      getEscudo()    { return escudo;    }
    public Escenario   getEscenario() { return escenario; }
    public int         getTurno()     { return turno;     }
    public boolean     isTerminado()  { return terminado; }
    public boolean     isVictoria()   { return victoria;  }

    // ── Reinicia todos los campos a su estado inicial ─────────
    //    Llamado tanto en el constructor como en "Nuevo juego".
    public void reiniciar()
    {
        diana = new WonderWoman();
        ares  = new Ares();
        steve = new Aliado(
            "Steve Trevor",
            "Piloto aliado",
            new String[]
            {
                "Diana, debemos detener a Ares.",
                "¡Confío en ti, adelante!",
                "¡Ten cuidado, hay soldados!"
            }
        );

        lazo   = new Objeto("Lazo de la Verdad", "Arma",
                            "Revela debilidades del enemigo", 10);
        escudo = new Objeto("Escudo de Atenea",  "Defensa",
                            "Bloquea el próximo ataque divino");

        escenario = new Escenario("Campo de batalla", "Francia, 1918");
        escenario.agregarEnemigo(ares);

        turno     = 0;
        terminado = false;
        victoria  = false;
    }


    // ── Avanza el contador de turno y lo devuelve ─────────────
    public int avanzarTurno()
    {
        turno++;
        return turno;
    }


    // ── Marca el juego como terminado ─────────────────────────
    public void terminar(boolean victoria)
    {
        this.terminado = true;
        this.victoria  = victoria;
    }




    // ── Punto de entrada del programa ─────────────────────────
    public static void main(String[] args)
    {
        JuegoControlador controlador = new JuegoControlador();
        controlador.iniciar();
    }
}
