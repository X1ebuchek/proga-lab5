import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс, который определяет откуда надо считывать(клавиатура, скрипт)
 */
public class Reader {
    int i = 0;
    static long w = 0;

    public String read(String n){
        if (!Main.str.equals(n)){
            if (Main.lineCounter==0){
                Main.lineCounter = LineCounter.count(n);
            }
            Main.str = n;
            Main.sc = new Scanner(System.in);
            try {
                File file = new File(n);
                Main.sc1 = new Scanner(file);
                if (Main.sc1.hasNext()){
                    String q = Main.sc1.next();
                    System.out.println(q);
                    if (q.equals("update") || q.equals("remove_by_id") || q.equals("execute_script") || q.equals("remove_any_by_price")
                            || q.equals("count_by_type") || q.equals("filter_less_than_comment")) Main.lineCounter++;
                    Main.lineCounter--;
                    if (Main.lineCounter<=0){
                        Main.script = false;
                        Main.str = "";
                        Receiver.scriptNow = "";
                        Main.lineCounter = 0;
                    }
                    return q;
                }
                else {
                    Main.str = "";
                    return Main.sc.next();
                }
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                Main.str = "";
                return Main.sc.next();
            }
        }
        if (Main.sc1.hasNext()){
            String e = Main.sc1.next();
            System.out.println(e);
            if (e.equals("update") || e.equals("remove_by_id") || e.equals("execute_script") || e.equals("remove_any_by_price")
            || e.equals("count_by_type") || e.equals("filter_less_than_comment")) Main.lineCounter++;
            Main.lineCounter--;
            if (Main.lineCounter<=0){
                Main.script = false;
                Main.str = "";
                Receiver.scriptNow = "";
                Main.lineCounter = 0;
            }
            return e;
        }
        Main.str = "";
        return Main.sc.next();
    }
}
