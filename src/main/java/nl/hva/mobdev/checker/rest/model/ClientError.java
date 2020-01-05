package nl.hva.mobdev.checker.rest.model;

public class ClientError {
    private String message;

    public ClientError(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
