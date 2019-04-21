import static spark.Spark.*;
import com.google.gson.Gson;

public class MainAPIPractico {

    static final UsuarioService usuarioService = new UsuarioServiceMapImpl();
    static final ProyectoService proyectoService = ProyectoServiceMapImpl.getProyectoServiceMapImpl();
    //static final IncidenteService incidenteService = new IncidenteServiceMapImpl();

    private static void loadData(){
        Usuario userTest1 = new Usuario(1, "Juan", "Filardo");
        Usuario userTest2 = new Usuario(2, "Federico", "Silva");
        Usuario userTest3 = new Usuario(3, "Matías", "Brond");
        usuarioService.addUsuario(userTest1);
        usuarioService.addUsuario(userTest2);
        usuarioService.addUsuario(userTest3);

        Incidente incidenteTest1 = new Incidente(1, "Falla de Api",userTest3,userTest1,Clasificacion.CRITICO);
        Incidente incidenteTest2 = new Incidente(2, "Falla de Framework",userTest3,userTest2,Clasificacion.MENOR);

        Proyecto proyectoTest1 = new Proyecto(1, "Desarrollo de Back",userTest1);
        Proyecto proyectoTest2 = new Proyecto(2, "Desarrollo de Front",userTest2);

        proyectoTest1.addIncidente(incidenteTest1);
        proyectoTest2.addIncidente(incidenteTest2);

        proyectoService.addProyecto(proyectoTest1);
        proyectoService.addProyecto(proyectoTest2);

    }

    public static void main(String[] args) {


        MainAPIPractico.loadData();


        post("/usuario", (request, response) -> {
            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);

            boolean add = usuarioService.addUsuario(usuario);

            //Verifico si se agregó el usuario y retorno el StatusResponse en función de eso.
            return new Gson().toJson(new StandardResponse(add ? StatusResponse.SUCCESS : StatusResponse.ERROR));
        });

        get("/usuario", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(usuarioService.getUsuarios())));
        });

        get("/usuario/?:id", (request, response) -> {
            //...
            response.type("application/json");
            int idUsuario = Integer.parseInt(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(usuarioService.getUsuario(
                            idUsuario
                    ))));
        });

        put("/usuario", (request, response) -> {
            //...
            response.type("application/json");

            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            Usuario usuarioEditado = usuarioService.editUsuario(usuario);

            if (usuarioEditado != null) {

                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(usuarioEditado
                        )));
            } else {

                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                        "Error al editar usuario."
                ));
            }
        });

        delete("/usuario/:id", (request, response) -> {
            //...
            response.type("application/json");
            boolean deleted = usuarioService.deleteUsuario(
                    Integer.parseInt(request.params(":id"))
            );

            return new Gson().toJson(new StandardResponse(deleted? StatusResponse.SUCCESS : StatusResponse.ERROR, deleted ? "Usuario eliminado" : "No se pudo eliminar el usuario"));
        });

        post("/proyecto", (request, response) -> {
            response.type("application/json");
            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);

            boolean add = proyectoService.addProyecto(proyecto);

            //Verifico si se agregó el proyecto y retorno el StatusResponse en función de eso.
            return new Gson().toJson(new StandardResponse(add ? StatusResponse.SUCCESS : StatusResponse.ERROR,add ? "El proyecto fue creado":"Error al crear el proyecto"));
        });

        get("/proyecto", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getProyectos())));
        });

        get("/proyecto/:id", (request, response) -> {
            //...
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getProyecto(
                            Integer.parseInt(request.params(":id"))
                    ))));
        });

        get("/proyecto/:id_usuario",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getProyectosUsuario(
                            Integer.parseInt(request.params(":id_usuario"))
                    ))));
        });


        put("/proyecto", (request, response) -> {
            //...
            response.type("application/json");

            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);
            Proyecto proyectoEditado = proyectoService.editProyecto(proyecto);

            if (proyectoEditado != null) {

                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(proyectoEditado
                        )));
            } else {

                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                        "Error al editar proyecto."
                ));
            }
        });

        delete("/proyecto/:id", (request, response) -> {
            //...
            response.type("application/json");
            boolean deletedProyecto = proyectoService.deleteProyecto(
                    Integer.parseInt(request.params(":id"))
            );

            return new Gson().toJson(new StandardResponse(deletedProyecto ? StatusResponse.SUCCESS : StatusResponse.ERROR, deletedProyecto ? "Proyecto borrado" : "No se pudo borrar el proyecto"));
        });

        post("/proyecto/:idProyecto/incidente", (request, response) -> {
            response.type("application/json");
            Incidente incidente = new Gson().fromJson(request.body(), Incidente.class);
            int idProyecto =  Integer.parseInt(request.params(":idProyecto"));

            boolean addedIncidente = proyectoService.addIncidente(incidente, idProyecto);
            //boolean addedIncidente = incidenteService.addIncidente(incidente);

                    return new Gson().toJson(new StandardResponse(addedIncidente ? StatusResponse.SUCCESS : StatusResponse.ERROR, addedIncidente ? "Incidente agregado" : "No se pudo agregar el incidente"));
        });

        put("/proyecto/:idProyecto/incidente", (request, response) -> {
            response.type("application/json");
            Incidente incidente = new Gson().fromJson(request.body(), Incidente.class);
            int idProyecto =  Integer.parseInt(request.params(":idProyecto"));

            Incidente incidenteEditado = proyectoService.editIncidente(incidente, idProyecto);

            if(incidenteEditado != null){

                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(incidenteEditado
                        )));
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                    "Error al actualizar el incidente."));

        });

        get("/proyecto/:idProyecto/incidente",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getIncidentesProyecto(
                            Integer.parseInt(request.params(":idProyecto"))
                    ))));
        });

        get("/proyecto/usuario/:idUsuario",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getProyectosUsuario(
                            Integer.parseInt(request.params(":idUsuario"))
                    ))));
        });

        get("/incidente/responsable/:idUsuario",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getIncidentesUsuarioResponsable(
                            Integer.parseInt(request.params(":idUsuario"))
                    ))));
        });

        get("/incidente/reportador/:idUsuario",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getIncidentesUsuarioReportador(
                            Integer.parseInt(request.params(":idUsuario"))
                    ))));
        });

        get("/incidente/resueltos",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getIncidentesCerrados()
                    )));
        });

        get("/incidente/abiertos",(request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getIncidentesAbiertos()
                    )));
        });
    }
}
