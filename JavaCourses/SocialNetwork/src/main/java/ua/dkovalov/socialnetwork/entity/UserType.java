package ua.dkovalov.socialnetwork.entity;

public enum UserType {
    USER(1), ADMIN(2);
    private int persistingValue;
    private UserType(int persistingValue) {
        this.persistingValue = persistingValue;
    }
    public int forPersistence() {
        return persistingValue;
    }
}
