import sun.dc.pr.PRError;

import java.util.Collection;
import java.util.HashMap;

public class UsuarioServiceMapImpl implements UsuarioService {

    private HashMap<Integer, Usuario> usuarioMap = null;

    public UsuarioServiceMapImpl() {
        this.usuarioMap = this.getUsuarioMap();
    }

    private HashMap<Integer, Usuario> getUsuarioMap(){
        if(this.usuarioMap!=null){
            return this.usuarioMap;
        }
        return new HashMap<Integer, Usuario>();
    }

    public boolean addUsuario(Usuario usuario) {

        if (usuarioMap.containsKey(usuario.getId()) || usuario.getId() <= 0){
            return false;
        }
        usuarioMap.put(usuario.getId(), usuario);
        return true;
    }

    public Collection<Usuario> getUsuarios() {
       return usuarioMap.values();
    }

    public Usuario getUsuario(int id) {
        return usuarioMap.get(id);
    }

    public Usuario editUsuario(Usuario usuario) {

        Usuario usuarioEditar = null;

        for (Usuario user : usuarioMap.values()
             ) {
            if(user.getId() == usuario.getId()){
                usuarioEditar = user;
            }
        }

        if( usuarioEditar != null){

            usuarioEditar.setApellido( usuario.getApellido()!=null ? usuario.getApellido() : usuarioEditar.getApellido() );
            usuarioEditar.setNombre( usuario.getNombre()!=null ? usuario.getNombre() : usuarioEditar.getNombre());
        }

        return usuarioEditar;

    }

    public boolean deleteUsuario(int id) {

        ProyectoServiceMapImpl proyecto = ProyectoServiceMapImpl.getProyectoServiceMapImpl();

        if(id <= 0){
            return false;
        }

        for (Proyecto p: proyecto.getProyectos()
             ) {
            if(p.getPropietario().getId() == id){
                return false;
            }
            for (Incidente i: p.getIncidentes()
                 ) {
                if(i.getResponsable().getId() == id || i.getReportador().getId() == id)
                {
                    return false;
                }
            }
        }

        usuarioMap.remove(id);
        return true;


    }
}
