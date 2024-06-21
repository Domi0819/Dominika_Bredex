# Az alkalmazásról
### Az alkalmazás általános célja, hogy lehetővé tegye cégek álláshírdetését, illetve felhasználók hatékony álláskeresését.

## Használat

### 1. Először a Maven nevű project kezelő eszközt kell telepíteni az alábbi módon:  
```bash
mvn clean install
```

### 2. Másoljuk át a lokális adatbázisunkat a megfelelő mappába.
```bash
cp ./testdb2.mv.db ./target/
cp ./testdb2.trace.db ./target/
```

### 3. Ezután a target-ban lévő jar fájlt kell futtatni java-val
### pl:
```bash
java -jar ./target/demo-0.0.1-SNAPSHOT.jar
```

## Továbbfejlesztés
> [!TIP]
> Profilok létrehozása pl: (production , dev , test)

> [!TIP]
> Konténerizáció

> [!TIP]
> Tesztek írása

> [!TIP]
> Dokumentáció
