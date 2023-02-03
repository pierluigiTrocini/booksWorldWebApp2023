package it.books_world.controller;

public class JsonResponse {
    private String content;
    private Boolean value;
    
    public void setContent(String content) {
        this.content = content;
    }
    public void setValue(Boolean value) {
        this.value = value;
    }
    public String getContent() {
        return content;
    }
    public Boolean getValue() {
        return value;
    }
}
