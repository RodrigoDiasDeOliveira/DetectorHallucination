package com.triminds.factcheck.model;

public class Evidence {
    private String content;
    private String source;
    private String date;

    // Construtor vazio
    public Evidence() {}

    // Construtor apenas com conteúdo
    public Evidence(String content) {
        this.content = content;
    }

    // Construtor completo
    public Evidence(String content, String source, String date) {
        this.content = content;
        this.source = source;
        this.date = date;
    }

    // Getters e setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
