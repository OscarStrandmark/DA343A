package l02;
import java.util.*; // ArrayList
import java.io.*; // BufferedReader, FileReader, IOException

public class Exercise5 {
    
    public static HashMap<String,Population> readPopulation( String filename ) {
        HashMap<String,Population> map = new HashMap<String,Population>();
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"ISO-8859-1"));
            String[] parts;
            Population pop;
            long inhabitants;
            String str = br.readLine();
            while( str != null ) {
                parts = str.split( "," );
                inhabitants = Long.parseLong( parts[ 1 ] ); // befolkningen konverteras till long
                pop = new Population( parts[ 0 ], inhabitants ); // Skapa ett Population-objekt
                map.put( parts[ 0 ], pop ); // (key=landets namn, value=Population-objekt) lagras i HashMappen
                str = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "l�sPersoner: " + e );
        }
        return map;
    }
    
    public static void info( HashMap map ) {
        Iterator<String> iter = map.keySet().iterator();
        Object value;
        Object key;
        while( iter.hasNext() ) {
            key = iter.next();
            value = map.get( key );
            System.out.println( value.toString() );
        }
    }
    
    public static void main( String[] args ) {
        HashMap<String,Population> population = Exercise5.readPopulation( "C:\\Users\\oscar\\git\\DA343A\\src\\l02\\files\\befolkning.txt" );
        Population pop1 = new Population( "Ladonia", 0 ); // Land p� nordsidan av Kullaberg
        Exercise5.info( population ); // H - Skriver ut alla objekt i HashMap
        
        System.out.println( population.toString() ); // A - Returnerar alla objekt i HashMap som en string, mellan m�svingar.
        System.out.println( population.size() ); // B - Returnerar antal objekt som f�rvaras i HashMap
        System.out.println( population.get( "Liberia" ) ); // C - H�mtar v�rdet fr�n angiven key.
        System.out.println( population.containsKey( "Sweden" ) ); // D - Kontrollerar om HashMap inneh�ller nyckel i parameter.
        System.out.println( population.containsKey( "Sverige" ) );
        population.put( "Ladonia", pop1 ); // E - L�gger till objekt i HashMap
        System.out.println( population.toString() );
        System.out.println( population.remove( "Sverige" ) ); // F - Tar bort objektet med nyckeln i parametern.
        population.clear(); // G - Tar bort alla objekt i HashMap
        System.out.println( population.toString() );
    }
}
