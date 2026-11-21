Feature: US34 - Lliurar una tasca completada
  Com a empresa
  Vull descarregar les anotacions d'una tasca completada en el format que triï
  Per entrenar el meu model

  El lliurament és un text amb el format, les categories numerades des de 0,
  una línia per caixa i etiqueta, i el CRC32 de totes les línies anteriors.

  Background:
    Given l'empresa "faunavisio" està registrada
    And l'empresa "medtech" està registrada
    And existeix la tasca d'imatges "t-sabana" publicada per "faunavisio":
      | titol              | Fauna de la sabana          |
      | instruccions       | Marca els arbres i animals  |
      | experteses         | Biologia                    |
      | remuneracioPerDada | 0.50                        |
      | dataLimit          | 2030-12-31                  |
      | categories         | arbre baobab;elefant        |
      | imatges            | i1:800x520                  |
    And l'anotadora "anna" està registrada amb les experteses "Biologia"
    And "anna" s'ha assignat la tasca "t-sabana"
    And "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    And "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 451, 327 al píxel 688, 476 amb les categories "elefant"

  Scenario: L'empresa rep el lliurament en YOLO
    Given "anna" ha marcat la dada "i1" de la tasca "t-sabana" com a anotada
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-sabana" en format "YOLO"
    Then el resultat és:
      """
      FORMAT YOLO
      CATEGORIES arbre baobab;elefant
      i1 0 0.267500 0.465385 0.280000 0.407692
      i1 1 0.712500 0.773077 0.297500 0.288462
      CRC32 08c9ee39
      """

  Scenario: Sense indicar el format, el lliurament és en el format per defecte
    Given "anna" ha marcat la dada "i1" de la tasca "t-sabana" com a anotada
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-sabana" sense indicar el format
    Then el resultat és:
      """
      FORMAT COCO
      CATEGORIES arbre baobab;elefant
      i1 0 102 136 224 212
      i1 1 451 327 238 150
      CRC32 63bcb3bb
      """

  Scenario: El lliurament en el format del quart client (TF)
    Given "anna" ha marcat la dada "i1" de la tasca "t-sabana" com a anotada
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-sabana" en format "TF"
    Then el resultat és:
      """
      FORMAT TF
      CATEGORIES arbre baobab;elefant
      i1 0 0.261538 0.127500 0.669231 0.407500
      i1 1 0.628846 0.563750 0.917308 0.861250
      CRC32 e25d9283
      """


  Scenario: Una altra empresa no pot rebre el lliurament
    Given "anna" ha marcat la dada "i1" de la tasca "t-sabana" com a anotada
    When l'empresa "medtech" demana el lliurament de la tasca "t-sabana" en format "YOLO"
    Then el sistema mostra un missatge "Aquesta tasca no és d'aquesta empresa."

  Scenario: Una tasca que no està completada no es pot lliurar
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-sabana" en format "YOLO"
    Then el sistema mostra un missatge "La tasca encara no està completada."

  Scenario: Una tasca de documents no es pot lliurar
    Given existeix la tasca de documents "t-text" publicada per "faunavisio":
      | titol              | Descripcions          |
      | instruccions       | Classifica el text    |
      | experteses         | Biologia              |
      | remuneracioPerDada | 0.10                  |
      | dataLimit          | 2030-12-31            |
      | categories         | arbre baobab;elefant  |
      | documents          | d1:Un baobab enorme   |
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-text" en format "YOLO"
    Then el sistema mostra un missatge "Aquesta tasca no té imatges."

  Scenario: Un format que no existeix és un error
    Given "anna" ha marcat la dada "i1" de la tasca "t-sabana" com a anotada
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-sabana" en format "PNG"
    Then el sistema mostra un missatge "El format demanat no existeix."

  Scenario: Una tasca inexistent no es pot lliurar
    When l'empresa "faunavisio" demana el lliurament de la tasca "t-cap" en format "YOLO"
    Then el sistema mostra un missatge "No existeix cap tasca amb aquest identificador."
