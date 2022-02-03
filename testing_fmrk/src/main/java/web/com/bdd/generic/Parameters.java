package web.com.bdd.generic;

public final class Parameters {

    private Parameters(){}

    public static final String BROWSER_HEADLESS = "web.browser.headless"; //serenity properties
    public static final String REMOTE_HUB = "web.remote.hub"; //serenity properties
    public static final String WEB_CUSTOM_BROWSER = "web.custom.browser"; //serenity properties
    public static final String CHROME_DRIVER = "chrome";
    public static final String IE_DRIVER = "ie";

    public static final int TIME_OUT = 60;
    public static final double MILL_TO_SECONDS = 0.001;
    public static final String GET = "Get";
    public static final String POST = "Post";
    public static final String DELETE = "Delete";
    public static final String PUT = "Put";

    public static final String FILE_CREATED = "File is created!";
    public static final String FILE_EXIST = "File already exists.";

}
