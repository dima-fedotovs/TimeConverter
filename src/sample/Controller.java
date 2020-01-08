package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Set;
import java.util.TreeSet;

public class Controller {


    public ComboBox<String> departureTimeZone;
    public ComboBox<String> destinationTimeZone;
    public TextField departureDate;
    public TextField result;

    public void initialize() {
        var timezones = new TreeSet<>(ZoneId.getAvailableZoneIds());
        departureTimeZone.getItems().addAll(timezones);
        destinationTimeZone.getItems().addAll(timezones);
    }

    public void calculate() {
        var strDepartureDate = departureDate.getText();
        result.setText(strDepartureDate);
        System.out.println("Button clicked! " + strDepartureDate);
    }
}
