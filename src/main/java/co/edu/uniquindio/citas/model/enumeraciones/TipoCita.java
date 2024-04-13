package co.edu.uniquindio.citas.model.enumeraciones;

public enum TipoCita {

    MEDICO_GENERAL(Prioridad.BAJA),
    CIRUGIA_PROGRAMADA(Prioridad.MEDIA),
    URGENCIA(Prioridad.ALTA);


    private final Prioridad prioridad;

    TipoCita(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }
}
