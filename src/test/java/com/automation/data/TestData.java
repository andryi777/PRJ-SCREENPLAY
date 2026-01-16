package com.automation.data;

/**
 * Clase centralizada para datos de prueba.
 * Evita hardcodear datos en Tasks e Interactions.
 */
public final class TestData {

    private TestData() {
        // Clase de utilidad, no instanciable
    }

    // ==================== DATOS DE USUARIO ====================
    public static final class Usuario {
        public static final String DIRECCION_DEFAULT = "Calle Principal 123";
        public static final String PAIS_DEFAULT = "United States";
        public static final String ESTADO_DEFAULT = "California";
        public static final String CIUDAD_DEFAULT = "Los Angeles";
        public static final String CODIGO_POSTAL_DEFAULT = "90001";
        public static final String TELEFONO_DEFAULT = "1234567890";

        private Usuario() {}
    }

    // ==================== DATOS DE PAGO ====================
    public static final class Pago {
        public static final String NOMBRE_TARJETA = "Juan Perez";
        public static final String NUMERO_TARJETA = "4532015112830366";
        public static final String CVC = "123";
        public static final String MES_EXPIRACION = "12";
        public static final String ANIO_EXPIRACION = "2028";

        private Pago() {}
    }

    // ==================== URLs ====================
    public static final class Urls {
        public static final String BASE_URL = "http://automationexercise.com";

        private Urls() {}
    }

    // ==================== MENSAJES ESPERADOS ====================
    public static final class Mensajes {
        public static final String CUENTA_CREADA = "ACCOUNT CREATED!";
        public static final String LOGGED_IN_AS = "Logged in as";
        public static final String PEDIDO_EXITOSO = "order has been placed successfully";

        private Mensajes() {}
    }
}
