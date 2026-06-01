package modelo;

// ============================================================
//  MODELO - Aliado.java
//  Personajes aliados con los que Diana puede interactuar
// ============================================================

public class Aliado extends Personaje
{
    private String   rol;
    private String[] dialogos;
    private int      indiceDialogo;

    public Aliado(String nombre, String rol, String[] dialogos)
    {
        super(nombre, 80, 3);
        this.rol           = rol;
        this.dialogos      = dialogos;
        this.indiceDialogo = 0;
    }

    @Override
    public void atacar()
    {
        System.out.println(nombre + " ayuda en combate!");
    }

    public String getSiguienteDialogo()
    {
        if (indiceDialogo < dialogos.length)
        {
            String linea = dialogos[indiceDialogo];
            indiceDialogo++;
            return linea;
        }
        return "(fin del diálogo)";
    }

    public void hablar()
    {
        System.out.println(nombre + ": " + getSiguienteDialogo());
    }

    public void ayudar()
    {
        System.out.println(nombre + " [" + rol + "] presta su ayuda.");
    }

    // Getter
    public String getRol() { return rol; }
}
