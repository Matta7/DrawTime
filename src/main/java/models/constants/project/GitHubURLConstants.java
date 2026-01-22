package models.constants.project;

public class GitHubURLConstants {
    protected GitHubURLConstants() {}

    public static final String GITHUB_BASE_URL= "https://github.com/";

    public static final String GITHUB_API_BASE_URL = "https://api.github.com/";

    public static final String GITHUB_DRAWTIME_OWNER = "Matta7";

    public static final String GITHUB_DRAWTIME_REPOSITORY = "DrawTime";

    public static final String GITHUB_DRAWTIME_URL = GITHUB_BASE_URL + GITHUB_DRAWTIME_OWNER + "/" + GITHUB_DRAWTIME_REPOSITORY + "/";

    public static final String GITHUB_DRAWTIME_LATEST_RELEASE_URL = GITHUB_DRAWTIME_URL + "releases/latest";

    public static final String GITHUB_API_DRAWTIME_URL = GITHUB_API_BASE_URL + "repos/" + GITHUB_DRAWTIME_OWNER + "/" + GITHUB_DRAWTIME_REPOSITORY + "/";

    public static final String GITHUB_API_DRAWTIME_LATEST_RELEASE_URL = GITHUB_API_DRAWTIME_URL + "releases/latest";


}
