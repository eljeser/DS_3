package stepdefinitions;

import edu.ub.ds2627.anotub.controlador.MessagesCAT;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(resultatEsperat, state.getResultat());
    }

    @Then("el resultat és buit")
    public void elResultatEsBuit() {
        assertEquals("", state.getResultat());
    }

    @Given("existeix la tasca de documents {string} publicada per {string}:")
    public void existeixLaTascaDeDocumentsPublicadaPer(String tasca, String empresa, DataTable dataTable) {
        Map<String, String> dades = dataTable.asMap(String.class, String.class);

        // Treballem sobre la taula de dades per aconseguir cada una de les dades per separat
        String titol = dades.get("titol");
        List<String> instruccions = separarDades(dades.get("instruccions"));
        List<String> experteses = separarDades(dades.get("experteses"));
        double remuneracioPerDada = Double.parseDouble(dades.get("remuneracioPerDada"));
        LocalDate dataLimit = LocalDate.parse(dades.get("dataLimit"));
        List<String> categories = separarDades(dades.get("categories"));
        List<String> documents = separarDades(dades.get("documents"));

        // Publiquem l'especificació de la tasca
        String resultat = state.getControlador().publicarEspecificacio(titol, experteses, categories, instruccions, remuneracioPerDada);
        assertEquals(MessagesCAT.EspecificacioPublicadaCorrectament.getMessage(), resultat);

        // Registrem la tasca de documents
        resultat = state.getControlador().registrarTascaDocuments(tasca, empresa, titol, dataLimit);
        assertEquals(MessagesCAT.TascaAfegidaCorrectament.getMessage(), resultat);

        // Afegim cada document a la tasca (format "titol:text")
        for (String document : documents) {
            String[] infoDocument = document.split(":", 2);
            String titolDocument = infoDocument[0].trim();
            String text = infoDocument[1].trim();

            resultat = state.getControlador().afegirDocumentTasca(tasca, titolDocument, text);
            assertEquals(MessagesCAT.DadaAfegidaCorrectament.getMessage(), resultat);
        }
    }

    // Mètode auxiliar per separar els elements d'una mateixa fila
    private List<String> separarDades(String valor) {
        return Arrays.stream(valor.split(";")).map(String::trim).toList();
    }
}
