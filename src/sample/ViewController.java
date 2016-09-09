package sample;

import sample.github.GitHubRepo;

import java.util.List;

/**
 * Created by cassiano on 05/09/16.
 */
public interface ViewController {

    void showReposList(List<GitHubRepo> repos);
    void showRepoProperties(List<String> expectedRepoProperties);
}
