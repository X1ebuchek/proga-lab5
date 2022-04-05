public class AddCommand implements Command{
    private Receiver theReceiver;


    public AddCommand(Receiver receiver){
        this.theReceiver = receiver;
    }
    @Override
    public void execute() {
        theReceiver.addCommand();
    }
}
