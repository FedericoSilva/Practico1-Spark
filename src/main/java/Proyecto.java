import java.util.ArrayList;
import java.util.Date;

public class Proyecto {

    private int id;
    private String titulo;
    private Usuario propietario;
    private ArrayList<Incidente> incidentes;

    public Proyecto(int id, String titulo, Usuario propietario) {
        this.id = id;
        this.titulo = titulo;
        this.propietario = propietario;
        incidentes = new ArrayList<Incidente>();
    }

    public int getId() {
        return id;
    }

    public void addIncidente(Incidente incidente){
        incidentes.add(incidente);
    }

    public Incidente editIncidente(Incidente incidente){

        Incidente incidenteEditar = null;

        for (Incidente i : incidentes
        ) {
            if(i.getId() == incidente.getId()){
                incidenteEditar = i;
                break;
            }
        }

        if(incidenteEditar !=  null) {

            incidenteEditar.setDescripcion(incidente.getDescripcion() != null ? incidenteEditar.getDescripcion() + " " + incidente.getDescripcion() : incidenteEditar.getDescripcion());

            if (incidente.getEstado() == Estado.RESUELTO) {
                incidenteEditar.setEstado(incidente.getEstado() != null ? incidente.getEstado() : incidenteEditar.getEstado());
                incidenteEditar.setFechaSolucion(new Date());
            }
        }

        return  incidenteEditar;
    }

    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(ArrayList<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }
}
