/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crud.model;

/**
 *
 * @author James
 */
public class Employe {

    /**
     * @return the observation
     */
   
    private int idEmp;
    private String name;
    private int salaire;
    private String observation;
    
    public Employe(){
    }
    
    public int getId(){
        return idEmp;
    }
    
    public void setId(int idEmp){
        this.idEmp = idEmp;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getSalaire(){
        return salaire;
    }
    
    public void setSalaire(int salaire){
        this.salaire = salaire;
    }
    
     public String getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }
}
