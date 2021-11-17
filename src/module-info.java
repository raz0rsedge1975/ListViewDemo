module ListViewDemo{

    requires javafx.base;
    requires javafx.controls;

    opens ListView.handler.sample to javafx.graphics;
}