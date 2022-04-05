public class ExitCommand implements Command {
    private Receiver theReceiver;


    public ExitCommand(Receiver receiver){
        this.theReceiver = receiver;

    }
    @Override
    public void execute() {
        theReceiver.exitCommand();
    }
}
