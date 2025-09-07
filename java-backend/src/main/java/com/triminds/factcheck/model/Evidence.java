package com.triminds.factcheck.model;

public class Evidence {
    private String text;
    private String source;
    private String metadata;

    public Evidence() {}

    public Evidence(String text, String source, String metadata) {
        this.text = text;
        this.source = source;
        this.metadata = metadata;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
}
