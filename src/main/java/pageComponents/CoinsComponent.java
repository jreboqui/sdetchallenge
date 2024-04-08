package pageComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class CoinsComponent extends BaseComponent{

    WebDriver driver;

    private int numOfCoins;

    @FindBy(xpath = "//button[contains(@id,'coin')]")   
    List<WebElement> listOfCoins;

    public CoinsComponent(WebDriver driver){
         super(driver);
    }

    public void setCoins() {
        numOfCoins = listOfCoins.size();
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public void clickOnCoin(int answer) {
        listOfCoins.get(answer).click();
    }
}
