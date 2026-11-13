package state;

import edu.ub.ds2627.anotub.controlador.Controlador;
import edu.ub.ds2627.anotub.controlador.MessagesCAT;

import java.time.LocalDate;
import java.util.List;

// Dades de prova compartides pels StepDefs; tot es fa a través del Controlador
public final class DadesDeProva {
  public static final String CONTRASENYA = "Password123";
  public static final String EXPERTESA_GENERICA = "General";
  public static final String EMPRESA = "empresa_prova";
  public static final List<String> CATEGORIES_PER_DEFECTE = List.of("Positiu", "Negatiu");
  private static final LocalDate DATA_LIMIT = LocalDate.parse("2030-12-31");
  private static final int AMPLADA_IMATGE = 2;
  private static final int ALCADA_IMATGE = 2;
  private static final String ANOTADORA_AUXILIAR = "anotadora_auxiliar";

  private DadesDeProva() {
  }

  public static void registrarAnotadora(Controlador controlador, String nomUsuari, String cognoms,
                                        List<String> experteses) {
    controlador.eliminarUsuari(nomUsuari);
    controlador.registrarUsuari(
        nomUsuari,
        CONTRASENYA,
        nomUsuari,
        cognoms,
        "ESP",
        "ABC123456",
        LocalDate.parse("2030-12-31"),
        "ES9121000418450200051332",
        "CAIXESBBXXX",
        nomUsuari + "@example.com",
        "600123123",
        experteses
    );
  }

  public static void registrarTascaImatges(Controlador controlador, String idTasca, String titolEspecificacio,
                                           List<String> experteses) {
    registrarTascaImatges(controlador, idTasca, titolEspecificacio, experteses, CATEGORIES_PER_DEFECTE, 0.0);
  }

  public static void registrarTascaImatges(Controlador controlador, String idTasca, String titolEspecificacio,
                                           List<String> experteses, double remuneracioPerDada) {
    registrarTascaImatges(controlador, idTasca, titolEspecificacio, experteses, CATEGORIES_PER_DEFECTE,
        remuneracioPerDada);
  }

  public static void registrarTascaImatges(Controlador controlador, String idTasca, String titolEspecificacio,
                                           List<String> experteses, List<String> categories) {
    registrarTascaImatges(controlador, idTasca, titolEspecificacio, experteses, categories, 0.0);
  }

  // L'empresa publica l'especificació (si encara no existeix) i n'ofereix una tasca concreta
  public static void registrarTascaImatges(Controlador controlador, String idTasca, String titolEspecificacio,
                                           List<String> experteses, List<String> categories,
                                           double remuneracioPerDada) {
    if (!controlador.estaEmpresaRegistrada(EMPRESA)) {
      assertEquals(MessagesCAT.RegistreEmpresaCorrecte.getMessage(), controlador.registrarEmpresa(
          EMPRESA, CONTRASENYA, "empresa@example.com", "930000000", "Empresa de Prova", "ESP", "ESA12345678"));
    }
    if (!controlador.estaEspecificacioPublicada(titolEspecificacio)) {
      assertEquals(MessagesCAT.EspecificacioPublicadaCorrectament.getMessage(), controlador.publicarEspecificacio(
          titolEspecificacio, experteses, categories, List.of("Anotar cada dada segons l'especificació"),
          remuneracioPerDada));
    }
    assertEquals(MessagesCAT.TascaAfegidaCorrectament.getMessage(),
        controlador.registrarTascaImatges(idTasca, EMPRESA, titolEspecificacio, DATA_LIMIT));
  }

  public static void afegirImatge(Controlador controlador, String idTasca, String titol) {
    assertEquals(MessagesCAT.DadaAfegidaCorrectament.getMessage(),
        controlador.afegirImatgeTasca(idTasca, titol, AMPLADA_IMATGE, ALCADA_IMATGE));
  }

  // Una altra anotadora s'assigna la tasca, de manera que deixa d'estar disponible
  public static void ocuparTasca(Controlador controlador, String idTasca) {
    if (!controlador.estaRegistrat(ANOTADORA_AUXILIAR)) {
      registrarAnotadora(controlador, ANOTADORA_AUXILIAR, "Auxiliar", List.of(EXPERTESA_GENERICA));
    }
    controlador.assignarTasca(ANOTADORA_AUXILIAR, idTasca);
  }

  // Una altra anotadora s'assigna la tasca i n'anota l'única dada, de manera que queda completada
  public static void completarTasca(Controlador controlador, String idTasca) {
    afegirImatge(controlador, idTasca, "Dada " + idTasca);
    ocuparTasca(controlador, idTasca);
    controlador.marcarDadaAnotada(ANOTADORA_AUXILIAR, idTasca, "Dada " + idTasca);
  }
}
