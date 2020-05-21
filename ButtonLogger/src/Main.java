import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TextLogger textLogger = new TextLogger();
        textLogger.setFont(Font.font("Helvetica", 20));

        Logger logger = new Logger();
        logger.setFont(Font.font("Helvetica", 70));
        logger.addObserver(textLogger);

        GridPane root = new GridPane();
        root.add(logger, 0, 0);
        root.add(textLogger, 1, 0);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(50);
        root.getColumnConstraints().add(columnConstraints);

        Scene scene = new Scene(root, 640, 480);
        scene.setOnKeyPressed(keyEvent -> {
            logger.setString(keyEvent.getCode().getName());
        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ButtonLogger");
        stage.show();
    }
}