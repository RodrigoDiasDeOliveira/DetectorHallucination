package com.triminds.factcheck.model;

public class Claim {
    private String id;
    private String text;

    public Claim() {}
    public Claim(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() { return id; }
    public String getText() { return text; }
}
