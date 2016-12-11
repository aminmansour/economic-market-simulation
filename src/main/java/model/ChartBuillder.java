package model;

import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Sarosi on 26/11/2016.
 */
public class ChartBuillder {
    public ChartBuillder(){

    }

    /**
     * this method takes the data generated by Arraybuilder and constructs a chart based on it
     * @param dataArray county code, data and year sorted grouped by country and then by year
     * @return a Linechart object representing our data
     */
    public  static LineChart<String,Number> buildLineChart(ArrayList<ArrayList<DataPiece>> dataArray) {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("Year");
        try {
            yAxis.setLabel(dataArray.get(0).get(0).getIndicator());


            ArrayList<XYChart.Series> SerialKiller = new ArrayList<XYChart.Series>();


            for (int q = 0; q < dataArray.size(); q++) {
                SerialKiller.add(new XYChart.Series());
                SerialKiller.get(q).setName(dataArray.get(q).get(0).getCountry());

                for (int i = 0; i < dataArray.get(q).size(); i++) {
                    System.out.println(SerialKiller.get(q).getName());
                    SerialKiller.get(q).getData().add(new XYChart.Data(dataArray.get(q).get(i).getYear(), Double.parseDouble(dataArray.get(q).get(i).getValue())));
                }
                checkNoData(SerialKiller.get(q));
                lineChart.getData().add(SerialKiller.get(q));

            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("css/chartPane-style.css");
            dialogPane.getStyleClass().add("alert");
            alert.setTitle("Error");
            alert.setHeaderText("No data found");
            alert.setContentText("There is no data for this coutry in this time period.");

            alert.showAndWait();
        }

        return lineChart;




    }

    public  static BarChart<String,Number> buildBarChart(ArrayList<ArrayList<DataPiece>> dataArray) {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel(dataArray.get(0).get(0).getIndicator());
        final BarChart<String, Number> lineChart =
                new BarChart<String, Number>(xAxis, yAxis);

        ArrayList<XYChart.Series> SerialKiller = new ArrayList<XYChart.Series>();


        for (int q = 0; q < dataArray.size(); q++) {
            SerialKiller.add(new XYChart.Series());
            SerialKiller.get(q).setName(dataArray.get(q).get(0).getCountry());

            for (int i = 0; i < dataArray.get(q).size(); i++) {
                System.out.println(SerialKiller.get(q).getName());
                SerialKiller.get(q).getData().add(new XYChart.Data(dataArray.get(q).get(i).getYear(), Double.parseDouble(dataArray.get(q).get(i).getValue())));
            }
                checkNoData(SerialKiller.get(q));
            lineChart.getData().add(SerialKiller.get(q));

        }

        return lineChart;
    }

    static void checkNoData(XYChart.Series<Integer,Double> series) {
        double d1 = 0;
        XYChart.Data<Integer,Double> last = null;
        for (Object data : series.getData()) {
            if (data instanceof XYChart.Data<?,?>) {
                XYChart.Data<Integer,Double> cdata = (XYChart.Data<Integer,Double>)data;
                if (last != null && last.getYValue() == null) {
                    double mid = (d1 + cdata.getYValue())/2;
                    last.setYValue(mid);
                    Text nodata = new Text("no data");
                    nodata.setTranslateY(nodata.getLayoutBounds().getHeight()/2);
                    last.setNode(nodata);
                }
                if (last != null) d1 = last.getYValue();
                last = cdata;
            }
        }
        if (last != null && last.getYValue() == null) {
            last.setYValue(d1);
            Text nodata = new Text("no data");
            nodata.setTranslateY(nodata.getLayoutBounds().getHeight()/2);
            last.setNode(nodata);

        }
    }

}
