package lv.teteris.toms.cardinfo.models;

/**
 * Created by tt007 on 25.07.2017.
 */

public class User {
    public int id;
    public String email;
    public String name;

    public User (int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId() { return this.id; }

    public String getEmail() { return this.email; }

    public String getName() { return this.name; }
}
