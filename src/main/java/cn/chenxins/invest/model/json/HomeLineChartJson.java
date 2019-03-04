package cn.chenxins.invest.model.json;

import java.util.HashMap;
import java.util.List;

public class HomeLineChartJson {

    private List<String> xValue;

    private List<Float> endValule;

    private List<Float> profitValue;

    public List<String> getxValue() {
        return xValue;
    }

    public void setxValue(List<String> xValue) {
        this.xValue = xValue;
    }

    public List<Float> getEndValule() {
        return endValule;
    }

    public void setEndValule(List<Float> endValule) {
        this.endValule = endValule;
    }

    public List<Float> getProfitValue() {
        return profitValue;
    }

    public void setProfitValue(List<Float> profitValue) {
        this.profitValue = profitValue;
    }


}
