package com.edu.unicordoba.registro_visitantes.util;

public final class TextoUtil {
    private TextoUtil() {
        // Nunca se instancia
        throw new UnsupportedOperationException();
    }

    public static String normalizarNombre(String t) {
        if (t == null || t.isBlank()) return "SIN NOMBRE";
        String[] ps = t.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String p : ps) {
            sb.append(Character.toUpperCase(p.charAt(0)))
              .append(p.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
