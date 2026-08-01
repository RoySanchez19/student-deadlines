package dev.roysanchez.deadlines.deadline;

/**
 * Tipo de fecha que se esta siguiendo.
 *
 * <p>Se guarda como texto en la base de datos, no como numero. Si se guardara
 * el ordinal, insertar un valor nuevo en medio de esta lista cambiaria el
 * significado de las filas ya existentes, en silencio.
 */
public enum DeadlineKind {
    I20_PROGRAM_END,
    OPT_APPLICATION_WINDOW,
    TRAVEL_SIGNATURE,
    SEVIS_FEE,
    TUITION,
    OTHER
}
