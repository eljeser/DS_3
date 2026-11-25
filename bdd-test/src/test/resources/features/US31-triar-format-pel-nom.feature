Feature: US31 - Triar el format pel nom
  Com a empresa
  Vull indicar el format pel seu nom i consultar quins hi ha disponibles
  Per no haver de saber res de com s'implementen

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

  Scenario: El nom del format no distingeix majúscules de minúscules
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "yolo"
    Then el resultat és:
      """
      arbre baobab [0.267500, 0.465385, 0.280000, 0.407692]
      elefant [0.712500, 0.773077, 0.297500, 0.288462]
      """

  Scenario: Els espais del voltant del nom no compten
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format " Coco "
    Then el resultat és:
      """
      arbre baobab [102, 136, 224, 212]
      elefant [451, 327, 238, 150]
      """

  Scenario: Si l'empresa no indica cap format, s'utilitza el per defecte
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" sense indicar el format
    Then el resultat és:
      """
      arbre baobab [102, 136, 224, 212]
      elefant [451, 327, 238, 150]
      """

  Scenario: L'empresa consulta els formats disponibles
    When l'empresa consulta els formats disponibles
    Then el sistema mostra un missatge "YOLO, TF, VOC, COCO"

  Scenario: Un format que no existeix és un error comprensible
    When l'empresa exporta la imatge "i1" de la tasca "t-sabana" en format "YOLOv8"
    Then el sistema mostra un missatge "El format demanat no existeix."
