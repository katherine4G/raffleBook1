/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author kathe
 */
public class userState {
    
    public int numberR;
    public String nameUser;
    public String numberUser;
    public int status;

    public userState(int numberR, String nameUser, String numberUser, int status) {
        this.numberR = numberR;
        this.nameUser = nameUser;
        this.numberUser = numberUser;
        this.status = status;
    }

    public int getNumberR() {
        return numberR;
    }

    public void setNumberR(int numberR) {
        this.numberR = numberR;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNumberUser() {
        return numberUser;
    }

    public void setNumberUser(String numberUser) {
        this.numberUser = numberUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
   
 
}
