package Learning.models;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    };

    public String getPassword(){
        return this.password;
    }



}
