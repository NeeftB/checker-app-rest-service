package nl.hva.mobdev.checker.rest.model;

/**
 * An object that gives back a message.
 * This message can be used when a REST request was not successful.
 *
 * @author NeeftB
 */
public class ClientError {

    private String message;

    public ClientError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
