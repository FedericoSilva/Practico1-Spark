import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ProyectoServiceMapImpl implements ProyectoService {

    private HashMap<Integer, Proyecto> proyectoMap;
    private static ProyectoServiceMapImpl proyectoServiceMapImpl;

    public static ProyectoServiceMapImpl getProyectoServiceMapImpl()
    {
        if(proyectoServiceMapImpl == null)
        {
            proyectoServiceMapImpl = new ProyectoServiceMapImpl();
        }
        return proyectoServiceMapImpl;
    }

    private ProyectoServiceMapImpl() {
        proyectoMap = new HashMap<Integer, Proyecto>();
    }

    public boolean addProyecto(Proyecto proyecto) {

        if (proyectoMap.containsKey(proyecto.getId()) || proyecto.getId() <= 0){
            return false;
        }
        proyectoMap.put(proyecto.getId(), proyecto);
        return true;
    }

    @Override
    public ArrayList<Incidente> getIncidentesProyecto(int idProyecto) {

        return proyectoMap.get(idProyecto).getIncidentes()!=null ? proyectoMap.get(idProyecto).getIncidentes() : new ArrayList<Incidente>();

    }

    @Override
    public Incidente editIncidente(Incidente incidente, int idProyecto) {

        Proyecto proyectoActualizar = proyectoMap.get(idProyecto);

        if(proyectoActualizar != null){
            return proyectoActualizar.editIncidente(incidente);
        }
        return null;
    }

    @Override
    public ArrayList<Incidente> getIncidentesUsuarioResponsable(int idUsuario) {

        ArrayList<Incidente> incidentesUsuarioResponsable = new ArrayList<Incidente>();
        Collection<Proyecto> proyectos = proyectoMap.values();

        for (Proyecto p: proyectos
        ) {
            for (Incidente incidente : p.getIncidentes()
                 ) {
                if(incidente.getResponsable().getId() == idUsuario){
                    incidentesUsuarioResponsable.add(incidente);
                }
            }
        }

        return incidentesUsuarioResponsable;
    }

    @Override
    public ArrayList<Incidente> getIncidentesAbiertos() {

        ArrayList<Incidente> incidentesAbiertos = new ArrayList<Incidente>();
        Collection<Proyecto> proyectos = proyectoMap.values();

        for (Proyecto p: proyectos
        ) {
            for (Incidente incidente : p.getIncidentes()
            ) {
                if(incidente.getEstado() == Estado.ASIGNADO){
                    incidentesAbiertos.add(incidente);
                }
            }
        }

        return incidentesAbiertos;
    }

    @Override
    public ArrayList<Incidente> getIncidentesCerrados() {

        ArrayList<Incidente> incidentesCerrados = new ArrayList<Incidente>();
        Collection<Proyecto> proyectos = proyectoMap.values();

        for (Proyecto p: proyectos
        ) {
            for (Incidente incidente : p.getIncidentes()
            ) {
                if(incidente.getEstado() == Estado.RESUELTO){
                    incidentesCerrados.add(incidente);
                }
            }
        }

        return incidentesCerrados;
    }

    @Override
    public ArrayList<Incidente> getIncidentesUsuarioReportador(int idUsuario) {

        ArrayList<Incidente> incidentesUsuarioReportador= new ArrayList<Incidente>();
        Collection<Proyecto> proyectos = proyectoMap.values();

        for (Proyecto p: proyectos
        ) {
            for (Incidente incidente : p.getIncidentes()
            ) {
                if(incidente.getReportador().getId() == idUsuario){
                    incidentesUsuarioReportador.add(incidente);
                }
            }
        }

        return incidentesUsuarioReportador;
    }

    public Proyecto getProyecto(int id) {
        return proyectoMap.get(id);
    }

    @Override
    public boolean addIncidente(Incidente incidente, int idProyecto) {

        if(idProyecto > 0){
            proyectoMap.get(idProyecto).addIncidente(incidente);
            return true;
        }

        return false;
    }

    public Collection<Proyecto> getProyectos() {
        return proyectoMap.values();
    }

    public ArrayList<Proyecto> getProyectosUsuario(int idUsuario){

        ArrayList<Proyecto> proyectosUsuario = new ArrayList<>();

        for (Proyecto p: proyectoMap.values()
             ) {
            if(proyectoMap.get(p.getId()).getPropietario().getId() == idUsuario){
                proyectosUsuario.add(proyectoMap.get(p));
            }
        }

        return proyectosUsuario;
    }

    public Proyecto editProyecto(Proyecto proyecto) {

        Proyecto proyectoEditar = proyectoMap.get(proyecto.getId());
        proyectoEditar.setTitulo( proyecto.getTitulo()!=null ? proyecto.getTitulo() : proyectoEditar.getTitulo() );
        proyectoEditar.setPropietario( proyecto.getPropietario()!=null ? proyecto.getPropietario() : proyectoEditar.getPropietario() );

        return proyectoEditar;
    }

    public boolean deleteProyecto(int id) {


        if( id <=0 || proyectoMap.get(id).getIncidentes().size() >= 0)
        {
            return false;
        }
        proyectoMap.remove(id);
        return true;
    }
}
