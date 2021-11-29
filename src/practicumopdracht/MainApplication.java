package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.SpecerijWinkelController;
import practicumopdracht.data.*;


public class MainApplication extends Application {
    private final String TITLE = "Practicumopdracht OOP2 - Nilava Kazal" ;
    private final int WIDTH = 800;
    private final int HEIGHT = 550;
    private static Stage stage;
    private static SpecerijDAO specerijDAO = new ObjectSpecerijDAO();
    private static SpecerijWinkelDAO specerijWinkelDAO = new BinarySpecerijWinkelDAO();


    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }
        MainApplication.stage = stage;
        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        switchController(new SpecerijWinkelController());
    }

    public static void switchController(Controller controller){
        Scene scene = new Scene(controller.getView().getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public static SpecerijWinkelDAO getSpecerijWinkelDAO(){
        return specerijWinkelDAO;
    }

    public static SpecerijDAO getSpecerijDAO(){
        return specerijDAO;
    }

    public static Stage getStage() {
        return stage;
    }
}
