package l02;
import java.util.*; // ArrayList
import java.io.*; // BufferedReader, FileReader, IOException

public class Exercise1 {
    public static ArrayList<Person> readPersons( String filename ) {
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            BufferedReader br = new BufferedReader( new FileReader( filename ) );
            String[] parts;
            Person person;
            String str = br.readLine();
            while( str != null ) {
                parts = str.split( "," );
                person = new Person( parts[ 0 ], parts[ 1 ], parts[ 2 ] );
                list.add( person );
                str = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "readPersons: " + e );
        }
        return list;
    }
    
    public static void main( String[] args ) {
        ArrayList<Person> list = Exercise1.readPersons( "C:\\Users\\oscar\\git\\DA343A\\src\\l02\\files\\personer.txt" );
        Person person1 = new Person( "761201-7654", "Henry", "Smith" );
        Person person2 = new Person( "011003-4444", "Alma", "Björk" );
        Person p;
        int a;
        
        System.out.println( list.toString() ); // A - Skriver ut hela listan i en sträng.
        
        p = list.get( 2 ); // B - Hämtar objekt i listan med index 2.
        System.out.println( p.toString() ); 
        
        list.add( person1 ); // C - Lägger till personen sist i listan.
        System.out.println( list.toString() );
        
        list.add( 4, person2 ); // D - Lägger till personen vid index 4
        System.out.println( list.toString() );
        
        a = list.size(); // E - Returnerar storleken på listan
        System.out.println( a );
        
        a = list.indexOf( person2 ); // F - Hittar om personen finns i listan och returnerar dess index om dem existerar.
        System.out.println( a );
        
        if( list.contains( person1 ) ) { // G - Kontrollerar om personen finns i listan
            System.out.println( person1.toString() + " FINNS" );
        } else {
            System.out.println( person1.toString() + " FINNS EJ" );
        }
        
        list.remove( 1 ); // H - Tar bort personen på index 1 från listan.
        System.out.println( list.toString() );
        
        list.clear(); // I - Tar bort alla objekt från listan. 
        System.out.println( list.toString() );
    }
}
