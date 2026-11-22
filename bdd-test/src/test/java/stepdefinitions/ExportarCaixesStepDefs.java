package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

public class ExportarCaixesStepDefs {

    private final WorldState state;

    public ExportarCaixesStepDefs(WorldState state) {
        this.state = state;
    }

    @When("l'empresa exporta la imatge {string} de la tasca {string} en format {string}")
    public void lEmpresaExportaLaImatgeDeLaTascaEnFormat(String idImg, String idTasca, String format) {
        state.setResultat(state.getControlador().exportarImatge(idImg, idTasca, format));
    }

    @Then("el resultat és:")
    public void elResultatEs(String resultatEsperat) {

    }

    @Then("el resultat és buit")
    public void elResultatEsBuit() {

    }

    @Given("existeix la tasca de documents {string} publicada per {string}:")
    public void existeixLaTascaDeDocumentsPublicadaPer(String idTasca, String idEmpresa) {

    }
}
