import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Главный класс
 */
public class Main {
    public static List<Ticket> list = new LinkedList<Ticket>();
    static Date creationDate = new Date();
    public static String str = "";
    static File file2 = new File("Empty.txt");
    public static boolean script = false;
    public static int lineCounter = 0;
    public static Scanner sc1;
    public static Scanner sc = new Scanner(System.in);

    static {
        try {
            sc1 = new Scanner(file2);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Parser.parse(args);
        Coordinates c = new Coordinates(2, 3);
        Event e = new Event(20, "event", "description", EventType.BASEBALL);
        Ticket t = new Ticket(10L, "name", c, creationDate, 100.9, "base", true, TicketType.USUAL, e);
        Ticket t1 = new Ticket(10L, "name12", c, creationDate, 150.7, "base", true, TicketType.USUAL, e);

        while (true){
            System.out.print("Введите команду: ");
            String s = sc.next();
            Computer.loop(s);
        }
    }
}
