package ua.dkovalov.socialnetwork.entity;

import javax.persistence.*;

@Entity
@Table(name = "SN.T_USER")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "nickname")
    String nickname;
    /*Gender gender;
    UserType userType;*/

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    /*public Gender getGender() {
        return gender;
    }

    public UserType getUserType() {
        return userType;
    }*/

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /*public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public void setUserType(String userType) {
        this.userType = UserType.valueOf(userType);
    }*/
}
