public class Execute_ScriptCommand implements Command {
    private Receiver theReceiver;


    public Execute_ScriptCommand(Receiver receiver){
        this.theReceiver = receiver;
    }
    @Override
    public void execute() {
        theReceiver.execute_scriptCommand();
    }
}
