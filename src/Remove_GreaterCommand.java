public class Remove_GreaterCommand implements Command{
    private Receiver theReceiver;


    public Remove_GreaterCommand(Receiver receiver){
        this.theReceiver = receiver;

    }
    @Override
    public void execute() {
        theReceiver.remove_greaterCommand();
    }
}
