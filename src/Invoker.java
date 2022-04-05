/**
 * Класс отправитель
 */
public class Invoker {
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command remove_by_idCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exitCommand;
    private Command add_if_minCommand;
    private Command remove_greaterCommand;
    private Command remove_any_priceCommand;
    private Command count_by_typeCommand;
    private Command filter_less_than_commentCommand;
    private Command historyCommand;
    private Command execute_scriptCommand;

    public Invoker(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand, Command updateCommand,
                   Command remove_by_idCommand, Command clearCommand, Command saveCommand, Command exitCommand,
                   Command add_if_minCommand, Command remove_greaterCommand, Command remove_any_priceCommand,
                   Command count_by_typeCommand, Command filter_less_than_commentCommand, Command historyCommand, Command execute_scriptCommand){
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.remove_by_idCommand = remove_by_idCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.exitCommand = exitCommand;
        this.add_if_minCommand = add_if_minCommand;
        this.remove_greaterCommand = remove_greaterCommand;
        this.remove_any_priceCommand = remove_any_priceCommand;
        this.count_by_typeCommand = count_by_typeCommand;
        this.filter_less_than_commentCommand = filter_less_than_commentCommand;
        this.historyCommand = historyCommand;
        this.execute_scriptCommand = execute_scriptCommand;
    }

    public void help(){
        helpCommand.execute();
    }
    public void info(){
        infoCommand.execute();
    }
    public void show(){
        showCommand.execute();
    }
    public void add(){
        addCommand.execute();
    }
    public void update(){
        updateCommand.execute();
    }
    public void remove_by_id(){
        remove_by_idCommand.execute();
    }
    public void clear(){
        clearCommand.execute();
    }
    public void save(){
        saveCommand.execute();
    }
    public void exit(){
        exitCommand.execute();
    }
    public void add_if_min(){
        add_if_minCommand.execute();
    }
    public void remove_greater(){
        remove_greaterCommand.execute();
    }
    public void remove_any_price(){
        remove_any_priceCommand.execute();
    }
    public void count_by_type(){
        count_by_typeCommand.execute();
    }
    public void filter_less_than_comment(){
        filter_less_than_commentCommand.execute();
    }
    public void history(){
        historyCommand.execute();
    }
    public void execute_script(){
        execute_scriptCommand.execute();
    }

}
