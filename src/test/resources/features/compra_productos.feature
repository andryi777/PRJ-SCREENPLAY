@examen @parte1
Feature: Compra de productos en Automation Store
  Como cliente de Automation Store
  Quiero poder agregar productos al carrito y realizar compras
  Para adquirir los productos que necesito

  Background:
    Given el usuario navega a "http://automationexercise.com"
    And la página de inicio se muestra correctamente

  @requisito_a @agregar_carrito
  Scenario Outline: Agregar producto al carrito y verificar precio
    When hace clic en el botón "Products"
    And agrega el producto "<producto>" al carrito con cantidad <cantidad>
    And hace clic en "View Cart"
    Then debería verificar que el producto "<producto>" tiene precio "<precio>", cantidad "<cantidad>" y total "<total>"

    Examples:
      | producto   | precio   | cantidad | total      |
      | Blue Top   | Rs. 500  | 2        | Rs. 1000   |
      | Men Tshirt | Rs. 400  | 3        | Rs. 1200   |

  @requisito_b @verificar_cantidad
  Scenario Outline: Verificar cantidad de producto en el carrito
    When hace clic en "View Product" para cualquier producto
    And se abre el detalle del producto
    And aumenta la cantidad a <cantidad>
    And hace clic en "Add to cart"
    And hace clic en "View Cart"
    Then debería verificar que el producto se muestra en el carrito con la cantidad exacta <cantidad>

    Examples:
      | cantidad |
      | 4        |

  @requisito_c @pedido_completo
  Scenario: Realizar pedido registrando al usuario durante el checkout
    Given agrega productos al carrito
    When hace clic en "Cart"
    And verifica que la página del carrito se muestra correctamente
    And hace clic en "Proceed To Checkout"
    And hace clic en "Register / Login"
    And completa el registro con los siguientes datos:
      | campo    | valor                      |
      | nombre   | Juan Perez                 |
      | email    | juan{timestamp}@test.com   |
      | password | Test123456                 |
    And verifica "ACCOUNT CREATED!" y hace clic en "Continue"
    And verifica "Logged in as"
    And ir nuevamente a Cart
    And hace clic en "Proceed To Checkout"
    And verifica dirección y pedido
    And agrega comentario "Por favor enviar rápido"
    And ingresa datos de pago
    And hace clic en "Pay and Confirm Order"
    Then debería verificar "Your order has been placed successfully!"
