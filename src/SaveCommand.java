public class SaveCommand implements Command{
    private Receiver theReceiver;


    public SaveCommand(Receiver receiver){
        this.theReceiver = receiver;

    }
    @Override
    public void execute() {
        theReceiver.saveCommand();
    }
}
