import java.util.Collection;

public interface ProyectoService {

    public boolean addProyecto(Proyecto proyecto);
    public Proyecto getProyecto(int id);
    public Collection<Proyecto> getProyectos();
    public Proyecto editProyecto(Proyecto proyecto);
    public boolean deleteProyecto(int id);
    public boolean addIncidente(Incidente incidente, int idProyecto);
    public Incidente editIncidente(Incidente incidente, int idProyecto);
    public Collection<Proyecto> getProyectosUsuario(int idUsuario);
    public Collection<Incidente> getIncidentesUsuarioResponsable(int idUsuario);
    public Collection<Incidente> getIncidentesUsuarioReportador(int idUsuario);
    public Collection<Incidente> getIncidentesProyecto(int idProyecto);
    public Collection<Incidente> getIncidentesAbiertos();
    public Collection<Incidente> getIncidentesCerrados();
}
