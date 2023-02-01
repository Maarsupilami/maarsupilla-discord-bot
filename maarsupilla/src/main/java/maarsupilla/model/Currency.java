package maarsupilla.model;

public enum Currency {
    BLOODPOINTS("Bloodpoints"),
    SHARDS("Shards");
    private final String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
