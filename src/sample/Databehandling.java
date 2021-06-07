package sample;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Databehandling {

    double[] Red = {0, 10, 15, 20, 15, 10, 0, 0, -10, 100, -30, 0, 0, 5, 10, 20, 25, 30, 20, 10, 5, 0};
    int u=0;
    int y=0;
    int red;

    public void ekgSimulation() {
        if (u < 21) {
            u++;
        } else {
            u = 0;
        }
        red = (int) Red[u];
    }

    public int redv() {
        return red;
    }

    ScheduledExecutorService Eventhandler;
    Boolean threadCheck = true;

    public void eventhandlerShutdown() {
        if (!threadCheck) {
            Eventhandler.shutdown();
            threadCheck = true;
        }
    }

    XYChart.Series<String, Number> ekgseries = new XYChart.Series<>();


    public void EKGSim(LineChart ekgplot) {

        if (threadCheck = false) {
            {
                return;
            }
        }
            threadCheck = false;
            Eventhandler = Executors.newSingleThreadScheduledExecutor();

            Eventhandler.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        ekgplot.getData().clear();
                        ekgSimulation();
                        String n = String.valueOf(y);
                        int redval = redv();
                        ekgseries.getData().add(new XYChart.Data<>(n, redval));
                        ekgplot.getData().add(ekgseries);
                        y++;
                            }
                    ), 0, 100, TimeUnit.MILLISECONDS);
        }
    }


