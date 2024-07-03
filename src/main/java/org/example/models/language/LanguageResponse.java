package org.example.models.language;


public class LanguageResponse extends Language {
    private Long id;

    public LanguageResponse(String name, Long id) {
        super(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
