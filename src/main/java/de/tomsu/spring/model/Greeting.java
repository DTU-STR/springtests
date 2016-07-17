package de.tomsu.spring.model;

/**
 * The model for a greeting.
 * Created by Daniel on 17.07.2016.
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.content = content;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
