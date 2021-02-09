package company;

public enum Position {
    OPERATOR("Оператор"),
    MANAGER("Менеджер"),
    TOP_MANAGER("Топ менеджер");

    private final String namePosition;

    Position(String namePosition) {
        this.namePosition = namePosition;
    }

    public String getNamePosition() {
        return namePosition;
    }
}