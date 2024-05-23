package org.example.models.language;


public class LanguageResponseDto extends LanguageDto {
    private Long id;

    public LanguageResponseDto(String name, Long id) {
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
