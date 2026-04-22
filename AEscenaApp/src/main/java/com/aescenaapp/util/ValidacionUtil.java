package com.aescenaapp.util;

public class ValidacionUtil {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";

    private static final String NOMBRE_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$";

    public static boolean emailValido(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    // -------- PASSWORD --------
    public static boolean passwordValida(String pass) {
        return pass != null && pass.matches(PASSWORD_REGEX);
    }

    public static boolean nombreValido(String nombre) {
        return nombre != null && nombre.matches(NOMBRE_REGEX);
    }

    // -------- REQUIRED FIELDS --------
    public static boolean camposObligatorios(String... campos) {
        for (String c : campos) {
            if (c == null || c.isBlank()) {
                return false;
            }
        }
        return true;
    }
}
