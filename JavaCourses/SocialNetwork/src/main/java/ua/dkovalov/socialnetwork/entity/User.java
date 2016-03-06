package ua.dkovalov.socialnetwork.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String nickname;
    private Gender gender;
    private UserType userType;

    public User() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public char getGender() {
        return gender.forPersistence();
    }

    @Column(name = "user_type_id")
    public int getUserType() {
        return userType.forPersistence();
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
        this.nickname = nickname;
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

    //TODO: find information about the necessity of hasCode and equals methods
}
