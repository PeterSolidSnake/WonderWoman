package controlador;

import modelo.Juego;
import modelo.WonderWoman;
import modelo.Enemigo;
import modelo.Aliado;
import modelo.Objeto;
import vista.VistaJuegoSwing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// ── Controlador de Combate ────────────────────────────────────
class CombateControlador
{
    private VistaJuegoSwing vista;

    public CombateControlador(VistaJuegoSwing vista)
    {
        this.vista = vista;
    }

    public void procesarTurno(WonderWoman diana, Enemigo enemigo)
    {
        int danioDiana = diana.isModoFuria()
                ? WonderWoman.DANIO_FURIA
                : WonderWoman.DANIO_NORMAL;

        enemigo.recibirDanio(danioDiana);
        vista.escribirLog("Diana ataca → " + danioDiana
                + " de daño a " + enemigo.getNombre());

        if (enemigo.estaVivo())
        {
            int     danioEnemigo = enemigo.getFuerza() / 2;
            boolean bloqueado    = diana.recibirDanioConInfo(danioEnemigo);

            if (bloqueado)
            {
                vista.escribirLog(enemigo.getNombre()
                        + " ataca → ¡BLOQUEADO por el Escudo de Atenea! (0 daño)");
            }
            else
            {
                vista.escribirLog(enemigo.getNombre()
                        + " contraataca → " + danioEnemigo + " de daño a Diana");
            }
        }
        else
        {
            vista.escribirLogDestacado("¡" + enemigo.getNombre()
                    + " ha sido derrotado!");
        }
    }

    public void aplicarEfectoObjeto(WonderWoman diana, Objeto objeto,
                                    Enemigo enemigo)
    {
        if (!objeto.estaDisponible())
        {
            vista.escribirLog(objeto.getNombre() + " ya fue usado.");
            return;
        }

        objeto.usar();

        if (objeto.getTipo().equals("Defensa"))
        {
            diana.activarEscudo();
            vista.escribirLog("Diana usa: " + objeto.getNombre()
                    + " → El próximo ataque de Ares será bloqueado.");
        }
        else if (objeto.getTipo().equals("Arma") && objeto.getReduccionFuerza() > 0)
        {
            int reduccion = objeto.getReduccionFuerza();
            enemigo.reducirFuerza(reduccion);
            vista.escribirLog("Diana usa: " + objeto.getNombre()
                    + " → Fuerza de " + enemigo.getNombre()
                    + " reducida en " + reduccion + " puntos.");
        }
        else
        {
            vista.escribirLog("Diana usa: " + objeto.getNombre()
                    + " → " + objeto.getEfecto());
        }
    }
}


// ── Controlador de Diálogos ───────────────────────────────────
class DialogoControlador
{
    private VistaJuegoSwing vista;

    public DialogoControlador(VistaJuegoSwing vista)
    {
        this.vista = vista;
    }

    public void iniciarDialogo(Aliado aliado)
    {
        String linea = aliado.getSiguienteDialogo();
        vista.mostrarDialogo(aliado.getNombre(), linea);
        vista.escribirLog("[Diálogo] " + aliado.getNombre() + ": " + linea);
    }
}


// ── Controlador Principal ─────────────────────────────────────
public class JuegoControlador
{
    private Juego           juego;
    private VistaJuegoSwing vista;
    private CombateControlador combateCtrl;
    private DialogoControlador dialogoCtrl;


    public JuegoControlador()
    {
        juego       = new Juego();
        vista       = new VistaJuegoSwing();
        combateCtrl = new CombateControlador(vista);
        dialogoCtrl = new DialogoControlador(vista);
    }

    public void iniciar()
    {
        conectarBotones();
        sincronizarVista();
        vista.mostrarDialogo("Steve Trevor", "Diana, debemos detener a Ares.");
        vista.escribirLog("=== Juego iniciado ===");
        vista.escribirLog("Diana enfrenta a Ares en el campo de batalla.");
        vista.escribirLog("¡Usa tus habilidades y objetos para derrotarlo!");
        vista.escribirLog("─────────────────────────────────────");
        vista.mostrar();
    }

