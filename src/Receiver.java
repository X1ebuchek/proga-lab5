import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Класс получатель
 */
public class Receiver {
    static List<String> scripts = new ArrayList<String>();
    Reader reader = new Reader();
    static String scriptNow = "";
    static boolean error = false;

    /**
     * выводит справку по доступным командам
     */
    public void helpCommand() {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "history : вывести последние 6 команд (без их аргументов)\n" +
                "remove_any_by_price price : удалить из коллекции один элемент, значение поля price которого эквивалентно заданному\n" +
                "count_by_type type : вывести количество элементов, значение поля type которых равно заданному\n" +
                "filter_less_than_comment comment : вывести элементы, значение поля comment которых меньше заданного");
    }

    /**
     * выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)
     */
    public void infoCommand() {
        System.out.println("Тип: LinkedList");
        System.out.println("Время инициализации: " + Main.creationDate);
        System.out.println("Количество элементов: " + Main.list.size());
    }

    /**
     * выводит в стандартный поток вывода все элементы коллекции в строковом представлении
     */
    public void showCommand() {
        for (int i = 0; i < Main.list.size(); i++) {
            System.out.println(Main.list.get(i).toString());
        }
    }

    /**
     * добавляет новый элемент в коллекцию
     */
    public void addCommand() {
        Long id = System.currentTimeMillis();
        System.out.print("Введите имя: ");
        String name = reader.read(scriptNow);

        float x = 0;
        while (true) {
            System.out.print("Введите x: ");
            try {
                x = Float.parseFloat(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. X должен быть типа float");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x <= -536) {
                System.out.println("X должен быть больше -536");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x != 0) break;
        }

        long y = 0;
        while (true) {
            System.out.print("Введите у: ");
            try {
                y = Long.parseLong(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Y должен быть типа long");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (y != 0) break;
        }

        Coordinates c = new Coordinates(x, y);

        Double price = 0d;
        while (true) {
            System.out.print("Введите цену: ");
            try {
                price = Double.parseDouble(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Цена должна быть типа Double");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (price <= 0) {
                System.out.println("Цена должна быть больше 0");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (price != 0) break;
        }

        String comment = "";
        while (true) {
            System.out.print("Хотите ввести коммент? (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите коммент: ");
                comment = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                comment = null;
                break;
            } else{
                System.out.println("Некорректный ввод");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        boolean refundable = true;
        while (true) {
            System.out.print("Введите возможность возврата: ");
            try {
                String s = reader.read(scriptNow).toLowerCase();
                if (s.equals("true") || s.equals("false")){
                    refundable = Boolean.parseBoolean(s);
                    break;
                }
                else{
                    System.out.println("Некорректное значение. Возможность возврата должна быть типа boolean");
                    if (Main.script){
                        error = true;
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Некорректное значение. Возможность возврата должна быть типа boolean");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String type = "";
        while (true) {
            System.out.print("Введите тип (VIP, USUAL, CHEAP): ");
            type = reader.read(scriptNow).toUpperCase();
            if (type.equals("VIP") || type.equals("USUAL") || type.equals("CHEAP")) break;
            else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        long id1 = System.currentTimeMillis() - 1;
        System.out.print("Введите название ивента: ");
        String name1 = reader.read(scriptNow);

        String description = "";
        while (true) {
            System.out.print("Хотите ввести описание (YES|NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите описание: ");
                description = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                description = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String eventType = "";
        while (true) {
            System.out.print("Хотите ввести тип ивента (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите тип ивента (BASEBALL, BASKETBALL, THEATRE_PERFORMANCE, EXPOSITION): ");
                eventType = reader.read(scriptNow).toUpperCase();
                if (eventType.equals("BASEBALL") || eventType.equals("BASKETBALL") || eventType.equals("THEATRE_PERFORMANCE")
                        || eventType.equals("EXPOSITION")) break;
                else {
                    System.out.println("Некорректное значение");
                    if (Main.script){
                        error = true;
                        return;
                    }
                    continue;
                }
            }
            if (s.equals("NO")) {
                eventType = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        Event e;
        if (eventType == null) e = new Event(id1, name1, description, null);
        else e = new Event(id1, name1, description, EventType.valueOf(eventType));

        Ticket ticket = new Ticket(id, name, c, new Date(), price, comment, refundable, TicketType.valueOf(type), e);
        Main.list.add(ticket);
    }

    /**
     * обновляет значение элемента коллекции, id которого равен заданному
     */
    public void updateCommand() {
        Long id = 0L;
        try {
            id = Long.parseLong(reader.read(scriptNow));
            boolean IdExist = false;
            for (int i = 0; i < Main.list.size(); i++) {
                if (id.equals(Main.list.get(i).getId())) {
                    IdExist = true;
                    break;
                }
            }
            if (!IdExist) {
                System.out.println("Тикета с данным id не существует");
                if (Main.script){
                    error = true;
                    return;
                }
                return;
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод. Id должен быть типа long");
            if (Main.script){
                error = true;
                return;
            }
            return;
        }
        System.out.print("Введите имя: ");
        String name = reader.read(scriptNow);

        float x = 0;
        while (true) {
            System.out.print("Введите x: ");
            try {
                x = Float.parseFloat(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. X должен быть типа float");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x <= -536) {
                System.out.println("X должен быть больше -536");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x != 0) break;
        }

        long y = 0;
        while (true) {
            System.out.print("Введите у: ");
            try {
                y = Long.parseLong(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Y должен быть типа long");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (y != 0) break;
        }
        Coordinates c = new Coordinates(x, y);

        Double price = 0d;
        while (true) {
            System.out.print("Введите цену: ");
            try {
                price = Double.parseDouble(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Цена должна быть типа Double");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (price <= 0) {
                System.out.println("Цена должна быть больше 0");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            break;
        }

        String comment = "";
        while (true) {
            System.out.print("Хотите ввести коммент? (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите коммент: ");
                comment = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                comment = null;
                break;
            } else {
                System.out.println("Некорректный ввод");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        boolean refundable = true;
        while (true) {
            System.out.print("Введите возможность возврата: ");
            try {
                refundable = Boolean.parseBoolean(reader.read(scriptNow));
                break;
            } catch (Exception e) {
                System.out.println("Некорректное значение. Возможность возврата должна быть типа boolean");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String type = "";
        while (true) {
            System.out.print("Введите тип (VIP, USUAL, CHEAP): ");
            type = reader.read(scriptNow).toUpperCase();
            if (type.equals("VIP") || type.equals("USUAL") || type.equals("CHEAP")) break;
            else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        long id1 = System.currentTimeMillis() - 1;
        System.out.print("Введите название ивента: ");
        String name1 = reader.read(scriptNow);
        String description = "";
        while (true) {
            System.out.print("Хотите ввести описание (YES|NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите описание: ");
                description = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                description = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String eventType = "";
        while (true) {
            System.out.print("Хотите ввести тип ивента (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите тип ивента (BASEBALL, BASKETBALL, THEATRE_PERFORMANCE, EXPOSITION): ");
                eventType = reader.read(scriptNow).toUpperCase();
                if (eventType.equals("BASEBALL") || eventType.equals("BASKETBALL") || eventType.equals("THEATRE_PERFORMANCE")
                        || eventType.equals("EXPOSITION")) break;
                else {
                    System.out.println("Некорректное значение");
                    if (Main.script){
                        error = true;
                        return;
                    }
                    continue;
                }
            }
            if (s.equals("NO")) {
                eventType = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }
        Event e;
        if (eventType == null) e = new Event(id1, name1, description, null);
        else e = new Event(id1, name1, description, EventType.valueOf(eventType));

        Ticket ticket = new Ticket(id, name, c, new Date(), price, comment, refundable, TicketType.valueOf(type), e);
        for (int i = 0; i < Main.list.size(); i++) {
            if (id == Main.list.get(i).getId()) {
                Main.list.set(i, ticket);
            }
        }
    }

    /**
     * удаляет элемент из коллекции по его id
     */
    public void remove_by_idCommand() {
        Long id = 0L;
        try {
            id = Long.parseLong(reader.read(scriptNow));
            boolean IdExist = false;
            for (int i = 0; i < Main.list.size(); i++) {
                if (id.equals(Main.list.get(i).getId())) {
                    IdExist = true;
                    Main.list.remove(i);
                    break;
                }
            }
            if (!IdExist) {
                System.out.println("Тикета с данным id не существует");
                if (Main.script){
                    error = true;
                    return;
                }
                return;
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод. Id должен быть типа long");
            if (Main.script){
                error = true;
                return;
            }
            return;
        }
    }

    /**
     * очищает коллекцию
     */
    public void clearCommand() {
        Main.list.clear();
    }

    /**
     * сохраняет коллекцию в файл
     */
    public void saveCommand() {
        System.out.print("Введите название файла: ");
        String s = reader.read(scriptNow);
        File file = new File(s);
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < Main.list.size(); i++) {
                writer.write("\"" + "Ticket" + (i+1) + "\"" + ":{" + "\n");
                writer.write("\t" + "\"" + "id" + "\"" + ":\"" + Main.list.get(i).getId() + "\"," + "\n");
                writer.write("\t" + "\"" + "name" + "\"" + ":\"" + Main.list.get(i).getName() + "\"," + "\n");
                writer.write("\t" + "\"" + "Coordinates" + "\"" + ":{" + "\n");
                writer.write("\t\t" + "\"" + "x" + "\"" + ":\"" + Main.list.get(i).getCoordinates().getX() + "\"," + "\n");
                writer.write("\t\t" + "\"" + "y" + "\"" + ":\"" + Main.list.get(i).getCoordinates().getY() + "\"," + "\n");
                writer.write("\t}\n");
                writer.write("\t" + "\"" + "creationDate" + "\"" + ":\"" + Main.list.get(i).getCreationDate() + "\"," + "\n");
                writer.write("\t" + "\"" + "price" + "\"" + ":\"" + Main.list.get(i).getPrice() + "\"," + "\n");
                writer.write("\t" + "\"" + "comment" + "\"" + ":\"" + Main.list.get(i).getComment() + "\"," + "\n");
                writer.write("\t" + "\"" + "refundable" + "\"" + ":\"" + Main.list.get(i).isRefundable() + "\"," + "\n");
                writer.write("\t" + "\"" + "type" + "\"" + ":\"" + Main.list.get(i).getType() + "\"," + "\n");
                writer.write("\t" + "\"" + "Event" + "\"" + ":{" + "\n");
                writer.write("\t\t" + "\"" + "id" + "\"" + ":\"" + Main.list.get(i).getEvent().getId() + "\"," + "\n");
                writer.write("\t\t" + "\"" + "name" + "\"" + ":\"" + Main.list.get(i).getEvent().getName() + "\"," + "\n");
                writer.write("\t\t" + "\"" + "description" + "\"" + ":\"" + Main.list.get(i).getEvent().getDescription() + "\"," + "\n");
                writer.write("\t\t" + "\"" + "eventType" + "\"" + ":\"" + Main.list.get(i).getEvent().getEventType() + "\"," + "\n");
                writer.write("\t}\n");
                if (i==Main.list.size()-1){
                    writer.write("}");
                }else writer.write("}\n");
            }
            System.out.println("Файл создан");
            writer.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Файл уже существует");
            if (Main.script){
                error = true;
                return;
            }
        }
    }

    /**
     * считывает и исполняет скрипт из указанного файла
     */
    public void execute_scriptCommand() {
        String s = "";
        while (true) {
            s = reader.read(scriptNow);
            File file = new File(s);
            if (file.exists()) break;
            else {
                System.out.println("Такого файла не существует");
                if (Main.script){
                    error = true;
                    return;
                }
                return;
            }
        }
        scriptNow = s;
        for (int i = 0; i < scripts.size(); i++) {
            if (s.equals(scripts.get(i)) && !Main.str.isEmpty()) {
                System.out.println("Скрипты зацикливаются");
                Main.script = false;
                Main.lineCounter = 0;
                Main.str = "";
                return;
            }
        }
        scripts.add(s);
        String s1 = "";
        Main.script = true;
        while (!scriptNow.isEmpty()) {
            if (error){
                System.out.println("В скрипте ошибка");
                Main.script = false;
                Main.lineCounter = 0;
                Main.str = "";
                error = false;
                return;
            }
            System.out.print("Введите команду: ");
            s1 = reader.read(scriptNow);
            Computer.loop(s1);
        }
    }

    /**
     * завершает программу (без сохранения в файл)
     */
    public void exitCommand() {
        System.exit(0);
    }

    /**
     * добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     */
    public void add_if_minCommand() {
        Long id = System.currentTimeMillis();
        System.out.print("Введите имя: ");
        String name = reader.read(scriptNow);

        float x = 0;
        while (true) {
            System.out.print("Введите x: ");
            try {
                x = Float.parseFloat(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. X должен быть типа float");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x <= -536) {
                System.out.println("X должен быть больше -536");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x != 0) break;
        }

        long y = 0;
        while (true) {
            System.out.print("Введите у: ");
            try {
                y = Long.parseLong(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Y должен быть типа long");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (y != 0) break;
        }

        Coordinates c = new Coordinates(x, y);

        Double price = 0d;
        while (true) {
            System.out.print("Введите цену: ");
            try {
                price = Double.parseDouble(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Цена должна быть типа Double");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (price <= 0) {
                System.out.println("Цена должна быть больше 0");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (price != 0) break;
        }

        String comment = "";
        while (true) {
            System.out.print("Хотите ввести коммент? (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите коммент: ");
                comment = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                comment = null;
                break;
            } else {
                System.out.println("Некорректный ввод");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        boolean refundable = true;
        while (true) {
            System.out.print("Введите возможность возврата: ");
            try {
                refundable = Boolean.parseBoolean(reader.read(scriptNow));
                break;
            } catch (Exception e) {
                System.out.println("Некорректное значение. Возможность возврата должна быть типа boolean");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String type = "";
        while (true) {
            System.out.print("Введите тип (VIP, USUAL, CHEAP): ");
            type = reader.read(scriptNow).toUpperCase();
            if (type.equals("VIP") || type.equals("USUAL") || type.equals("CHEAP")) break;
            else System.out.println("Некорректное значение");
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        long id1 = System.currentTimeMillis() - 1;
        System.out.print("Введите название ивента: ");
        String name1 = reader.read(scriptNow);

        String description = "";
        while (true) {
            System.out.print("Хотите ввести описание (YES|NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите описание: ");
                description = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                description = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String eventType = "";
        while (true) {
            System.out.print("Хотите ввести тип ивента (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите тип ивента (BASEBALL, BASKETBALL, THEATRE_PERFORMANCE, EXPOSITION): ");
                eventType = reader.read(scriptNow).toUpperCase();
                if (eventType.equals("BASEBALL") || eventType.equals("BASKETBALL") || eventType.equals("THEATRE_PERFORMANCE")
                        || eventType.equals("EXPOSITION")) break;
                else {
                    System.out.println("Некорректное значение");
                    if (Main.script){
                        error = true;
                        return;
                    }
                    continue;
                }
            }
            if (s.equals("NO")) {
                eventType = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        Event e;
        if (eventType == null) e = new Event(id1, name1, description, null);
        else e = new Event(id1, name1, description, EventType.valueOf(eventType));

        Ticket ticket = new Ticket(id, name, c, new Date(), price, comment, refundable, TicketType.valueOf(type), e);

        Double min = Main.list.get(0).getPrice();
        for (int i = 1; i < Main.list.size(); i++) {
            if (Main.list.get(i).getPrice() < min) min = Main.list.get(i).getPrice();
        }
        if (ticket.getPrice() < min) Main.list.add(ticket);
    }

    /**
     * удаляет из коллекции все элементы, превышающие заданный
     */
    public void remove_greaterCommand() {
        Long id = System.currentTimeMillis();
        System.out.print("Введите имя: ");
        String name = reader.read(scriptNow);

        float x = 0;
        while (true) {
            System.out.print("Введите x: ");
            try {
                x = Float.parseFloat(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. X должен быть типа float");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x <= -536) {
                System.out.println("X должен быть больше -536");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (x != 0) break;
        }

        long y = 0;
        while (true) {
            System.out.print("Введите у: ");
            try {
                y = Long.parseLong(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Y должен быть типа long");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (y != 0) break;
        }

        Coordinates c = new Coordinates(x, y);

        Double price = 0d;
        while (true) {
            System.out.print("Введите цену: ");
            try {
                price = Double.parseDouble(reader.read(scriptNow));
            } catch (Exception e) {
                System.out.println("Некорректное значение. Цена должна быть типа Double");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            if (price <= 0) {
                System.out.println("Цена должна быть больше 0");
                if (Main.script){
                    error = true;
                    return;
                }
                continue;
            }
            break;
        }

        String comment = "";
        while (true) {
            System.out.print("Хотите ввести коммент? (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите коммент: ");
                comment = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                comment = null;
                break;
            } else {
                System.out.println("Некорректный ввод");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        boolean refundable = true;
        while (true) {
            System.out.print("Введите возможность возврата: ");
            try {
                refundable = Boolean.parseBoolean(reader.read(scriptNow));
                break;
            } catch (Exception e) {
                System.out.println("Некорректное значение. Возможность возврата должна быть типа boolean");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String type = "";
        while (true) {
            System.out.print("Введите тип (VIP, USUAL, CHEAP): ");
            type = reader.read(scriptNow).toUpperCase();
            if (type.equals("VIP") || type.equals("USUAL") || type.equals("CHEAP")) break;
            else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        long id1 = System.currentTimeMillis() - 1;
        System.out.print("Введите название ивента: ");
        String name1 = reader.read(scriptNow);

        String description = "";
        while (true) {
            System.out.print("Хотите ввести описание (YES|NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите описание: ");
                description = reader.read(scriptNow);
                break;
            }
            if (s.equals("NO")) {
                description = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        String eventType = "";
        while (true) {
            System.out.print("Хотите ввести тип ивента (YES/NO): ");
            String s = reader.read(scriptNow).toUpperCase();
            if (s.equals("YES")) {
                System.out.print("Введите тип ивента (BASEBALL, BASKETBALL, THEATRE_PERFORMANCE, EXPOSITION): ");
                eventType = reader.read(scriptNow).toUpperCase();
                if (eventType.equals("BASEBALL") || eventType.equals("BASKETBALL") || eventType.equals("THEATRE_PERFORMANCE")
                        || eventType.equals("EXPOSITION")) break;
                else {
                    System.out.println("Некорректное значение");
                    if (Main.script){
                        error = true;
                        return;
                    }
                    continue;
                }
            }
            if (s.equals("NO")) {
                eventType = null;
                break;
            } else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
            }
        }

        Event e;
        if (eventType == null) e = new Event(id1, name1, description, null);
        else e = new Event(id1, name1, description, EventType.valueOf(eventType));

        Ticket ticket = new Ticket(id, name, c, new Date(), price, comment, refundable, TicketType.valueOf(type), e);

        for (int i = 0; i < Main.list.size(); i++) {
            if (Main.list.get(i).getPrice() > ticket.getPrice()) Main.list.remove(i);
        }
    }

    /**
     * выводит последние 6 команд (без их аргументов)
     */
    public void historyCommand() {
        if (Computer.history1.isEmpty()) System.out.print("");
        else System.out.println(Computer.history1);
        if (Computer.history2.isEmpty()) System.out.print("");
        else System.out.println(Computer.history2);
        if (Computer.history3.isEmpty()) System.out.print("");
        else System.out.println(Computer.history3);
        if (Computer.history4.isEmpty()) System.out.print("");
        else System.out.println(Computer.history4);
        if (Computer.history5.isEmpty()) System.out.print("");
        else System.out.println(Computer.history5);
        if (Computer.history6.isEmpty()) System.out.print("");
        else System.out.println(Computer.history6);
    }

    /**
     * удаляет из коллекции один элемент, значение поля price которого эквивалентно заданному
     */
    public void remove_any_by_priceCommand() {
        Double price = 0d;
        try {
            price = Double.parseDouble(reader.read(scriptNow));
        } catch (Exception e) {
            System.out.println("Некорректное значение. Цена должна быть типа Double");
            if (Main.script){
                error = true;
                return;
            }
            return;
        }
        boolean PriceExists = false;
        for (int i = 0;i<Main.list.size();i++){
            if (price.equals(Main.list.get(i).getPrice())){
                PriceExists = true;
                Main.list.remove(i);
            }
        }
        if (!PriceExists){
            System.out.println("Нет тикета с данной ценой");
            if (Main.script){
                error = true;
                return;
            }
        }
    }

    /**
     * выводит количество элементов, значение поля type которых равно заданному
     */
    public void count_by_typeCommand() {
        int k = 0;
        String type = "";
        while (true) {
            type = reader.read(scriptNow).toUpperCase();
            if (type.equals("VIP") || type.equals("USUAL") || type.equals("CHEAP")) break;
            else {
                System.out.println("Некорректное значение");
                if (Main.script){
                    error = true;
                    return;
                }
                return;
            }
        }
        for (int i = 0; i < Main.list.size(); i++) {
            if (TicketType.valueOf(type) == Main.list.get(i).getType()) k++;
        }
        System.out.println(k);
    }

    /**
     * выводит элементы, значение поля comment которых меньше заданного
     */
    public void filter_less_than_commentCommand() {
        String comment = reader.read(scriptNow);
        for (int i = 0; i < Main.list.size(); i++) {
            if (Main.list.get(i).getComment().length() < comment.length()) {
                System.out.println(Main.list.get(i));
            }
        }
    }
}


