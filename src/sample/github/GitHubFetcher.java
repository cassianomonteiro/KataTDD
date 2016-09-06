package sample.github;

import java.util.List;

/**
 * Created by cassiano on 05/09/16.
 */
public class GitHubFetcher {
    private HTTPConnector httpConnector;

    public GitHubFetcher() {
        this.httpConnector = new HTTPConnector();
    }

    public void setHTTPConnector(HTTPConnector HTTPConnector) {
        this.httpConnector = HTTPConnector;
    }

    public HTTPConnector getHTTPConnector() {
        return httpConnector;
    }

    public List<GitHubRepo> getUserRepos(String userName) {
        String url = "https://api.github.com/users/" + userName + "/repos";
        return GitHubParser.parseReposFromJSON(
                httpConnector.getContentsFromURLString(url));
    }
}
