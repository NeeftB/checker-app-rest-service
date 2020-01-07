package nl.hva.mobdev.checker.rest.model;

/**
 * An object that gives back a message.
 * This message can be used when a REST request was successful.
 *
 * @author NeeftB
 */
public class ClientApproval {

    private String message;

    public ClientApproval(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
