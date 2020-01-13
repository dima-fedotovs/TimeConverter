package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

public class Controller {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String TIME_PATTERN = "HH:mm";
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FORMATTER
            = DateTimeFormatter.ofPattern(TIME_PATTERN);

    public ComboBox<String> departureTimeZone;
    public ComboBox<String> destinationTimeZone;
    public TextField departureDate;
    public TextField departureTime;
    public TextField destinationDate;
    public TextField destinationTime;
    public TextField result;

    public void initialize() {
        var timezones = new TreeSet<>(ZoneId.getAvailableZoneIds());
        departureTimeZone.getItems().addAll(timezones);
        destinationTimeZone.getItems().addAll(timezones);

        var date = LocalDate.now().format(DATE_FORMATTER);
        var time = LocalTime.now().format(TIME_FORMATTER);
        var tz = ZoneId.systemDefault().getId();

        departureDate.setText(date);
        departureTime.setText(time);
        departureTimeZone.getSelectionModel().select(tz);

        destinationDate.setText(date);
        destinationTime.setText(time);
        destinationTimeZone.getSelectionModel().select(tz);

    }

    public void calculate() {
        var strDepartureDate = departureDate.getText();
        var strDepartureTime = departureTime.getText();
        var strDepartureTZ = departureTimeZone.getSelectionModel().getSelectedItem();

        var strDestinationDate = destinationDate.getText();
        var strDestinationTime = destinationTime.getText();
        var strDestinationTZ = destinationTimeZone.getSelectionModel().getSelectedItem();

        var departureZonedDateTime
                = convertToZonedDateTime(strDepartureDate, strDepartureTime, strDepartureTZ);
        var destinationZonedDateTime
                = convertToZonedDateTime(strDestinationDate, strDestinationTime, strDestinationTZ);

        var duration = Duration.between(departureZonedDateTime, destinationZonedDateTime);

        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds / 60) % 60;

        var strDuration = String.format("%d hours and %d minutes", hours, minutes);

        System.out.println(departureZonedDateTime);
        System.out.println(destinationZonedDateTime);
        System.out.println(duration);
        System.out.println(strDuration);

        result.setText(strDuration);
    }

    private ZonedDateTime convertToZonedDateTime(String strDate, String strTime, String strTZ) {
        var date = LocalDate.parse(strDate, DATE_FORMATTER);
        var time = LocalTime.parse(strTime, TIME_FORMATTER);
        var tz = ZoneId.of(strTZ);
        return ZonedDateTime.of(date, time, tz);
    }
}
