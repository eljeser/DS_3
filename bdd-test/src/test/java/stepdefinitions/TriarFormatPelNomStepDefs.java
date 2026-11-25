package stepdefinitions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.When;
import java.util.List;

import state.WorldState;

public class TriarFormatPelNomStepDefs {
    private final WorldState state;

    public TriarFormatPelNomStepDefs(WorldState state) {
        this.state = state;
    }

    @When("l'empresa exporta la imatge {string} de la tasca {string} sense indicar el format")
    public void lEmpresaExportaLaImatgeDeLaTascaSenseIndicarElFormat(String imatge, String tasca) {
        state.setResultat(state.getControlador().exportarImatge(imatge, tasca, null));
    }

    @When("l'empresa consulta els formats disponibles")
    public void lEmpresaConsultaElsFormatsDisponibles() {
        state.setResultat(String.join(", ",state.getControlador().obtenirFormatsDisponibles()));

    }
}