    private void conectarBotones()
    {
        vista.btnAtacar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) { accionAtacar(); }
        });
        vista.btnFuria.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) { accionActivarFuria(); }
        });
        vista.btnLazo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) { accionUsarLazo(); }
        });
        vista.btnEscudo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) { accionUsarEscudo(); }
        });
        vista.btnDialogo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) { accionHablarConSteve(); }
        });
        vista.btnNuevoJuego.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) { accionNuevoJuego(); }
        });
    }

    private void accionAtacar()
    {
        if (juego.isTerminado()) return;

        int turno = juego.avanzarTurno();
        vista.actualizarTurno(turno);
        vista.escribirLog("── Turno " + turno + " ──");

        combateCtrl.procesarTurno(juego.getDiana(), juego.getAres());
        sincronizarVista();

        if (!juego.getAres().estaVivo())       terminarJuego(true);
        else if (!juego.getDiana().estaVivo())  terminarJuego(false);
    }

    private void accionActivarFuria()
    {
        if (juego.isTerminado() || juego.getDiana().isModoFuria()) return;

        juego.getDiana().activarFuria();
        vista.actualizarModoFuria(true);
        vista.escribirLog("🔥 Diana activa el MODO FURIA. "
                + "Daño aumentado a " + WonderWoman.DANIO_FURIA + "!");
        vista.deshabilitarBoton(vista.btnFuria);
    }

    private void accionUsarLazo()
    {
        if (juego.isTerminado()) return;
        combateCtrl.aplicarEfectoObjeto(
                juego.getDiana(), juego.getLazo(), juego.getAres());
        if (!juego.getLazo().estaDisponible())
            vista.deshabilitarBoton(vista.btnLazo);
    }

    private void accionUsarEscudo()
    {
        if (juego.isTerminado()) return;
        combateCtrl.aplicarEfectoObjeto(
                juego.getDiana(), juego.getEscudo(), juego.getAres());
        if (!juego.getEscudo().estaDisponible())
            vista.deshabilitarBoton(vista.btnEscudo);
    }

    private void accionHablarConSteve()
    {
        dialogoCtrl.iniciarDialogo(juego.getSteve());
    }

    private void accionNuevoJuego()
    {
        juego.reiniciar();
        combateCtrl = new CombateControlador(vista);
        dialogoCtrl = new DialogoControlador(vista);

        vista.limpiarLog();
        vista.habilitarBotones(true);
        vista.actualizarModoFuria(false);
        vista.actualizarTurno(0);
        vista.mostrarDialogo("Steve Trevor", "Diana, debemos detener a Ares.");
        sincronizarVista();

        vista.escribirLog("=== Nuevo juego iniciado ===");
        vista.escribirLog("─────────────────────────────────────");
    }

    private void sincronizarVista()
    {
        vista.actualizarSaludDiana(juego.getDiana().getSalud(), WonderWoman.SALUD_MAX);
        vista.actualizarSaludAres(juego.getAres().getSalud(),   Juego.SALUD_MAX_ARES);
        vista.actualizarEscenario(
                juego.getEscenario().getNombre(),
                juego.getEscenario().getUbicacion());
    }

    private void terminarJuego(boolean victoria)
    {
        juego.terminar(victoria);
        vista.habilitarBotones(false);

        if (victoria)
        {
            vista.escribirLogDestacado("¡VICTORIA! Diana ha derrotado a Ares.");
            vista.mostrarDialogo("Diana Prince", "¡La paz ha sido restaurada!");
        }
        else
        {
            vista.escribirLogDestacado("DERROTA. Diana ha caído en combate.");
            vista.mostrarDialogo("Ares", "¡La guerra nunca terminará!");
        }
    }
}
