package com.example.keyframework.bean;

public class layoutBean {
    public Float getToffsetYbili() {
        return ToffsetYbili;
    }

    public void setToffsetYbili(Float toffsetYbili) {
        ToffsetYbili = toffsetYbili;
    }

    public Float getTnumberStart() {
        return TnumberStart;
    }

    public void setTnumberStart(Float tnumberStart) {
        TnumberStart = tnumberStart;
    }

    public Float getTnumberAdd() {
        return TnumberAdd;
    }

    public void setTnumberAdd(Float tnumberAdd) {
        TnumberAdd = tnumberAdd;
    }

    public Float getTnumberMax() {
        return TnumberMax;
    }

    public void setTnumberMax(Float tnumberMax) {
        TnumberMax = tnumberMax;
    }

    public Float getTnumberMin() {
        return TnumberMin;
    }

    public void setTnumberMin(Float tnumberMin) {
        TnumberMin = tnumberMin;
    }

    public Float getTitemRotationX() {
        return TitemRotationX;
    }

    public void setTitemRotationX(Float titemRotationX) {
        TitemRotationX = titemRotationX;
    }

    public layoutBean(Float toffsetYbili, Float tnumberStart, Float tnumberAdd, Float tnumberMax, Float tnumberMin, Float titemRotationX) {
        ToffsetYbili = toffsetYbili;
        TnumberStart = tnumberStart;
        TnumberAdd = tnumberAdd;
        TnumberMax = tnumberMax;
        TnumberMin = tnumberMin;
        TitemRotationX = titemRotationX;
    }

    Float ToffsetYbili = 0.6f;
    Float TnumberStart = 0.6f;
    Float TnumberAdd = 0.6f;
    Float TnumberMax = 0.6f;
    Float TnumberMin = 0.6f;
    Float TitemRotationX = -15f;
}
