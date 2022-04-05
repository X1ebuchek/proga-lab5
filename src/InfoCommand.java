public class InfoCommand implements Command {
    private Receiver theReceiver;

    public InfoCommand(Receiver receiver){
        this.theReceiver = receiver;
    }
    @Override
    public void execute() {
        theReceiver.infoCommand();
    }
}
