public class ShowCommand implements Command {
    private Receiver theReceiver;

    public ShowCommand(Receiver receiver){
        this.theReceiver = receiver;
    }
    @Override
    public void execute() {
        theReceiver.showCommand();
    }
}
