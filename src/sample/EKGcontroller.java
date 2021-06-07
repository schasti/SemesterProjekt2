package sample;

import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;

public class EKGcontroller extends Databehandling {

    @FXML
    LineChart<String, Number> ekgplot;

    public void startEKG() {
        EKGSim(ekgplot);
    }

    public void EKGstop() {
        eventhandlerShutdown();
    }
}
