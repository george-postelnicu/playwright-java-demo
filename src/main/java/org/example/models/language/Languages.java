package org.example.models.language;

import java.util.HashSet;
import java.util.Set;

public class Languages {
    private Set<String> languages = new HashSet<>();

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }
}
