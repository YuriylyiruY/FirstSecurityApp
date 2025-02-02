package SecurityApp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Person")
public class User {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @Column(name = "password")
    @NotEmpty
    private String password;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Person_Auth"
            , joinColumns = @JoinColumn(name = "for_person_id")
            , inverseJoinColumns = @JoinColumn(name = "for_auth_id")
    )
    private Set<Auth> auths = new HashSet<>();


    // Конструктор по умолчанию нужен для Spring
    public User() {
    }

    public User(int age, String email, String name, String password) {
        this.age = age;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void addAuth(Auth auth) {
        if (auths == null) {
            auths = new HashSet<>();
        }
        auths.add(auth);
    }

    public Set<Auth> getAuths() {
        return auths;
    }



    public void setAuths(Auth auth) {
        auths.add(auth);

    }

    @Min(value = 0, message = "Age should be greater than 0")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 0, message = "Age should be greater than 0") int age) {
        this.age = age;
    }

    public @NotEmpty(message = "Email should not be empty") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email should not be empty") @Email String email) {
        this.email = email;
    }

    public int getId() {
        return person_id;
    }

    public void setId(int person_id) {
        this.person_id = person_id;
    }


    public @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name) {
        this.name = name;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "person_id=" + person_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", auths=" + auths +
                '}';
    }
}