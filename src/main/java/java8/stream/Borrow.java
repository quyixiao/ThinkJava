package java8.stream;

import java.math.BigDecimal;

public class Borrow {

    private BigDecimal a ;
    private  BigDecimal b;

    public Borrow() {
    }

    public Borrow(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }
}
