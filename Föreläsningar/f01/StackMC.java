package f01;

public interface StackMC<T> {
    /**
     * Placerar ett element i stacken.
     * @param element elementet att lägga på stacken
     * @return referens till stack-implementeringen
     */
    public StackMC<T> push(T element);
    
    /**
     * Returnerar det element som senast placerades i stacken. Elementet tas bort från stacken.
     * @return referens till det element som senast placerades i stacken
     */
    public T pop();
    
    /**
     * Returnerar det element som senast placerade i stacken. Elementet är kvar i stacken.
     * @return referens till det element som senast placerades i stacken
     */
    public T peek();
    
    /**
     * Returnerar true om stacken inte innehåller några element och false om det finns element i stacken.
     * @return true om stacken är tom och false om det finns element i stacken
     */
    public boolean isEmpty();
    
    /**
     * Returnerar antalet element som finns i stacken.
     * @return antalet element som finns i stacken
     */
    public int size();
}
