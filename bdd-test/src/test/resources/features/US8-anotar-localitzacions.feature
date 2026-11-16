Feature: US8 - Anotar localitzacions d'objecte en una imatge
  Com a anotadora amb una tasca d'imatges assignada
  Vull marcar on hi ha cada objecte amb una caixa i les seves categories
  Per localitzar els objectes de la imatge

  Background:
    Given l'empresa "faunavisio" està registrada
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

  Scenario: L'anotadora localitza un objecte
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    Then el sistema mostra un missatge "L'objecte s'ha localitzat amb èxit."
    And la imatge "i1" de la tasca "t-sabana" té 1 localitzacions

  Scenario: Una imatge pot tenir diverses localitzacions
    Given "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 451,327 al píxel 688,476 amb les categories "elefant"
    Then el sistema mostra un missatge "L'objecte s'ha localitzat amb èxit."
    And la imatge "i1" de la tasca "t-sabana" té 2 localitzacions
    And la dada "i1" de la tasca "t-sabana" no està marcada com a anotada

  Scenario: La caixa ha de cabre dins la imatge
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 700, 400 al píxel 800, 519 amb les categories "elefant"
    Then el sistema mostra un missatge "La localització no és vàlida."
    And la imatge "i1" de la tasca "t-sabana" té 0 localitzacions

  Scenario: El primer píxel ha de ser el superior esquerre
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 325, 347 al píxel 102, 136 amb les categories "arbre baobab"
    Then el sistema mostra un missatge "La localització no és vàlida."

  Scenario: Només es poden fer servir les categories de la tasca
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "lleó"
    Then el sistema mostra un missatge "Una o més categories no són permeses per aquesta tasca."
    And la imatge "i1" de la tasca "t-sabana" té 0 localitzacions

  Scenario: Cal indicar com a mínim una categoria
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories ""
    Then el sistema mostra un missatge "Falta l'atribut 'categories'."

  Scenario: Una altra anotadora no pot localitzar objectes en una tasca que no té assignada
    Given l'anotadora "berta" està registrada amb les experteses "Biologia"
    When "berta" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    Then el sistema mostra un missatge "La tasca no està assignada a aquesta anotadora."

  Scenario: No es pot localitzar en una imatge inexistent
    When "anna" localitza a la imatge "i9" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    Then el sistema mostra un missatge "No existeix cap dada amb aquest identificador a la tasca."

  Scenario: Les localitzacions compten com a anotació per marcar la imatge
    Given "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    When "anna" marca la dada "i1" de la tasca "t-sabana" com a anotada
    Then el sistema mostra un missatge "La dada s'ha marcat com a anotada. La tasca s'ha completat."

  Scenario: No es poden afegir localitzacions a una tasca completada
    Given "anna" ha localitzat a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 102, 136 al píxel 325, 347 amb les categories "arbre baobab"
    And "anna" ha marcat la dada "i1" de la tasca "t-sabana" com a anotada
    When "anna" localitza a la imatge "i1" de la tasca "t-sabana" la caixa del píxel 451, 327 al píxel 688, 476 amb les categories "elefant"
    Then el sistema mostra un missatge "La tasca ja està completada."
    And la imatge "i1" de la tasca "t-sabana" té 1 localitzacions
