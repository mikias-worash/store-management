package main.store;

public class Account {
    private int accId;
    private String userName;
    private String password;
    private String role;
    public boolean isLogged;

//    public Account() {
//        this.accId = -1;
//        this.userName = "unknown";
//        this.password = "unknown";
//        this.role = "unknown";
//        this.isLogged = false;
//    }

    public Account(int accId, String userName, String role ,String password) {
        this.accId = accId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getAccId() {
        return accId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public boolean authentication( String password) {
        if (password.equals(this.password)) {
            isLogged = true;
            return true;
        }
        return false;
    }
}
