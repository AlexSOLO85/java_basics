public enum CommandInput {
    COMMAND_LIST("LIST"),
    COMMAND_EXIT("EXIT"),
    INPUT_NAME("^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?$"),
    INPUT_PHONE("^(7)\\d{10}$");

    private final String command;

    CommandInput(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
