package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;


public class VistaJuegoSwing extends JFrame
{
    private static final Color COLOR_FONDO       = new Color(15, 10, 30);
    private static final Color COLOR_PANEL       = new Color(25, 18, 50);
    private static final Color COLOR_DORADO      = new Color(212, 175, 55);
    private static final Color COLOR_ROJO_ARES   = new Color(180, 30, 30);
    private static final Color COLOR_BARRA_DIANA = new Color(70, 130, 200);
    private static final Color COLOR_BARRA_ARES  = new Color(180, 30, 30);
    private static final Color COLOR_TEXTO       = new Color(230, 220, 200);
    private static final Color COLOR_LOG         = new Color(20, 14, 40);

    private static final Font FUENTE_TITULO = new Font("Serif",      Font.BOLD,  22);
    private static final Font FUENTE_LABEL  = new Font("SansSerif",  Font.BOLD,  13);
    private static final Font FUENTE_NORMAL = new Font("SansSerif",  Font.PLAIN, 12);
    private static final Font FUENTE_LOG    = new Font("Monospaced", Font.PLAIN, 12);
    private static final Font FUENTE_BOTON  = new Font("SansSerif",  Font.BOLD,  12);

    private JLabel       lblEscenario;
    private JLabel       lblSaludDianaNum;
    private JLabel       lblSaludAresNum;
    private JProgressBar barSaludDiana;
    private JProgressBar barSaludAres;
    private JLabel       lblModoFuria;
    private JLabel       lblTurno;
    private JTextArea    areaLog;
    private JLabel       lblNombreHablante;
    private JLabel       lblDialogo;

    public JButton btnAtacar;
    public JButton btnFuria;
    public JButton btnLazo;
    public JButton btnEscudo;
    public JButton btnDialogo;
    public JButton btnNuevoJuego;


    public VistaJuegoSwing()
    {
        configurarVentana();
        construirUI();
    }

    private void configurarVentana()
    {
        setTitle("Wonder Woman - Juego MVC");
        setSize(820, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(8, 8));
    }

