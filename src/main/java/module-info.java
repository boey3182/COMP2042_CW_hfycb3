module com.coursework.cw1dms {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires javafx.media;

    exports com.coursework.cw1dms.ModelClasses;
    opens com.coursework.cw1dms.ModelClasses to javafx.fxml;
    exports com.coursework.cw1dms.ControllerClasses;
    opens com.coursework.cw1dms.ControllerClasses to javafx.fxml;
    exports com.coursework.cw1dms.Account;
    opens com.coursework.cw1dms.Account to javafx.fxml;
    exports com.coursework.cw1dms.ViewClasses;
    opens com.coursework.cw1dms.ViewClasses to javafx.fxml;
}