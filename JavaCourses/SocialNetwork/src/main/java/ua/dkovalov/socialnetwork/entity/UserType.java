package ua.dkovalov.socialnetwork.entity;

import javax.persistence.*;

@Entity
@Table(name = "ref_user_type")
public class UserType {
    private Integer userTypeId;
    private String userTypeName;
    private String brief;

    public UserType() {};

    @Id
    @GeneratedValue
    @Column(name = "user_type_id")
    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Column(name = "user_type_name")
    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    @Column(name = "brief")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
