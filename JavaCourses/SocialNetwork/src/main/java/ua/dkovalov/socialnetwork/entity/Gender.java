package ua.dkovalov.socialnetwork.entity;

public enum Gender {
    MALE('M'), FEMALE('F');

    private char persistingValue;
    Gender(char persistingValue) {
        this.persistingValue = persistingValue;
    }

    public char forPersistence() {
        return persistingValue;
    }
}
