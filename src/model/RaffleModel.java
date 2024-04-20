/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Classes.Raffle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RaffleModel {
    private ObservableList<Raffle> raffles;

    // Singleton instance
    private static final RaffleModel instance = new RaffleModel();

    // Private constructor to prevent instantiation
    private RaffleModel() {
        raffles = FXCollections.observableArrayList();
    }

    // Método para obtener la instancia del modelo compartido
    public static RaffleModel getInstance() {
        return instance;
    }

    // Método para obtener la lista de rifas
    public ObservableList<Raffle> getRaffles() {
        return raffles;
    }

    // Método para agregar una nueva rifa al modelo compartido
    public void addRaffle(Raffle raffle) {
        raffles.add(raffle);
    }
}
