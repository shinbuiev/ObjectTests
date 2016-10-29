package EmailNotification;

/**
 * Created by geser on 29.10.16.
 */
public class ErrorMessage {
    private String errorMessage;
    private String fileName1;
    private String fileName2;
    private String fileFolder;

    public ErrorMessage(String errorMessage, String fileFolder, String fileName1, String fileName2){
        this.errorMessage = errorMessage;
        this.fileFolder = fileFolder;
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFileName1() {
        return fileName1;
    }

    public String getFileName2() {
        return fileName2;
    }

    public String getFileFolder() {
        return fileFolder;
    }
}
