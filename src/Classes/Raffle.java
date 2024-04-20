package Classes;

public class Raffle {
    private final String nombre;
    private final int priceNumber;
    private final int amountOfNumbers;
    
    //private boolean completada;

    public Raffle(String nombre, int priceNumber, int amountOfNumbers) {
        this.nombre = nombre;
        this.priceNumber = priceNumber;
        this.amountOfNumbers = amountOfNumbers;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPriceNumber() {
        return priceNumber;
    }

    public int getAmountOfNumbers() {
        return amountOfNumbers;
    }
  @Override
public String toString() {
    return nombre;
    //return nombre + " - Precio: $" + priceNumber + " - Cantidad de números: " + amountOfNumbers;
}
  

}