    private void construirUI()
    {
        add(crearPanelTitulo(),  BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelTitulo()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        JLabel lblTitulo = new JLabel("⚔  WONDER WOMAN", SwingConstants.LEFT);
        lblTitulo.setFont(FUENTE_TITULO);
        lblTitulo.setForeground(COLOR_DORADO);

        lblEscenario = new JLabel("Escenario: Themyscira", SwingConstants.RIGHT);
        lblEscenario.setFont(FUENTE_LABEL);
        lblEscenario.setForeground(COLOR_TEXTO);

        panel.add(lblTitulo,    BorderLayout.WEST);
        panel.add(lblEscenario, BorderLayout.EAST);
        return panel;
    }

    private JPanel crearPanelCentral()
    {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(6, 10, 4, 10));

        panel.add(crearPanelVidas(),   BorderLayout.NORTH);
        panel.add(crearPanelLog(),     BorderLayout.CENTER);
        panel.add(crearPanelDialogo(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelVidas()
    {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

        // Diana
        JPanel panelDiana = new JPanel(new BorderLayout(4, 4));
        panelDiana.setBackground(COLOR_PANEL);
        panelDiana.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_DORADO, 1),
                "Diana Prince", 0, 0, FUENTE_LABEL, COLOR_DORADO));

        lblSaludDianaNum = new JLabel("100 / 100", SwingConstants.RIGHT);
        lblSaludDianaNum.setFont(FUENTE_NORMAL);
        lblSaludDianaNum.setForeground(COLOR_TEXTO);

        barSaludDiana = new JProgressBar(0, 100);
        barSaludDiana.setValue(100);
        barSaludDiana.setForeground(COLOR_BARRA_DIANA);
        barSaludDiana.setBackground(new Color(40, 40, 60));
        barSaludDiana.setBorderPainted(false);
        barSaludDiana.setPreferredSize(new Dimension(0, 18));

        lblModoFuria = new JLabel("Modo furia: OFF", SwingConstants.LEFT);
        lblModoFuria.setFont(FUENTE_NORMAL);
        lblModoFuria.setForeground(new Color(150, 150, 180));

        JPanel infoDiana = new JPanel(new BorderLayout());
        infoDiana.setBackground(COLOR_PANEL);
        infoDiana.add(lblModoFuria,     BorderLayout.WEST);
        infoDiana.add(lblSaludDianaNum, BorderLayout.EAST);

        panelDiana.add(barSaludDiana, BorderLayout.CENTER);
        panelDiana.add(infoDiana,     BorderLayout.SOUTH);

        // Ares
        JPanel panelAres = new JPanel(new BorderLayout(4, 4));
        panelAres.setBackground(COLOR_PANEL);
        panelAres.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_ROJO_ARES, 1),
                "Ares - Dios de la guerra", 0, 0, FUENTE_LABEL, COLOR_ROJO_ARES));

        lblSaludAresNum = new JLabel("200 / 200", SwingConstants.RIGHT);
        lblSaludAresNum.setFont(FUENTE_NORMAL);
        lblSaludAresNum.setForeground(COLOR_TEXTO);

        barSaludAres = new JProgressBar(0, 200);
        barSaludAres.setValue(200);
        barSaludAres.setForeground(COLOR_ROJO_ARES);
        barSaludAres.setBackground(new Color(40, 40, 60));
        barSaludAres.setBorderPainted(false);
        barSaludAres.setPreferredSize(new Dimension(0, 18));

        lblTurno = new JLabel("Turno: 0", SwingConstants.LEFT);
        lblTurno.setFont(FUENTE_NORMAL);
        lblTurno.setForeground(new Color(150, 150, 180));

        JPanel infoAres = new JPanel(new BorderLayout());
        infoAres.setBackground(COLOR_PANEL);
        infoAres.add(lblTurno,        BorderLayout.WEST);
        infoAres.add(lblSaludAresNum, BorderLayout.EAST);

        panelAres.add(barSaludAres, BorderLayout.CENTER);
        panelAres.add(infoAres,     BorderLayout.SOUTH);

        panel.add(panelDiana);
        panel.add(panelAres);
        return panel;
    }

    private JPanel crearPanelLog()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);

        JLabel lblLogTitulo = new JLabel("  Registro de combate");
        lblLogTitulo.setFont(FUENTE_LABEL);
        lblLogTitulo.setForeground(new Color(160, 150, 200));

        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setBackground(COLOR_LOG);
        areaLog.setForeground(new Color(180, 220, 180));
        areaLog.setFont(FUENTE_LOG);
        areaLog.setLineWrap(true);
        areaLog.setWrapStyleWord(true);
        areaLog.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JScrollPane scroll = new JScrollPane(areaLog);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(60, 50, 90)));
        scroll.getVerticalScrollBar().setBackground(COLOR_LOG);

        panel.add(lblLogTitulo, BorderLayout.NORTH);
        panel.add(scroll,       BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelDialogo()
    {
        JPanel panel = new JPanel(new BorderLayout(6, 0));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 60, 100)),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panel.setPreferredSize(new Dimension(0, 50));

        lblNombreHablante = new JLabel("...");
        lblNombreHablante.setFont(FUENTE_LABEL);
        lblNombreHablante.setForeground(COLOR_DORADO);
        lblNombreHablante.setPreferredSize(new Dimension(140, 0));

        lblDialogo = new JLabel("...");
        lblDialogo.setFont(FUENTE_NORMAL);
        lblDialogo.setForeground(COLOR_TEXTO);

        panel.add(lblNombreHablante, BorderLayout.WEST);
        panel.add(lblDialogo,        BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelBotones()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createMatteBorder(
                1, 0, 0, 0, new Color(70, 60, 100)));

        btnAtacar     = crearBoton("⚔  Atacar",     COLOR_DORADO,            COLOR_FONDO);
        btnFuria      = crearBoton("🔥 Furia",       new Color(220, 80, 20),  Color.WHITE);
        btnLazo       = crearBoton("🌀 Lazo",        new Color(60, 140, 200), Color.WHITE);
        btnEscudo     = crearBoton("🛡  Escudo",     new Color(50, 130, 80),  Color.WHITE);
        btnDialogo    = crearBoton("💬 Hablar",      new Color(120, 80, 180), Color.WHITE);
        btnNuevoJuego = crearBoton("↺  Nuevo juego", new Color(60, 60, 80),   COLOR_TEXTO);

        panel.add(btnAtacar);
        panel.add(btnFuria);
        panel.add(btnLazo);
        panel.add(btnEscudo);
        panel.add(btnDialogo);
        panel.add(btnNuevoJuego);
        return panel;
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor)
    {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(fondo.darker()),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        boton.setPreferredSize(new Dimension(120, 36));
        return boton;
    }

    // ── Métodos públicos para el Controlador ──────────────────

    public void actualizarSaludDiana(int salud, int maxSalud)
    {
        barSaludDiana.setMaximum(maxSalud);
        barSaludDiana.setValue(Math.max(salud, 0));
        lblSaludDianaNum.setText(Math.max(salud, 0) + " / " + maxSalud);
        barSaludDiana.setForeground(
                salud <= maxSalud * 0.25 ? new Color(220, 60, 60) : COLOR_BARRA_DIANA);
    }

    public void actualizarSaludAres(int salud, int maxSalud)
    {
        barSaludAres.setMaximum(maxSalud);
        barSaludAres.setValue(Math.max(salud, 0));
        lblSaludAresNum.setText(Math.max(salud, 0) + " / " + maxSalud);
    }

    public void actualizarEscenario(String nombre, String ubicacion)
    {
        lblEscenario.setText("Escenario: " + nombre + "  ·  " + ubicacion);
    }

    public void actualizarModoFuria(boolean activo)
    {
        if (activo)
        {
            lblModoFuria.setText("🔥 FURIA ACTIVA");
            lblModoFuria.setForeground(new Color(240, 120, 30));
        }
        else
        {
            lblModoFuria.setText("Modo furia: OFF");
            lblModoFuria.setForeground(new Color(150, 150, 180));
        }
    }

    public void actualizarTurno(int turno)
    {
        lblTurno.setText("Turno: " + turno);
    }

    public void escribirLog(String mensaje)
    {
        areaLog.append(mensaje + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    public void escribirLogDestacado(String mensaje)
    {
        areaLog.append(">>> " + mensaje + " <<<\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    public void limpiarLog()
    {
        areaLog.setText("");
    }

    public void mostrarDialogo(String personaje, String texto)
    {
        lblNombreHablante.setText(personaje + ":");
        lblDialogo.setText(texto);
    }

    public void habilitarBotones(boolean estado)
    {
        btnAtacar.setEnabled(estado);
        btnFuria.setEnabled(estado);
        btnLazo.setEnabled(estado);
        btnEscudo.setEnabled(estado);
        btnDialogo.setEnabled(estado);
    }

    public void deshabilitarBoton(JButton boton)
    {
        boton.setEnabled(false);
        boton.setBackground(new Color(50, 50, 60));
    }

    public void mostrar()
    {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}
