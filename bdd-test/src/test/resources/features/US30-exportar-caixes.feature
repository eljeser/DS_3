Feature: US30 - Exportar les caixes d'una imatge
  Com a empresa
  Vull obtenir totes les caixes d'una imatge en el format que faig servir
  Per poder-les carregar a la meva eina d'entrenament

  Una línia per caixa i etiqueta, amb els decimals del format: 6 a YOLO, 0 a COCO i a Pascal VOC.

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

  Scenario: Exportar en YOLO
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "YOLO"
    Then el resultat és:
      """
      arbre baobab [0.267500, 0.465385, 0.280000, 0.407692]
      elefant [0.712500, 0.773077, 0.297500, 0.288462]
      """

  Scenario: Exportar en COCO
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "COCO"
    Then el resultat és:
      """
      arbre baobab [102, 136, 224, 212]
      elefant [451, 327, 238, 150]
      """

  Scenario: Exportar en Pascal VOC
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "VOC"
    Then el resultat és:
      """
      arbre baobab [103, 137, 326, 348]
      elefant [452, 328, 689, 477]
      """

  Scenario: Una localització amb dues etiquetes genera dues línies
    Given "anna" ha localitzat a la imatge "i2" de la tasca "t-sabana" la caixa del píxel 10, 20 al píxel 49, 59 amb les categories "elefant;arbre baobab"
    When l'empresa exporta la imatge "i2" de la tasca "t-sabana" en format "COCO"
    Then el resultat és:
      """
      elefant [10, 20, 40, 40]
      arbre baobab [10, 20, 40, 40]
      """

  Scenario: Una imatge sense caixes dona un resultat buit
    When l'empresa exporta la imatge "i2" de la tasca "t-sabana" en format "YOLO"
    Then el resultat és buit

  Scenario: Un format que no existeix és un error
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "PNG"
    Then el sistema mostra un missatge "El format demanat no existeix."

  Scenario: Un format que no existeix és un error encara que la imatge no tingui caixes
    When l'empresa exporta la imatge "i2" de la tasca "t-sabana" en format "PNG"
    Then el sistema mostra un missatge "El format demanat no existeix."

  Scenario: No es pot exportar una imatge inexistent
    When l'empresa exporta la imatge "i9" de la tasca "t-sabana" en format "YOLO"
    Then el sistema mostra un missatge "No existeix cap dada amb aquest identificador a la tasca."

  Scenario: Una tasca de documents no té caixes per exportar
    Given existeix la tasca de documents "t-text" publicada per "faunavisio":
      | titol              | Descripcions          |
      | instruccions       | Classifica el text    |
      | experteses         | Biologia              |
      | remuneracioPerDada | 0.10                  |
      | dataLimit          | 2030-12-31            |
      | categories         | arbre baobab;elefant  |
      | documents          | d1:Un baobab enorme   |
    When l'empresa exporta la imatge "d1" de la tasca "t-text" en format "YOLO"
    Then el sistema mostra un missatge "Aquesta tasca no té imatges."
