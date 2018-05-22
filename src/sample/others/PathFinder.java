package sample.others;

import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class PathFinder {

    private String path;

    public String findPath(Node node) {
        final DirectoryChooser directoryChooser = new DirectoryChooser(); // this is for opening dialogue for choosing dir
        Stage stage = (Stage) node.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);

        if (file != null)
            path = file.getAbsolutePath();

        return path;
    }
}
