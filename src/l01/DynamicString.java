package l01;

public interface DynamicString {
	/**
	 * Antal tecken i str�ngen
	 * @return antal tecken i str�ngen
	 */
	public int length();
	
	/**
	 * Returnerar tecknet i angiven position i str�ngen
	 * @param index positionen f�r tecknet som ska returneras
	 * @return tecknet i positionen
	 */
	public char charAt(int index);
	
	/**
	 * Returnerar teckensekvensen som ett String-objket
	 * @return Teckensekvensen som ett String-objekt
	 */
	public String toString();
	
	/**
	 * L�gger till ett tecken sist i str�ngen
	 * @param chr Tecknet som ska l�ggas till
	 */
	public DynamicString append(char chr);
	
	/**
	 * L�gger till tecknen i ett DString-objekt sist i str�ngen
	 * @param str Str�ngen som ska l�ggas till
	 */
	public DynamicString append(DString str);
	
	/**
	 * Tar bort tecken ur str�ngen. Det f�rsta tecknet som tas bort �r i positionen start och 
	 * teckenen fram till positionen (end-1) tas bort
	 * @param start Positionen d�r borttagningen startas
	 * @param end Tecken tas bort fram till positionen end. Tecknet i positionen end �r kvar i str�ngen.
	 */
	public DynamicString delete(int start, int end);
	
	/**
	 * Tar bort tecknet i angiven position
	 * @param index Positionen f�r tecknet som ska tas bort
	 */
	public DynamicString delete(int index);
	
	/**
	 * Returnerar ett String-objekt som best�r av tecknen fr�n positionen start till positionen (end-1)
	 * @param start Positionen start ger det f�rsta tecknet i returstr�ngen
	 * @param end Positionen (end-1) ger det sista tecknet i returstr�ngen
	 * @return Ett String-objekt som inneh�ller tecknen fr�n positionen start till positionen (end-1)
	 */
	public String substring(int start, int end);
	
	/**
	 * Returnerar ett String-objekt som best�r av tecknen fr�n positionen start
	 * @param start Positionen start ger det f�rsta tecknet i returstr�ngen
	 * @return ett String-objekt som inneh�ller tecknen fr.o.m. positionen start
	 */
	public String substring(int start);
	
	/**
	 * Returnerar positionen f�r f�rsta f�rekomsten av tecknet chr. Om tecknet chr ej finns i str�ngen 
	 * returneras -1.
	 * @param chr tecknet som metoden s�ker efter
	 * @return positionen f�r f�rsta f�rekomsten av tecknet i str�ngen. Om tecknet ej finns i str�ngen 
	 * s� returneras -1. 
	 */
	public int indexOf(char chr);
}
