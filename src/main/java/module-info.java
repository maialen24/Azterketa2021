open module javafx {
  requires javafx.graphics;
  requires javafx.fxml;
  requires javafx.controls;
  requires com.google.gson;
    requires java.desktop;
  requires com.google.common;
    requires org.apache.commons.codec;


    exports ehu.isad;
}
