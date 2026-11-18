package stepdefinitions;

import edu.ub.ds2627.anotub.controlador.MessagesCAT;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import state.WorldState;

// imports necessaris per la classe auxiliar
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnotarLocalitzacionsStepDefs {

    private final WorldState state;

    public AnotarLocalitzacionsStepDefs(WorldState state) {
        this.state = state;
    }

    @Given("l'empresa {string} està registrada")
    public void lEmpresaEstaRegistrada(String empresa) {
        if (!state.getControlador().estaEmpresaRegistrada(empresa)) {
            String resultat = state.getControlador().registrarEmpresa(
                    empresa,
                    "Contrasenya123!",
                    "empresa@gmail.com",
                    "123456789",
                    empresa,
                    "Suissa",
                    "12345678A"
            );
            assertEquals(MessagesCAT.RegistreEmpresaCorrecte.getMessage(), resultat);
        }
        assertTrue(state.getControlador().estaEmpresaRegistrada(empresa));
    }

    @And("existeix la tasca d'imatges {string} publicada per {string}:")
    public void existeixLaTascaDImatgesPublicadaPer(String tasca, String empresa, DataTable dataTable) {
        Map<String, String> dades = dataTable.asMap(String.class, String.class);

        // Treballem sobre la taula de dades per aconseguir cada una de les dades per separat per separat
        String titol = dades.get("titol");
        List<String> instruccions = separarDades(dades.get("instruccions"));
        List<String> experteses = separarDades(dades.get("experteses"));
        double remuneracioPerDada = Double.parseDouble(dades.get("remuneracioPerDada"));
        LocalDate dataLimit = LocalDate.parse(dades.get("dataLimit"));
        List<String> categories = separarDades(dades.get("categories"));
        List<String> imatges = separarDades(dades.get("imatges"));

        // Publiquem l'especificació de la tasca
        state.getControlador().publicarEspecificacio(titol, experteses, categories, instruccions, remuneracioPerDada);

        // Registrem la tasca
        state.getControlador().registrarTascaImatges(tasca, empresa, titol, dataLimit);

        // Afegim una imatge a la tasca
        String[] infoImatge = dades.get("imatges").split(":");
        String titolImatge = infoImatge[0];
        String[] dimensions = infoImatge[1].split("x");

        int amplada = Integer.parseInt(dimensions[0]);
        int alcada = Integer.parseInt(dimensions[1].trim());

        state.getControlador().afegirImatgeTasca(tasca, titolImatge, amplada, alcada);
    }

    // Mètode auxiliar per separar els elements d'una mateixa fila
    private List<String> separarDades(String valor) {
        return Arrays.stream(valor.split(";")).map(String::trim).toList();
    }

    @And("l'anotadora {string} està registrada amb les experteses {string}")
    public void lAnotadoraEstaRegistradaAmbLesExperteses(String anotadora, String expertesa) {
        if (!state.getControlador().estaRegistrat(anotadora)) {
            String resultat = state.getControlador().registrarUsuari(
                    anotadora,
                    "Contrasenya123!",
                    anotadora,
                    "Cognoms",
                    "ESP",
                    "ABC123456",
                    LocalDate.parse("2030-12-31"),
                    "ES9121000418450200051332",
                    "CAIXESBBXXX",
                    anotadora + "@example.com",
                    "600123123",
                    List.of(expertesa)
            );
            assertEquals(MessagesCAT.RegistreAnotadoraCorrecte.getMessage(), resultat);
        }
        assertTrue(state.getControlador().estaRegistradaAmbLesExperteses(anotadora, expertesa));
    }

    @And("{string} s'ha assignat la tasca {string}")
    public void sHaAssignatLaTasca(String anotadora, String tasca) {
        if (!state.getControlador().estaTascaAssignadaA(tasca, anotadora)){
            String resultat = state.getControlador().assignarTasca(anotadora, tasca);
            assertEquals(MessagesCAT.TascaAssignadaCorrectament.getMessage(), resultat);
        };
        assertTrue(state.getControlador().estaTascaAssignadaA(tasca, anotadora));
    }

    @When("{string} localitza a la imatge {string} de la tasca {string} la caixa del píxel {int}, {int} al píxel {int}, {int} amb les categories {string}")
    public void localitzaALaImatgeDeLaTascaLaCaixaDelPixelAlPixelAmbLesCategories(String anotadora, String imatge,
                                                                                  String tasca, int xInicial,
                                                                                  int yIncial, int xFi, int yFi,
                                                                                  String categories) {
        List<String> categoriesList = separarDades(categories);
        state.setResultat(state.getControlador().anotarLocalitzacio(anotadora, tasca, imatge, xInicial, yIncial, xFi, yFi, categoriesList));
    }

    @And("la imatge {string} de la tasca {string} té {int} localitzacions")
    public void laImatgeDeLaTascaTéLocalitzacions(String imatge, String tasca, int nombreLocalitzacions) {
        int nombreLocalitzacionsObtingudes = state.getControlador().obtenirNombreLocalitzacions(tasca, imatge);
        assertTrue(nombreLocalitzacionsObtingudes == nombreLocalitzacions);
    }


    @Given("{string} ha localitzat a la imatge {string} de la tasca {string} la caixa del píxel {int}, {int} al píxel {int}, {int} amb les categories {string}")
    public void haLocalitzatALaImatgeDeLaTascaLaCaixaDelPíxelAlPíxelAmbLesCategories(String anotadora, String imatge, String tasca, int xInicial, int yInicial, int xFi, int yFi, String categories) {
        String resultat = state.getControlador().anotarLocalitzacio(anotadora, tasca, imatge, xInicial, yInicial, xFi, yFi, separarDades(categories));
        assertEquals(MessagesCAT.LocalitzacioAssignadaCorrectament.getMessage(), resultat);
    }

    @When("{string} localitza a la imatge {string} de la tasca {string} la caixa del píxel {double} al píxel {double} amb les categories {string}")
    public void localitzaALaImatgeDeLaTascaLaCaixaDelPíxelAlPíxelAmbLesCategories(String anotadora, String imatge, String tasca, int xInicial, int yInicial, int xFi, int yFi, String categories) {
        state.setResultat(state.getControlador().anotarLocalitzacio(anotadora, tasca, imatge, xInicial, yInicial, xFi, yFi, separarDades(categories)));
    }

    @And("la dada {string} de la tasca {string} no està marcada com a anotada")
    public void laDadaDeLaTascaNoEstàMarcadaComAAnotada(String titolDada, String tasca) {
        assertTrue(!state.getControlador().estaDadaAnotada(tasca, titolDada));
    }

    @When("{string} marca la dada {string} de la tasca {string} com a anotada")
    public void marcaLaDadaDeLaTascaComAAnotada(String anotadora, String titol, String tasca) {
        state.setResultat(state.getControlador().marcarDadaAnotada(anotadora, tasca, titol));
    }

    @And("{string} ha marcat la dada {string} de la tasca {string} com a anotada")
    public void haMarcatLaDadaDeLaTascaComAAnotada(String anotadora, String titol, String tasca) {
        String resultat = state.getControlador().marcarDadaAnotada(anotadora, tasca, titol);
        assertEquals(MessagesCAT.DadaAnotadaCorrectament.getMessage(), resultat);
    }


}
