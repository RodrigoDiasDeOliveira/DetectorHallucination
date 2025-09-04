package com.triminds.factcheck.model;

public class Claim {
    private String text;
    private String author; // opcional

    // Construtor vazio
    public Claim() {}

    // Construtor com texto
    public Claim(String text) {
        this.text = text;
    }

    // Construtor completo
    public Claim(String text, String author) {
        this.text = text;
        this.author = author;
    }

    // Getters e setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
