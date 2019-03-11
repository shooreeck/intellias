package enums;

public enum Reason {

    DEBD_RESTRICKTING ("Umschuldung"),
    AUTO_MOTO("Auto / Motorrad"),
    LIVE("Wohnen"),
    BUSINESS("Gewerbe"),
    FREE_USE("Freie Verwendung");

    private String reasonName;

    Reason(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getReasonName() {
        return reasonName;
    }
}
