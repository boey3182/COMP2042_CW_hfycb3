module com.coursework.cw1dms {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires javafx.media;


    opens com.coursework.cw1dms to javafx.fxml;
    exports com.coursework.cw1dms;
}