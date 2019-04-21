import java.util.Collection;

public interface UsuarioService {

    public boolean addUsuario(Usuario usuario);
    public Collection<Usuario> getUsuarios();
    public Usuario getUsuario(int id);
    public Usuario editUsuario(Usuario usuario);
    public boolean deleteUsuario(int id);

}
