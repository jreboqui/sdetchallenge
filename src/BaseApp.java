import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseApp {

    static final String CHALLENGE_URL = "http://sdetchallenge.fetch.com/";
    public static WebDriver driver = new ChromeDriver();
    
    //Change the directory address to where you save chromeDriver
    public static void initializeChromeDriver(){
        System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\Rai\\Desktop\\sdetTest\\sdetChallenge\\chromeDriver\\chromedriver.exe");
    }

    public static void goToUrl(){
        driver.get(CHALLENGE_URL);
    }
    
    public static void goToUrl(String urlString){
        driver.get(urlString);
    }
   

}
