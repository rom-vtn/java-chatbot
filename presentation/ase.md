---
marp: true
header: "ASE SoSe 2024"
footer: "2024-07-03"
paginate: true
math: katex
---
<style>
    img {
        text-align: center;
    }
</style>


# Einen offen***er***en selbst gehosteten Chatbot machen
## Automatisierte Softwareentwicklung, SoSe 2024 ⚙

> Viton Romane, DFIW, 5006508
> Chendjou Dzogouloh Arold Stephyl, DFIW, 5012980
> Setra Thierry Andriamiadanarivo, DFIW, 0388051
> Chrislie Briel Mohomye Yotchouen, PI, 5013415

---

# Inhalt 📚 
- Ziel
- Server-Side: _`llama-gpt` for Dummies_ 
- Strukturierung des Verhaltens
- Client-Side: Modell mit Methoden
- Tests (JUnit, Mockito)
- Statische Analyse (ErrorProne, Checkstyle)
- Github Actions: mehr Sicherheit

---

![bg left:40% w:350](https://cdn.pixabay.com/photo/2012/04/24/21/35/screen-40956_960_720.png)

# Ziel 🧭
## Was wollen wir eigentlich?
- Nachrichten:
  - schreiben
  - speichern
  - an einer API schicken
- Die Antwort:
  - bekommen & speichern
  - darstellen

> Bild: [pixabay](https://cdn.pixabay.com/photo/2012/04/24/21/35/screen-40956_960_720.png)

---
# Server-Side: *`llama-gpt` for Dummies* 🤖

> Von: https://github.com/getumbrel/llama-gpt

![h:175](./img/llama-gpt-readme.png)


---
# Server-Side: *`llama-gpt` for Dummies* 🤖
## Installierung: 3 Zeilen
```sh
git clone https://github.com/getumbrel/llama-gpt.git
cd llama-gpt
./run.sh --model 7b
```

$\rightarrow$ UI auf `http://localhost:3000`, API auf `http://localhost:3001` 

**Aber: das können sehr schwere Modelle sein!**

---
# Server-Side: *`llama-gpt` for Dummies* 🤖
## Den Server vom Außen erreichbar machen
- Lösung: [`caddy` als Reverse-Proxy](https://github.com/caddyserver/caddy)
- Konfigurationsdateien sind sehr lesbar
  $\rightarrow$ API auf https://gptapi.oc.romaneviton.fr erreichbar

---
# Strukturierung des Verhaltens ✉
<div style="text-align: center">

![](./img/api_docs.png)
</div>

> Quelle: [die API selbst](https://gptapi.oc.romaneviton.fr)


---

# Strukturierung des Verhaltens ✉
## Eine API, die *sehr* ähnlich zur OpenAI-API ist
<div style="text-align: center">

![](./img/postman-request.png)
</div>

---

# Strukturierung des Verhaltens ✉
## ...und sie funktioniert!

![bg right contain](./img/postman-response.png)

---

# Client-Side: Überblick 🛠
- 3 unterschiedliche Aufgaben
  - Kommunikation mit dem:der Nutzer:in
  - Nachrichtenverlauf speichern
  - Kommunikation mit dem Server

$\implies$ Antwort: **verschiedene Klassen nutzen**

---
# Client-Side: Überblick 🛠
- 4 Hauptklassen
  - `Chatbot`
  - `CommandLineInterface` (implementiert `UserInterface`)
  - `RequestSender`
  - `Conversation`

![bg right contain](./img/classes.drawio.svg)

---

# Tests 🧪
## Unit-Tests mit JUnit
- Teile individuell testen, um das Verhalten zu sichern
- Dauerhafte Sicherheit

![bg left contain](./img/messageEqualityTest.png)

---


# Tests 🧪
## Mockito: Klassen simulieren
- Erlaubt, einfachere Klassen für Testzwecke zu nutzen

![bg right contain](./img/chatbotMockitoTest.png)

---

# Tests 🧪
## JaCoCo: Testabdeckung
- Kann helfen, Stellen zu finden, die nicht getestet sind
- "Welche Tests soll ich jetzt schreiben?"

![bg right contain](./img/jacocoResults.png)

---

# Statische Codeanalyse 📖 ✅ 
- Den Code vor der Laufzeit prüfen
  - `errorprone`, um häufige Fehlermuster zu erkennen (😇)
  - `checkstyle`, um zu sichern, dass der Code an den Stilregeln angepasst ist (🤬)

![w:1500](./img/checkstyleViolation.png)

---

# Github Actions: mehr Sicherheit 🚧
- Wir Menschen sind sehr oft vergesslich
- Eine Ausführung von `mvn clean test` kann schnell fehlen und gepusht werden
- Sicherheitsnetze mit Github Actions erlauben, jeden Push zu prüfen

---

# Github Actions: mehr Sicherheit 🚧

> Aus [.github/workflows/maven.yml](https://github.com/rom-vtn/java-chatbot/blob/main/.github/workflows/maven.yml)

```yaml
jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
        cache: maven
    - name: Build with Maven
      run: mvn -B clean package site --file pom.xml
```

