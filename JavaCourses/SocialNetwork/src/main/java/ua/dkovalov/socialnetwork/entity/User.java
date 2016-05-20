package ua.dkovalov.socialnetwork.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String nickname;
    private Gender gender;
    private UserType userType;

    public User() {}

    @Id
    @SequenceGenerator(name = "userSequence", sequenceName = "t_user_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="userSequence")
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender.forPersistence();
    }

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    public UserType getUserType() {
        return userType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname.toUpperCase();
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", userType=" + userType +
                '}';
    }
}
