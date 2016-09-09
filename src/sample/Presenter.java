package sample;

import sample.github.GitHubFetcher;
import sample.github.GitHubRepo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cassiano on 05/09/16.
 */
public class Presenter {
    private GitHubFetcher gitHubFetcher;
    private ViewController viewController;

    // Disable default constructor to avoid creating a Presenter without a ViewController
    private Presenter() {}

    public Presenter(ViewController viewController) {
        this.viewController = viewController;
        this.gitHubFetcher = new GitHubFetcher();
    }

    public void setGitHubFetcher(GitHubFetcher gitHubFetcher) {
        this.gitHubFetcher = gitHubFetcher;
    }

    public GitHubFetcher getGitHubFetcher() {
        return gitHubFetcher;
    }

    public void viewRequestedReposFromUser(String username) {
        List<GitHubRepo> repos = gitHubFetcher.getUserRepos(username);
        viewController.showReposList(repos);
    }
    public void viewSelectedRepo(GitHubRepo repo) {

        List<String> repoProperties = Arrays.asList(
                "id: " + repo.getId(),
                "name: " + repo.getName(),
                "language: " + repo.getLanguage(),
                "owner: " + repo.getOwner().getLogin());

        viewController.showRepoProperties(repoProperties);
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
