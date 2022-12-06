module com.coursework.cw1dms {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires javafx.media;

    exports com.coursework.cw1dms;
    exports com.coursework.cw1dms.Game;
    opens com.coursework.cw1dms.Game to javafx.fxml;
    exports com.coursework.cw1dms.ControllerClasses;
    opens com.coursework.cw1dms.ControllerClasses to javafx.fxml;
    exports com.coursework.cw1dms.Account;
    opens com.coursework.cw1dms.Account to javafx.fxml;
}