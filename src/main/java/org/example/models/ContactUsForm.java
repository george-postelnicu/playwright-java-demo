package org.example.models;

public record ContactUsForm(String firstname, String lastname, String company, String email, String jobTitle,
                            String phone, String country, String contactTopic, String message) {
}
