import java.util.Date;

public class Incidente {

    private int id;
    private String descripcion;
    private Usuario reportador;
    private Usuario responsable;
    private Date fechaCreacion;
    private Date fechaSolucion;
    private Clasificacion clasificacion;
    private Estado estado;

    public Incidente(int id, String descripcion, Usuario reportador, Usuario responsable, Clasificacion clasificacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.reportador = reportador;
        this.responsable = responsable;
        this.clasificacion = clasificacion;
        fechaCreacion = new Date();
        estado = Estado.ASIGNADO;
    }

    public Incidente(int id, String descripcion, Usuario reportador, Usuario responsable, Date fechaCreacion, Date fechaSolucion, Clasificacion clasificacion, Estado estado, int idProyecto) {
        this.id = id;
        this.descripcion = descripcion;
        this.reportador = reportador;
        this.responsable = responsable;
        this.fechaCreacion = fechaCreacion;
        this.fechaSolucion = fechaSolucion;
        this.clasificacion = clasificacion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getReportador() {
        return reportador;
    }

    public void setReportador(Usuario reportador) {
        this.reportador = reportador;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaSolucion() {
        return fechaSolucion;
    }

    public void setFechaSolucion(Date fechaSolucion) {
        this.fechaSolucion = fechaSolucion;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
