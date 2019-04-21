import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface ProyectoService {

    public boolean addProyecto(Proyecto proyecto);
    public Proyecto getProyecto(int id);
    public Collection<Proyecto> getProyectos();
    public Proyecto editProyecto(Proyecto proyecto);
    public boolean deleteProyecto(int id);
    public boolean addIncidente(Incidente incidente, int idProyecto);
    public Incidente editIncidente(Incidente incidente, int idProyecto);
    public ArrayList<Proyecto> getProyectosUsuario(int idUsuario);
    public ArrayList<Incidente> getIncidentesUsuarioResponsable(int idUsuario);
    public ArrayList<Incidente> getIncidentesUsuarioReportador(int idUsuario);
    public ArrayList<Incidente> getIncidentesProyecto(int idProyecto);
    public ArrayList<Incidente> getIncidentesAbiertos();
    public ArrayList<Incidente> getIncidentesCerrados();
}
