package modelo;

// ============================================================
//  MODELO - Ares.java
//  Jefe final del juego, extiende Enemigo
// ============================================================

public class Ares extends Enemigo
{
    private String[] poderesDivinos;
    private boolean  faseFinal;

    public Ares()
    {
        super("Ares", "Dios de la guerra", 200, 40, 1000);
        this.faseFinal      = false;
        this.poderesDivinos = new String[]
        {
            "Llamarada de guerra",
            "Control mental",
            "Tormenta divina"
        };
    }

    public void activarPoderDivino(String poder)
    {
        System.out.println("Ares activa poder divino: " + poder + "!");
    }

    public void activarFaseFinal()
    {
        faseFinal = true;
        System.out.println("Ares entra en fase final. ¡Cuidado!");
    }

    // Getters
    public boolean  isFaseFinal()      { return faseFinal;      }
    public String[] getPoderesDivinos() { return poderesDivinos; }
}
