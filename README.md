# Proyecto de Automatización - Automation Exercise

Pruebas automatizadas para [automationexercise.com](http://automationexercise.com) con Serenity BDD y Screenplay Pattern.

## Tecnologías

- Java 11, Maven, Serenity BDD 4.1.14, Selenium 4.18.1, Cucumber 7.18.0

## Estructura

```
src/test/java/com/automation/
├── data/           # TestData, Secciones
├── interactions/   # CerrarPopupsYAnuncios
├── questions/      # ProductoEnCarrito, ElPrecioDelProducto, etc.
├── tasks/          # AgregarProductoConCantidadPorNombre, RegistrarseEnElCheckout, etc.
├── userinterfaces/ # ProductsPage, CartPage, CheckoutPage, etc.
├── stepdefinitions/# CompraProductosStepDefinitions, Hooks
└── runners/        # ExamenTestRunner
```

## Requisitos Implementados

### Requisito A - Agregar productos y verificar precios
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

### Requisito B - Verificar cantidad en carrito

### Requisito C - Pedido completo con registro

## Ejecución

```bash
# Instalar
mvn clean install -DskipTests

# Ejecutar todas las pruebas
mvn clean verify

# Por tags
mvn clean verify -Dcucumber.filter.tags="@requisito_a"
mvn clean verify -Dcucumber.filter.tags="@requisito_b"
mvn clean verify -Dcucumber.filter.tags="@requisito_c"
```

## Reportes

`target/site/serenity/index.html`

## Autor

Christian Maury
