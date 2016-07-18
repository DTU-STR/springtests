package de.tomsu.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The model for the Value of the quote.
 * Created by Daniel on 18.07.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Value {
    private Long id;
    private String quote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
