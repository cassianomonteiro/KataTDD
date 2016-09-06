package sample;

import sample.github.GitHubFetcher;
import sample.github.GitHubRepo;

import java.util.List;

/**
 * Created by cassiano on 05/09/16.
 */
public class Presenter {
    private GitHubFetcher gitHubFetcher;
    private ViewController viewController;

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

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
