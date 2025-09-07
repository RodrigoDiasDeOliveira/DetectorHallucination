package com.triminds.factcheck.model;

public class Claim {
    private String text;
    private String metadata; // opcional

    public Claim() {}

    public Claim(String text, String metadata) {
        this.text = text;
        this.metadata = metadata;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
}
