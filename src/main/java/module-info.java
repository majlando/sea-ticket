module dk.easv.ticket {
    requires javafx.controls;
    requires javafx.fxml;

    opens dk.easv.ticket to javafx.fxml;
    opens dk.easv.ticket.be to javafx.fxml, javafx.base;
    opens dk.easv.ticket.gui.model to javafx.fxml;
    opens dk.easv.ticket.gui.controller to javafx.fxml;

    exports dk.easv.ticket;
}