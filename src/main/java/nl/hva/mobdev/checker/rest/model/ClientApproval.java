package nl.hva.mobdev.checker.rest.model;

public class ClientApproval {

    private String message;

    public ClientApproval(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
