package com.jacky.innovex;

public class Compute {
    private int id;
    private int month;
    private String initial;
    private String generated;
    private String initialgenerated;
    private String total;
    private String interest;

    public Compute( int month, String initial, String generated, String initialgenerated, String interest, String total) {
        this.month = month;
        this.initial = initial;
        this.total = total;
        this.interest = interest;
        this.generated = generated;
        this.initialgenerated = initialgenerated;
    }

    public Compute() {}

    public int getMonth() {
        return month;
    }

    public String getInitial() {
        return initial;
    }

    public String getTotal() {
        return total;
    }

    public String getInterest() {
        return interest;
    }

    public String getGenerated() {
        return generated;
    }

    public String getInitialgenerated() {
        return initialgenerated;
    }
}



