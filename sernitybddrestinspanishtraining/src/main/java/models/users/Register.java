package models.users;

public class Register {
    private String name;
    private String Job;
    private String email;
    private String password;
    // Getter Methods

    public String getName() {
        return name;
    }

    public String getJob() {
        return Job;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setter Methods

    public void setName( String name ) {
        this.name = name;
    }

    public void setJob( String Job ) {
        this.Job = Job;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}