package sample;

/**
 * Created by cassiano on 29/08/16.
 */
public class GitHubRepo {

    private long id;
    private String name;
    private GitHubOwner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GitHubOwner getOwner() {
        return owner;
    }

    public void setOwner(GitHubOwner owner) {
        this.owner = owner;
    }
}
