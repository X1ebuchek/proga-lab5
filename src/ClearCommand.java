public class ClearCommand implements Command{
    private Receiver theReceiver;


    public ClearCommand(Receiver receiver){
        this.theReceiver = receiver;

    }
    @Override
    public void execute() {
        theReceiver.clearCommand();
    }
}
