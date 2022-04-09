import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Главный класс
 */
public class Main {
    public static List<Ticket> list = new LinkedList<Ticket>();
    static Date creationDate = new Date();
    public static String str = "";
    public static boolean script = false;
    public static int lineCounter = 0;
    public static Scanner sc1;
    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        Parser.parse(args);

        while (true){
            System.out.print("Введите команду: ");
            String s = sc.next();
            Computer.loop(s);
        }
    }
}
