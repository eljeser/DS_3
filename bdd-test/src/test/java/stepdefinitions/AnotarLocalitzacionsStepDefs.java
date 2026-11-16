package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import state.WorldState;

public class AnotarLocalitzacionsStepDefs {

    private final WorldState state;

    public AnotarLocalitzacionsStepDefs(WorldState state) {
        this.state = state;
    }

    @Given("l'empresa {string} està registrada")
    public void lEmpresaEstaRegistrada(String empresa) {
    }


    @And("existeix la tasca d'imatges {string} publicada per {string}:")
    public void existeixLaTascaDImatgesPublicadaPer(String tasca, String empresa, DataTable dataTable) {


    }

    @And("l'anotadora {string} està registrada amb les experteses {string}")
    public void lAnotadoraEstaRegistradaAmbLesExperteses(String anotadora, String expertesa) {

    }

    @And("{string} s'ha assignat la tasca {string}")
    public void sHaAssignatLaTasca(String anotadora, String tasca) {

    }

    @When("{string} localitza a la imatge {string} de la tasca {string} la caixa del píxel {int}, {int} al píxel {int}, {int} amb les categories {string}")
    public void localitzaALaImatgeDeLaTascaLaCaixaDelPixelAlPixelAmbLesCategories(String arg0, String arg1,
                                                                                  String arg2, int arg3,
                                                                                  int arg4, int arg5, int arg6,
                                                                                  String arg7) {

    }
}
