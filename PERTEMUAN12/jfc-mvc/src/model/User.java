/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author m marsa n j
 */
public class User {
    private int id;
    private String name;
    private String email;

    // GETTER AND SETTER
    public int getId(){ return id; };
    public void setId(int id){ this.id = id; };
    
    public String getName(){ return name; };
    public void setName(String name){ this.name = name; };  
    
    public String getEmail(){ return email; };
    public void setEmail(String email){ this.email = email; };
}