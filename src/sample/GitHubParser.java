package sample;

import com.oracle.javafx.jmx.json.JSONDocument;
import com.oracle.javafx.jmx.json.JSONFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassiano on 29/08/16.
 */
public class GitHubParser {

    public static GitHubOwner parseOwnerFromJSON(String ownerJSON) {

        GitHubOwner owner = new GitHubOwner();

        try {
            JSONDocument doc = JSONFactory.instance().makeReader(
                    new StringReader(ownerJSON)).build();

            owner.setID(doc.getNumber("id").longValue());
            owner.setLogin(doc.getString("login"));
        }
        catch (Exception e) {}

        return owner;
    }

    private static List<GitHubRepo> parseRepoFromDoc(JSONDocument doc) {

        GitHubRepo repo = new GitHubRepo();
        repo.setOwner(new GitHubOwner());

        ArrayList<GitHubRepo> repos = new ArrayList<>();
        repos.add(repo);

        try {
            repo.setId(doc.getNumber("id").longValue());
            repo.setName(doc.getString("name"));
            repo.getOwner().setID(doc.get("owner").getNumber("id").longValue());
            repo.getOwner().setLogin(doc.get("owner").getString("login"));
        }
        catch (Exception e) {}

        return repos;
    }

    public static List<GitHubRepo> parseReposFromJSON(String repoJSON) {

        List<GitHubRepo> repos = new ArrayList<>();

        try {
            JSONDocument doc = JSONFactory.instance().makeReader(
                    new StringReader(repoJSON)).build();

            if (doc.isObject()) {
                repos = parseRepoFromDoc(doc);
            }
            else if (doc.isArray()) {
                repos = new ArrayList<>(doc.array().size());

                for (Object child : doc.array()) {
                    repos.addAll(parseRepoFromDoc((JSONDocument) child));
                }
            }

        }
        catch (Exception e) {}

        return repos;
    }
}
