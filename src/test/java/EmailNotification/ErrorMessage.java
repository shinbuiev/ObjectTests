package EmailNotification;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by geser on 29.10.16.
 */
public class ErrorMessage {
    private String errorMessage;

    public ErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
