# Proyecto de Automatización Web con Screenplay Pattern

Proyecto de automatización de pruebas web utilizando Serenity BDD, Selenium WebDriver, Cucumber BDD y el patrón Screenplay.

## Tecnologías Utilizadas

- **Java 11**
- **Maven** - Gestión de dependencias y construcción
- **Serenity BDD 4.1.14** - Framework de automatización y reportes
- **Selenium WebDriver 4.18.1** - Automatización del navegador
- **Cucumber 7.18.0** - BDD (Behavior Driven Development)
- **Screenplay Pattern** - Patrón de diseño para pruebas más mantenibles
- **JUnit 5** - Framework de pruebas
- **AssertJ** - Aserciones fluidas

## Estructura del Proyecto

```
prj-ScreenPlay/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── automation/
│       │           ├── runners/              # Test Runners de Cucumber
│       │           │   └── GoogleSearchTestRunner.java
│       │           ├── stepdefinitions/      # Step Definitions de Cucumber
│       │           │   ├── BusquedaGoogleStepDefinitions.java
│       │           │   └── Hooks.java
│       │           ├── tasks/                # Tasks del patrón Screenplay
│       │           │   ├── AbrirPaginaDeGoogle.java
│       │           │   └── BuscarEn.java
│       │           ├── questions/            # Questions del patrón Screenplay
│       │           │   └── LosResultadosDeBusqueda.java
│       │           └── ui/                   # Page Objects / UI Elements
│       │               └── GoogleHomePage.java
│       └── resources/
│           ├── features/                     # Archivos .feature de Cucumber
│           │   └── busqueda_google.feature
│           └── serenity.conf                 # Configuración de ambientes
├── pom.xml                                   # Dependencias Maven
├── serenity.properties                       # Configuración de Serenity
└── README.md
```

## Patrón Screenplay

El patrón Screenplay organiza el código en capas:

1. **UI (User Interface)**: Define los elementos de la página usando Targets
2. **Tasks**: Acciones de alto nivel que realiza el actor (ej: BuscarEn, AbrirPaginaDeGoogle)
3. **Interactions**: Acciones de bajo nivel (Click, Enter, etc.) - provistas por Serenity
4. **Questions**: Consultas sobre el estado de la aplicación (ej: LosResultadosDeBusqueda)
5. **Actors**: Representan a los usuarios que interactúan con el sistema

## Prerequisitos

- Java JDK 11 o superior
- Maven 3.6 o superior
- Google Chrome (se descargará automáticamente el ChromeDriver)

## Instalación

1. Clonar o descargar el proyecto
2. Navegar al directorio del proyecto
3. Ejecutar el comando para descargar las dependencias:

```bash
mvn clean install -DskipTests
```

## Ejecución de Pruebas

### Ejecutar todas las pruebas

```bash
mvn clean verify
```

### Ejecutar pruebas por tags

```bash
mvn clean verify -Dcucumber.filter.tags="@smoke"
```

```bash
mvn clean verify -Dcucumber.filter.tags="@regression"
```

### Ejecutar con diferentes navegadores

```bash
mvn clean verify -Dwebdriver.driver=chrome
```

```bash
mvn clean verify -Dwebdriver.driver=firefox
```

### Ejecutar en modo headless

Descomentar las líneas en `serenity.properties`:
```properties
headless.mode=true
chrome.switches=--headless=new,--disable-gpu,--window-size=1920,1080,--remote-allow-origins=*
```

## Reportes

Después de ejecutar las pruebas, los reportes se generan automáticamente en:

- **Reporte de Serenity**: `target/site/serenity/index.html`
- **Reporte de Cucumber**: `target/cucumber-reports.html`

Para visualizar el reporte de Serenity, abrir el archivo `index.html` en un navegador.

## Configuración de Ambientes

Los ambientes se configuran en `src/test/resources/serenity.conf`:

- **default**: Ambiente por defecto
- **dev**: Ambiente de desarrollo
- **qa**: Ambiente de pruebas
- **prod**: Ambiente de producción

Para ejecutar en un ambiente específico:

```bash
mvn clean verify -Denvironment=qa
```

## Ejemplo de Feature

```gherkin
# language: es
Característica: Búsqueda en Google
  Como usuario de internet
  Quiero realizar búsquedas en Google
  Para encontrar información relevante

  @busqueda @smoke
  Escenario: Búsqueda exitosa de información
    Dado que el usuario está en la página de Google
    Cuando realiza una búsqueda de "Serenity BDD Screenplay"
    Entonces debería ver resultados relacionados con "Serenity BDD"
```

## Crear Nuevas Pruebas

### 1. Crear un nuevo archivo .feature

Crear un archivo en `src/test/resources/features/` con la especificación en Gherkin.

### 2. Crear UI Elements

Definir los elementos de la página en `src/test/java/com/automation/ui/`:

```java
public class MiPaginaUI {
    public static final Target MI_ELEMENTO = Target.the("mi elemento")
            .located(By.id("elemento-id"));
}
```

### 3. Crear Tasks

Crear acciones de alto nivel en `src/test/java/com/automation/tasks/`:

```java
public class MiTarea implements Task {
    public static MiTarea realizar() {
        return instrumented(MiTarea.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(MiPaginaUI.MI_ELEMENTO)
        );
    }
}
```

### 4. Crear Questions

Crear consultas en `src/test/java/com/automation/questions/`:

```java
public class MiPregunta implements Question<String> {
    public static MiPregunta obtener() {
        return new MiPregunta();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MiPaginaUI.MI_ELEMENTO).answeredBy(actor);
    }
}
```

### 5. Crear Step Definitions

Implementar los pasos en `src/test/java/com/automation/stepdefinitions/`:

```java
public class MisSteps {
    @Dado("que el usuario...")
    public void queElUsuario() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            MiTarea.realizar()
        );
    }
}
```

## Buenas Prácticas

1. **Un Task por acción de negocio**: Cada task debe representar una acción completa del usuario
2. **Questions para verificaciones**: Usar Questions para obtener información del sistema
3. **UI separado**: Mantener los selectores en clases UI separadas
4. **Nombres descriptivos**: Usar nombres que describan la intención, no la implementación
5. **Actors específicos**: Usar actores con nombres descriptivos (Usuario, Administrador, etc.)

## Solución de Problemas

### Error de ChromeDriver

Si hay problemas con ChromeDriver, asegurarse de que `webdriver.autodownload=true` esté en `serenity.properties`.

### Timeout en elementos

Ajustar los tiempos de espera en `serenity.properties`:

```properties
webdriver.timeouts.implicitlywait=15000
webdriver.wait.for.timeout=15000
```

### Problemas con dependencias

Limpiar y recompilar:

```bash
mvn clean install -U
```

## Recursos Adicionales

- [Serenity BDD Documentation](https://serenity-bdd.github.io/)
- [Screenplay Pattern](https://serenity-js.org/handbook/design/screenplay-pattern/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Selenium WebDriver](https://www.selenium.dev/documentation/)

## Autor

Christian Maury

## Licencia

Este proyecto está bajo la Licencia MIT.
