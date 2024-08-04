/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crud.resource;
import example.crud.model.Employe;
import example.crud.service.EmployeService;
import java.sql.SQLException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

/**
 *
 * @author James
 */
/*public class EmployeResource {   
}*/

@Path("/users")
public class EmployeResource {
    private EmployeService EmpService;

    public EmployeResource() {
        try {
            this.EmpService = new EmployeService();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employe> getEmployes() {
        System.out.println("mety e ");
        return EmpService.getAllEmp();
        
    }
    
    @GET
    @Path("/salary")
    @Produces(MediaType.TEXT_PLAIN)
    public String getValSalary() {
       
        return EmpService.getMaxMinSalary();
      
    }
    

    @GET
    @Path("list/{id}")
    public Employe getEmploye(@PathParam("id") int id) {
        return EmpService.getEmpById(id);
    }

    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddEmploye(
          
            @QueryParam("name") String name,
            @QueryParam("salaire") int salaire) {      
        Employe user = new Employe();
        user.setName(name);
        user.setSalaire(salaire);
        EmpService.addEmp(user);
        
        String jsonResponse = "{\"status\": \"success\"}";

        return Response.ok(jsonResponse).build();
    }
     
   
    
    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmploye(@QueryParam("name") String name,
            @QueryParam("salaire") int salaire , @PathParam("id") int id) {
        Employe user = new Employe();
        user.setName(name);
        user.setSalaire(salaire);
        user.setId(id);
        EmpService.updateEmp(user);
        
        String jsonResponse = "{\"status\": \"success\"}";
        return Response.ok(jsonResponse).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEmploye(@PathParam("id") int id) {
        EmpService.deleteEmp(id);
        return "Employee effacé avec succès";
    }
}
