package sample;

/**
 * Created by cassiano on 29/08/16.
 */
public class GitHubOwner {
    private long id;
    private String ownerLogin;

    public void setID(long ownerID) {
        id = ownerID;
    }

    public long getID() {
        return id;
    }

    public void setLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getLogin() {
        return ownerLogin;
    }
}
