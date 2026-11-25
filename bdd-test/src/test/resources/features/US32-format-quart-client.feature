Feature: US32 - Afegir el format del quart client
  Com a administradora d'AnotUB
  Vull afegir el format del quart client del meu grup sense modificar les classes que l'utilitzen
  Per poder atendre nous clients ràpidament

  Background:
    Given l'empresa "faunavisio" està registrada
    And existeix la tasca d'imatges "t-sabana" publicada per "faunavisio":
      | titol              | Fauna de la sabana          |
      | instruccions       | Marca els arbres i animals  |
      | experteses         | Biologia                    |
      | remuneracioPerDada | 0.50                        |
      | dataLimit          | 2030-12-31                  |
      | categories         | arbre baobab;elefant        |
      | imatges            | i1:800x520;i2:640x480       |
    And l'anotadora "anna" està registrada amb les experteses "Biologia"
    And "anna" s'ha assignat la tasca "t-sabana"
    And "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    And "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 451, 327 al píxel 688, 476 amb les categories "elefant"

  Scenario: Exportar en el format del quart client (TF)
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "TF"
    Then el resultat és:
      """
      arbre baobab [0.261538, 0.127500, 0.669231, 0.407500]
      elefant [0.628846, 0.563750, 0.917308, 0.861250]
      """