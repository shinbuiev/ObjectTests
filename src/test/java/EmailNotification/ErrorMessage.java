package EmailNotification;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by geser on 29.10.16.
 */
public class ErrorMessage {
    private String errorMessage;
    private String fileFolder;
    private ArrayList<String> fileNames;
    private ArrayList<File> files;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ErrorMessage(String errorMessage, String fileFolder, ArrayList<File> fileNames){
        this.errorMessage = errorMessage;
        this.fileFolder = fileFolder;
        this.files = fileNames;
    }

    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    public ArrayList<File> getFiles(){
        return files;
    }

    public ErrorMessage getError(){
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFileFolder() {
        return fileFolder;
    }
}
