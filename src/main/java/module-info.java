module com.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.escriturarapida to javafx.fxml;
    opens com.example.escriturarapida.model.words to javafx.fxml;
    opens com.example.escriturarapida.model.timer to javafx.fxml;
    opens com.example.escriturarapida.model.gameLogic to javafx.fxml;
    opens com.example.escriturarapida.view to javafx.fxml;

    exports com.example.escriturarapida;
    exports com.example.escriturarapida.controller.result;
    opens com.example.escriturarapida.controller.result to javafx.fxml;
    exports com.example.escriturarapida.controller.game;
    opens com.example.escriturarapida.controller.game to javafx.fxml;
    exports com.example.escriturarapida.controller.welcome;
    opens com.example.escriturarapida.controller.welcome to javafx.fxml;
}