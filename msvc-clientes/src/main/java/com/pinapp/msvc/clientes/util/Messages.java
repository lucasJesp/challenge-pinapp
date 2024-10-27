package com.pinapp.msvc.clientes.util;

public class Messages {

    private Messages() {
        throw new UnsupportedOperationException("Utility class");
    }

    //CLIENTE
    public static final String CLIENTES_NO_OBTENIDOS = "No se pudieron obtener los clientes. Inténtelo más tarde.";
    public static final String CLIENTE_DUPLICADO = "El cliente ya existe con los mismos datos.";
    public static final String KPI_NO_OBTENIDOS = "No se pudieron obtener los KPI. Inténtelo más tarde.";
    public static final String FECHAS_PROBABLES_NO_OBTENIDAS = "No se pudieron obtener las fechas probables de muerte. Inténtelo más tarde.";
    public static final String EDAD_INCORRECTA = "La edad no coincide con la fecha de nacimiento.";

    // ERRORES
    public static final String VALIDACION_FALLIDA = "Validación fallida.";


    // VALIDACIONES
    public static final String NOMBRE_OBLIGATORIO = "El nombre es obligatorio.";
    public static final String NOMBRE_LONGITUD_INCORRECTA = "El nombre debe tener entre 2 y 50 caracteres.";
    public static final String NOMBRE_INVALIDO = "El nombre no debe contener números ni caracteres especiales.";

    public static final String APELLIDO_OBLIGATORIO = "El apellido es obligatorio.";
    public static final String APELLIDO_LONGITUD_INCORRECTA = "El apellido debe tener entre 2 y 50 caracteres.";
    public static final String APELLIDO_INVALIDO = "El apellido no debe contener números ni caracteres especiales.";

    public static final String EDAD_OBLIGATORIA = "La edad es obligatoria.";

    public static final String FECHA_NACIMIENTO_OBLIGATORIA = "La fecha de nacimiento es obligatoria.";
    public static final String FECHA_NACIMIENTO_PASADA = "La fecha de nacimiento debe ser una fecha pasada.";
    public static final String FORMATO_FECHA_INVALIDO = "La fecha debe estar en el formato 'yyyy-MM-dd'.";

}
