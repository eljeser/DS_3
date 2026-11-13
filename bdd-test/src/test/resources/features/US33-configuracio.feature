Feature: US33 - Canviar la configuració
  Com a administradora d'AnotUB
  Vull canviar el format per defecte i el valor de la regla del meu grup
  Perquè totes les parts d'AnotUB facin servir els mateixos valors

  Exercici 4 (opcional). La regla del vostre grup s'aplica en exportar les caixes d'una imatge (US30).

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
    And "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102,136 al píxel 325,347 amb les categories "arbre baobab"
    And "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 451,327 al píxel 688,476 amb les categories "elefant"

  Scenario: L'administradora canvia el format per defecte
    When l'administradora fixa el format per defecte "yolo"
    Then el sistema mostra un missatge "La configuració s'ha actualitzat amb èxit."
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" sense indicar el format
    Then el resultat és:
      """
      arbre baobab [0.267500, 0.465385, 0.280000, 0.407692]
      elefant [0.712500, 0.773077, 0.297500, 0.288462]
      """

  Scenario: El format per defecte ha d'existir
    When l'administradora fixa el format per defecte "PNG"
    Then el sistema mostra un missatge "El format demanat no existeix."
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" sense indicar el format
    Then el resultat és:
      """
      arbre baobab [102, 136, 224, 212]
      elefant [451, 327, 238, 150]
      """

  Scenario: Es descarten les caixes per sota de la mida mínima
    Given "anna" ha localitzat a la imatge "i2" de la tasca "t-sabana" la caixa del píxel 200,200 al píxel 202,202 amb les categories "elefant"
    When l'administradora fixa la mida mínima de caixa a 4
    And l'empresa exporta la imatge "i2" de la tasca "t-sabana" en format "COCO"
    Then el resultat és buit

  Scenario: Una caixa igual a la mida mínima sí que s'exporta
    Given "anna" ha localitzat a la imatge "i2" de la tasca "t-sabana" la caixa del píxel 200,200 al píxel 202,202 amb les categories "elefant"
    When l'administradora fixa la mida mínima de caixa a 3
    And l'empresa exporta la imatge "i2" de la tasca "t-sabana" en format "COCO"
    Then el resultat és:
      """
      elefant [200, 200, 3, 3]
      """

  Scenario: La mida mínima ha de ser com a mínim 1
    When l'administradora fixa la mida mínima de caixa a 0
    Then el sistema mostra un missatge "El valor de configuració no és vàlid."
