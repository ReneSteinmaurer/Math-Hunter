package model;

public class User {
    private String nickname;
    private boolean loggedIn;
    private int points;

    public User() {
        this (null,false,10);
    }

    public User(String nickname, boolean loggedIn, int points) {
        this.nickname = nickname;
        this.loggedIn = loggedIn;
        this.points = points;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
