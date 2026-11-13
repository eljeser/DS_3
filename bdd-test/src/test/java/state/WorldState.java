package state;

import edu.ub.ds2627.anotub.controlador.AppContainer;
import edu.ub.ds2627.anotub.controlador.Controlador;

public class WorldState {

  private final Controlador controlador;
  private String resultat;

  public WorldState() {
    // Afagem el controlador a través del contenidor d'aplicació
    this.controlador = new AppContainer().getControlador();
  }

  public Controlador getControlador() {
    return controlador;
  }

  public String getResultat() {
    return resultat;
  }

  public void setResultat(String resultat) {
    this.resultat = resultat;
  }
}
