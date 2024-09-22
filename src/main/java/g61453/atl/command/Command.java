package g61453.atl.command;

public interface Command {
    void execute();

    void cancel();

    boolean isReversible();
}
