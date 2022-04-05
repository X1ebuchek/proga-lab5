import java.util.Date;
import java.util.Scanner;

/**
 * Класс клиент
 */
public class Computer {

    static String history1 = "";
    static String history2 = "";
    static String history3 = "";
    static String history4 = "";
    static String history5 = "";
    static String history6 = "";
    public static void loop(String s) {

        Receiver r = new Receiver();
        Command helpCommand = new HelpCommand(r);
        Command infoCommand = new InfoCommand(r);
        Command showCommand = new ShowCommand(r);
        Command addCommand = new AddCommand(r);
        Command updateCommand = new UpdateCommand(r);
        Command remote_by_idCommand = new Remove_By_IdCommand(r);
        Command clearCommand = new ClearCommand(r);
        Command saveCommand = new SaveCommand(r);
        Command exitCommand = new ExitCommand(r);
        Command add_if_minCommand = new Add_If_MinCommand(r);
        Command remove_greaterCommand = new Remove_GreaterCommand(r);
        Command remove_any_priceCommand = new Remove_Any_By_PriceCommand(r);
        Command count_by_typeCommand = new Count_By_TypeCommand(r);
        Command filter_less_than_commentCommand = new Filter_Less_Than_CommentCommand(r);
        Command historyCommand = new HistoryCommand(r);
        Command execute_scriptCommand = new Execute_ScriptCommand(r);

        Invoker i = new Invoker(helpCommand, infoCommand, showCommand, addCommand, updateCommand,
                remote_by_idCommand, clearCommand, saveCommand, exitCommand, add_if_minCommand,
                remove_greaterCommand, remove_any_priceCommand, count_by_typeCommand, filter_less_than_commentCommand,
                historyCommand, execute_scriptCommand);


        history1 = history2;
        history2 = history3;
        history3 = history4;
        history4 = history5;
        history5 = history6;
        history6 = s;

            switch (s) {
                case "help":
                    i.help();
                    break;
                case "info":
                    i.info();
                    break;
                case "show":
                    i.show();
                    break;
                case "add":
                    i.add();
                    break;
                case "exit":
                    i.exit();
                case "history":
                    i.history();
                    break;
                case "execute_script":
                    i.execute_script();
                    break;
                case "update":
                    i.update();
                    break;
                case "remove_by_id":
                    i.remove_by_id();
                    break;
                case "clear":
                    i.clear();
                    break;
                case "save":
                    i.save();
                    break;
                case "add_if_min":
                    i.add_if_min();
                    break;
                case "remove_greater":
                    i.remove_greater();
                    break;
                case "remove_any_by_price":
                    i.remove_any_price();
                    break;
                case "count_by_type":
                    i.count_by_type();
                    break;
                case "filter_less_than_comment":
                    i.filter_less_than_comment();
                    break;
                case "rer":
                    System.out.println(Main.script);
                    System.out.println(Main.str);
                    System.out.println(Main.lineCounter);
                    break;
                default:
                    System.out.println("Такой команды не существует");
            }
    }
}
