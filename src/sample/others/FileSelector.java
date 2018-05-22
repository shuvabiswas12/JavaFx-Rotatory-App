package sample.others;

import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileSelector {

    private File file;

    public File chooseFile(Node node) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select backup.xls file");
        Stage stage = (Stage) node.getScene().getWindow();

        file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getAbsolutePath());

        return file;
    }
}
