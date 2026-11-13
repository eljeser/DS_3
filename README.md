# p3-AnotUB

Projecte de la Pràctica 3 d'AnotUB, desenvolupat a partir del nostre repositori de la Pràctica 2 (marcat amb l'etiqueta `INICI P3`).

En aquesta pràctica continuarem millorant l'arquitectura d'AnotUB reconeixent problemes de disseny i aplicant **patrons de disseny** (Strategy, Factory, Façana i Singleton) juntament amb els principis GRASP i SOLID, per tal que el sistema pugui créixer i exportar anotacions sense haver de reescriure codi existent.

---

## 🎯 Objectius Principals
- **Identificar i aplicar patrons de disseny:** Implementar *Strategy*, *Factory*, *Façana* i *Singleton* (opcional) sobre codi existent i funcionalitats noves.
- **Mantenir els principis SOLID i GRASP:** Evitar el codi acoblat (com `switch` gegants o classes Déu) i respectar el principi Obert/Tancat (OCP) i de Responsabilitat Única (SRP).
- **Documentar i justificar:** Completar l'informe responent les preguntes teòriques, analitzant els rols de cada classe i adjuntant el Diagrama de Classes final amb els patrons marcats.

---

## 📋 Estat de les Històries d'Usuari (US)

### Base de Pràctiques Anteriors
- **US1 – US7 i US9**: Implementades i refactoritzades a la P1 i P2.
- **US8 (Anotar localitzacions d'objecte en una imatge)**: Passa a ser **obligatòria** (abans opcional). Cal assegurar que localitzar un objecte no marca la imatge com a completada automàticament, permetent afegir múltiples caixes abans de tancar-la (US6).

### Noves Històries d'Usuari (P3)
- **US30**: Exportar les caixes d'una imatge en el format desitjat (`COCO`, `YOLO`, `Pascal VOC`).
- **US31**: Triar el format d'exportació pel seu nom (insensible a majúscules/minúscules, `COCO` per defecte) i consultar els formats disponibles.
- **US32**: Afegir el format específic del nostre grup de laboratori sense modificar les classes que l'utilitzen.
- **US33** *(Opcional)*: Configurar globalment el format per defecte i la regla d'exportació del grup.
- **US34**: Generar el paquet de lliurament complet d'una tasca finalitzada amb verificació d'integritat (CRC32).

---

## 🛠️ Full de Ruta dels Exercicis

### Exercici 0: Posada a punt
1. Etiquetar el darrer commit de la P2 com a `INICI P3`.
2. Descarregar `US8-anotar-localitzacions.feature` i adaptar el codi perquè passi els tests.
3. Verificar que afegir caixes (US8) permet seguir editant la imatge fins que es marca explícitament com a anotada (US6).

### Exercici 1: Del `switch` a **Strategy** (US30)
- **Refactorització (SRP):** Extreure la geometria i validació de coordenades a la nova classe `CaixaEmbolcallant`.
- **Patró Strategy:** Substituir el bloc `switch` per la interfície `FormatCaixa` i crear les estratègies concretes per a `COCO`, `YOLO` i `Pascal VOC`.
- Implementar `Imatge.exportar(FormatCaixa)` retornant una llista de `LocalitzacioExportada`.

### Exercici 2: Nous formats amb **Factory** (US31 i US32)
- Crear `FormatCaixaFactory` per registrar i instanciar dinàmicament els formats pel seu nom, eliminant la dependència directa d'`AnotUB` respecte a les classes concretes.
- Implementar el nou format assignat al grup de laboratori:
    - **Grups A i B:** `TF` (TensorFlow: $[y_1/H, x_1/W, (y_2+1)/H, (x_2+1)/W]$, 6 decimals).
    - **Grups C i D:** `ALBUMENTATIONS` ($[x_1/W, y_1/H, (x_2+1)/W, (y_2+1)/H]$, 6 decimals).
    - **Grup F:** `XYXY` ($[x_1, y_1, x_2+1, y_2+1]$, 0 decimals).

### Exercici 3: Paquet de lliurament amb **Façana** (US34)
- Dissenyar un subsistema acoblat mitjançant una **Façana** (o patró equivalent justificat) per no sobrecarregar `AnotUB` ni el `Controlador`.
- Encapsular les 4 responsabilitats del lliurament:
    1. Validar la petició (tasca completada, d'imatges i propietat de l'empresa).
    2. Indexar les categories utilitzades.
    3. Generar el text estructurat amb les línies de cada imatge i caixa.
    4. Segellar el resultat calculant el hash hexadecimal **CRC32** (8 dígits en UTF-8).

### Exercici 4 *(Opcional)*: Configuració global amb **Singleton** (US33)
- Implementar la classe `ConfiguracioAnotUB` seguint el patró **Singleton** per gestionar el format per defecte i la regla de filtratge/exportació del grup:
    - **Grups A i B:** `midaMinimaCaixa` (per defecte `1`).
    - **Grups C i D:** `incloureImatgesBuides` (per defecte `true`).
    - **Grup F:** `unaEtiquetaPerCaixa` (per defecte `false`).

---

## 📦 Lliurament i Avaluació
- **Repositori GitHub:** El darrer commit ha d'estar etiquetat com a `VERSIO FINAL PRÀCTICA 3` (o `Lliurament de la pràctica 3`).
- **Campus Virtual (1 lliurament per equip):**
    1. Document de l'enunciat amb totes les preguntes respostes i el **Diagrama de Classes** exportat en format **PDF**.
    2. Fitxer `.zip` amb el codi complet del projecte.
- **Avaluació:** Es valorarà el lliurament conjuntament amb un qüestionari individual a la sessió de laboratori.