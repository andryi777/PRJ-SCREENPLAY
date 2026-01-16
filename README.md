# Proyecto de Automatización - Automation Exercise

Proyecto de automatización de pruebas web para [automationexercise.com](http://automationexercise.com) utilizando Serenity BDD, Selenium WebDriver, Cucumber BDD y el patrón Screenplay.

## Tecnologías Utilizadas

- **Java 11**
- **Maven** - Gestión de dependencias
- **Serenity BDD 4.1.14** - Framework de automatización y reportes
- **Selenium WebDriver 4.18.1** - Automatización del navegador
- **Cucumber 7.18.0** - BDD (Behavior Driven Development)
- **Screenplay Pattern** - Patrón de diseño para pruebas mantenibles
- **JUnit 5** - Framework de pruebas

## Estructura del Proyecto

```
prj-ScreenPlay/
├── src/test/java/com/automation/
│   ├── data/                    # Datos de prueba centralizados
│   │   ├── TestData.java
│   │   └── Secciones.java
│   ├── interactions/            # Interacciones personalizadas
│   │   └── CerrarPopupsYAnuncios.java
│   ├── questions/               # Questions (verificaciones)
│   │   ├── ProductoEnCarrito.java
│   │   ├── ElPrecioDelProducto.java
│   │   ├── LaCantidadDelProducto.java
│   │   ├── ElTotalDelProducto.java
│   │   └── ...
│   ├── tasks/                   # Tasks (acciones)
│   │   ├── AgregarProductoConCantidadPorNombre.java
│   │   ├── AgregarProductosAlCarrito.java
│   │   ├── NavegarHaciaSeccion.java
│   │   ├── RegistrarseEnElCheckout.java
│   │   ├── RealizarElPago.java
│   │   └── ...
│   ├── userinterfaces/          # Page Objects (selectores)
│   │   ├── ProductsPage.java
│   │   ├── CartPage.java
│   │   ├── CheckoutPage.java
│   │   └── ...
│   ├── stepdefinitions/         # Step Definitions
│   │   ├── CompraProductosStepDefinitions.java
│   │   └── Hooks.java
│   └── runners/                 # Test Runners
│       └── ExamenTestRunner.java
├── src/test/resources/
│   ├── features/
│   │   └── compra_productos.feature
│   └── serenity.conf
├── pom.xml
├── serenity.properties
└── README.md
```

## Requisitos Implementados

### Requisito A - Agregar productos al carrito y verificar precios
- Navegar a Products
- Agregar producto con cantidad específica
- Verificar precio, cantidad y total en el carrito

```gherkin
Scenario Outline: Agregar producto al carrito y verificar precio
  When hace clic en el botón "Products"
  And agrega el producto "<producto>" al carrito con cantidad <cantidad>
  And hace clic en "View Cart"
  Then debería verificar que el producto "<producto>" tiene precio "<precio>", cantidad "<cantidad>" y total "<total>"

  Examples:
    | producto   | precio   | cantidad | total      |
    | Blue Top   | Rs. 500  | 2        | Rs. 1000   |
    | Men Tshirt | Rs. 400  | 3        | Rs. 1200   |
```

### Requisito B - Verificar cantidad de producto
- Ver detalle de producto
- Modificar cantidad
- Verificar cantidad en carrito

### Requisito C - Realizar pedido completo
- Agregar productos
- Registrarse durante checkout
- Completar datos de pago
- Verificar pedido exitoso

## Prerequisitos

- Java JDK 11 o superior
- Maven 3.6 o superior
- Google Chrome

## Instalación

```bash
git clone https://github.com/andryi777/PRJ-SCREENPLAY.git
cd PRJ-SCREENPLAY
mvn clean install -DskipTests
```

## Ejecución de Pruebas

### Ejecutar todas las pruebas
```bash
mvn clean verify
```

### Ejecutar por tags
```bash
# Solo requisito A
mvn clean verify -Dcucumber.filter.tags="@requisito_a"

# Solo requisito B
mvn clean verify -Dcucumber.filter.tags="@requisito_b"

# Solo requisito C
mvn clean verify -Dcucumber.filter.tags="@requisito_c"

# Todos los requisitos
mvn clean verify -Dcucumber.filter.tags="@examen"
```

### Ejecutar en modo headless
```bash
mvn clean verify -Dheadless.mode=true
```

## Reportes

Después de ejecutar las pruebas:

- **Reporte Serenity**: `target/site/serenity/index.html`

## Patrón Screenplay

El proyecto sigue el patrón Screenplay:

- **Tasks**: Acciones de alto nivel (AgregarProductoConCantidadPorNombre, RegistrarseEnElCheckout)
- **Questions**: Verificaciones del estado (ProductoEnCarrito, ElPrecioDelProducto)
- **UserInterfaces**: Selectores de elementos (ProductsPage, CartPage)
- **Interactions**: Acciones de bajo nivel (CerrarPopupsYAnuncios)

## Autor

Christian Maury
