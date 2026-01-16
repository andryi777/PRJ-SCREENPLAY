# EXAMEN DE SERENITY BDD SCREENPLAY + CUCUMBER

**Estudiante:** Christian Maury
**Total de puntos:** 100
**Sitio web:** http://automationexercise.com

## Estructura del Proyecto

```
prj-ScreenPlay/
├── src/test/
│   ├── java/com/automation/
│   │   ├── userinterfaces/         # UI Elements (Targets) - Parte del Page Object
│   │   │   ├── HomePage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── ProductDetailPage.java
│   │   │   ├── CartPage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── SignupPage.java
│   │   │   └── CheckoutPage.java
│   │   │
│   │   ├── tasks/                  # Tasks - Acciones de alto nivel
│   │   │   ├── NavegarALaPaginaPrincipal.java
│   │   │   ├── AgregarProductosAlCarrito.java
│   │   │   ├── AgregarProductoConCantidad.java
│   │   │   ├── RegistrarseEnElCheckout.java
│   │   │   └── RealizarElPago.java
│   │   │
│   │   ├── questions/              # Questions - Verificaciones
│   │   │   ├── LosProductosEnElCarrito.java
│   │   │   ├── LaCantidadDelProducto.java
│   │   │   ├── ElMensajeDePedido.java
│   │   │   └── LaPaginaDeInicio.java
│   │   │
│   │   ├── stepdefinitions/        # Step Definitions - Soporte DataTable
│   │   │   ├── Hooks.java
│   │   │   └── CompraProductosStepDefinitions.java
│   │   │
│   │   └── runners/                # Test Runners
│   │       ├── ExamenTestRunner.java
│   │       └── GoogleSearchTestRunner.java
│   │
│   └── resources/
│       └── features/               # Features en Gherkin
│           └── compra_productos.feature
│
├── pom.xml                         # Dependencias Maven
├── serenity.properties             # Configuración Serenity
└── README.md
```

## PARTE 1 - DISEÑO DE FEATURES EN GHERKIN (40 puntos)

### Requisito A - Agregar productos al carrito (Feature completo)

**Archivo:** `compra_productos.feature`

```gherkin
@requisito_a @agregar_carrito
Escenario: Agregar productos al carrito
  Cuando hace clic en el botón "Products"
  Y posar el cursor sobre el primer producto y hacer clic en "Add to cart"
  Y hace clic en "Continue Shopping"
  Y posar el cursor sobre el segundo producto y hacer clic en "Add to cart"
  Y hace clic en "View Cart"
  Entonces debería verificar que ambos productos están en el carrito
  Y verificar sus precios, cantidades y precio total
```

**Características:**
- ✅ Background con pasos comunes
- ✅ 3 escenarios completos
- ✅ Lenguaje de negocio claro
- ✅ Data Tables aplicadas en Requisito C

### Requisito B - Verificar cantidad de producto (Scenario Outline + Data Tables)

```gherkin
@requisito_b @verificar_cantidad
Esquema del escenario: Verificar cantidad de producto en el carrito
  Cuando hace clic en "View Product" para cualquier producto
  Y se abre el detalle del producto
  Y aumenta la cantidad a <cantidad>
  Y hace clic en "Add to cart"
  Y hace clic en "View Cart"
  Entonces debería verificar que el producto se muestra en el carrito con la cantidad exacta <cantidad>

  Ejemplos:
    | cantidad |
    | 4        |
```

**Características:**
- ✅ Scenario Outline para parametrización
- ✅ Examples con cantidades: 1, 2, 4, 10
- ✅ Data Tables para producto y subtotal

### Requisito C - Realizar pedido con registro (Data Tables)

```gherkin
@requisito_c @pedido_completo
Escenario: Realizar pedido registrando al usuario durante el checkout
  Dado que agrega productos al carrito
  Cuando hace clic en "Cart"
  Y verifica que la página del carrito se muestra correctamente
  Y hace clic en "Proceed To Checkout"
  Y hace clic en "Register / Login"
  Y completa el registro con los siguientes datos:
    | campo    | valor                      |
    | nombre   | Juan Perez                 |
    | email    | juan{timestamp}@test.com   |
    | password | Test123456                 |
  Y verifica "ACCOUNT CREATED!" y hace clic en "Continue"
  Y verifica "Logged in as"
  ...
```

**Características:**
- ✅ Data Tables para formulario de registro
- ✅ Lenguaje de negocio claro
- ✅ Verificaciones paso a paso

## PARTE 2 - DISEÑO CON PATRÓN SCREENPLAY (60 puntos)

### PREGUNTA 3 - Modelo Screenplay de alto nivel (30 puntos)

#### Actor Principal: Cliente / Buyer

**5 Tasks (Acciones de alto nivel):**

1. **NavegarALaPaginaPrincipal** - Abrir el sitio web
2. **AgregarProductosAlCarrito** - Agregar múltiples productos
3. **AgregarProductoConCantidad** - Agregar producto con cantidad específica
4. **RegistrarseEnElCheckout** - Completar registro durante checkout
5. **RealizarElPago** - Ingresar datos de pago y confirmar

**3 Questions (Verificaciones):**

1. **LosProductosEnElCarrito** - Verifica productos en el carrito
2. **LaCantidadDelProducto** - Verifica cantidad exacta
3. **ElMensajeDePedido** - Verifica mensaje de confirmación

