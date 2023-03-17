package BusinessLogic;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    enum UserType {
        ADMIN,
        EMPLOYEE,
        CUSTOMER
    }

    private UserType userType;

    public User(int id, String username, String password, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    //region get&set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    //endregion
}
