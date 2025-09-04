package com.triminds.factcheck.model;

public class Evidence {
    private String id;
    private String source;
    private String content;

    public Evidence() {}
    public Evidence(String id, String source, String content) {
        this.id = id;
        this.source = source;
        this.content = content;
    }

    public String getId() { return id; }
    public String getSource() { return source; }
    public String getContent() { return content; }
}