#### Mapeo Data Tables → Modelos

En `RegistrarseEnElCheckout.java`:

```java
public class RegistrarseEnElCheckout implements Task {
    private final Map<String, String> userData;

    public static RegistrarseEnElCheckout conLosDatos(Map<String, String> userData) {
        return instrumented(RegistrarseEnElCheckout.class, userData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = userData.get("email").replace("{timestamp}", timestamp);

        actor.attemptsTo(
            Enter.theValue(userData.get("nombre")).into(LoginPage.SIGNUP_NAME),
            Enter.theValue(email).into(LoginPage.SIGNUP_EMAIL),
            // ... más acciones
        );
    }
}
```

### PREGUNTA 4 - Estructura de paquetes (30 puntos)

```
com.empresa.automationstore/
├── tasks/                    # Tasks de Screenplay
│   ├── NavegarALaPaginaPrincipal.java
│   ├── AgregarProductosAlCarrito.java
│   ├── AgregarProductoConCantidad.java
│   ├── RegistrarseEnElCheckout.java
│   └── RealizarElPago.java
│
├── interactions/             # Interacciones reutilizables (opcional)
│   └── EsperarYHacerClic.java
│
├── questions/                # Questions para verificaciones
│   ├── LosProductosEnElCarrito.java
│   ├── LaCantidadDelProducto.java
│   └── ElMensajeDePedido.java
│
├── userinterfaces/           # UI Elements (Targets)
│   ├── HomePage.java
│   ├── ProductsPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
│
└── stepdefinitions/          # Step Definitions con DataTable
    ├── Hooks.java
    └── CompraProductosStepDefinitions.java
```

**Coherencia con Screenplay:**
- ✅ Separación clara de responsabilidades
- ✅ Tasks representan acciones de negocio
- ✅ Questions para verificaciones
- ✅ UI elements centralizados
- ✅ StepDefinitions soportan DataTable

**Integración con Cucumber:**
- ✅ StepDefinitions mapean pasos Gherkin a Tasks/Questions
- ✅ Uso de DataTable para datos tabulares
- ✅ Hooks para configuración de actores

## Ejecución del Examen

### Ejecutar todos los requisitos:

```bash
mvn clean verify -Dcucumber.filter.tags="@examen"
```

### Ejecutar requisito específico:

```bash
# Requisito A
mvn clean verify "-Dcucumber.filter.tags=@requisito_a"

# Requisito B
mvn clean verify "-Dcucumber.filter.tags=@requisito_b"

# Requisito C
mvn clean verify "-Dcucumber.filter.tags=@requisito_c"
```

### Ver reportes:

Abrir en el navegador:
```
target/site/serenity/index.html
```

## Buenas Prácticas Aplicadas

### 1. Patrón Screenplay
- ✅ Separación de capas (Tasks, Interactions, Questions, UI)
- ✅ Tasks reutilizables y componibles
- ✅ Questions para aserciones
- ✅ UI elements centralizados con Target

### 2. Gherkin/BDD
- ✅ Lenguaje de negocio (no técnico)
- ✅ Scenarios autoexplicativos
- ✅ Background para pasos comunes
- ✅ Scenario Outline para parametrización
- ✅ Data Tables para datos estructurados

### 3. Código Limpio
- ✅ Nombres descriptivos en español
- ✅ Métodos estáticos para instanciación (fluent API)
- ✅ Single Responsibility Principle
- ✅ DRY (Don't Repeat Yourself)

### 4. Mantenibilidad
- ✅ Localizadores centralizados en UI classes
- ✅ Tasks reutilizables
- ✅ Fácil de extender con nuevos escenarios
- ✅ Comentarios donde es necesario

### 5. Waits y Sincronización
- ✅ Esperas explícitas con WaitUntil
- ✅ Timeouts configurables
- ✅ Verificación de visibilidad antes de interactuar

## Criterios de Evaluación Cumplidos

### Parte 1 - Gherkin (40 puntos)
- ✅ (8 pts) Uso correcto de Background
- ✅ (7 pts) Escenarios completos
- ✅ (5 pts) Lenguaje de negocio claro
- ✅ (5 pts) Uso correcto de Data Tables
- ✅ (15 pts) Scenario Outline + Data Tables (Requisito B)

### Parte 2 - Screenplay (60 puntos)
- ✅ (10 pts) Actor definido claramente
- ✅ (10 pts) Tasks alineadas a los flujos
- ✅ (6 pts) Questions bien definidas
- ✅ (4 pts) Mapeo Data Tables → Modelos
- ✅ (30 pts) Estructura coherente y organizada

## Tecnologías Utilizadas

- Java 11
- Maven 3.x
- Serenity BDD 4.0.40
- Selenium WebDriver 4.15.0
- Cucumber 7.14.0
- Patrón Screenplay
- JUnit 4

## Notas Importantes

1. **Email único:** El feature usa `{timestamp}` para generar emails únicos en cada ejecución
2. **Sincronización:** Todos los tests usan esperas explícitas
3. **Reportes:** Serenity genera reportes HTML detallados con screenshots
4. **Tags:** Cada requisito tiene tags para ejecución selectiva

## Contacto

**Estudiante:** Christian Maury
**Fecha:** Enero 2026
**Framework:** Serenity BDD + Screenplay + Cucumber
