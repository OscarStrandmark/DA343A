package f06;

public class HouseProgram {
    public static void main(String[] args)  {
        House house = new House();
        Resting v1 = new Resting("Rolf", 4000, house);
        Resting v2 = new Resting("Jens", 2000, house);
        Resting v3 = new Resting("Dagobert", 6000, house);
        Talking p1 = new Talking("Blondie",  house);
        Talking p2 = new Talking("Katarina",  house);
        v1.start();
        p1.start();
        v2.start();
        v3.start();
        p2.start();
    }
}
