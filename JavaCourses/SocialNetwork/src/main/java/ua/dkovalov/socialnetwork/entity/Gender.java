package ua.dkovalov.socialnetwork.entity;

public enum Gender {
    M("M"), F("F");

    private String persistingValue;
    Gender(String persistingValue) {
        this.persistingValue = persistingValue;
    }

    public String forPersistence() {
        return persistingValue;
    }
}
