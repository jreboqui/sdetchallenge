package pageComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeighingScaleComponent extends BaseComponent {

    public WeighingScaleComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id[starts-with(., 'left')]]")
    List<WebElement> leftBowl;

    @FindBy(xpath = "//input[@id[starts-with(., 'right')]]")
    List<WebElement> rightBowl;

    @FindBy(xpath = "//div[@class='result']/button[@id='reset']")
    WebElement resultElement;

    @FindBy(id = "weigh")
    WebElement weighButton;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    WebElement clearWeighBowlsButton;



    //poll every .5 seconds for 10 seconds until condition is met
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    
    public void enterValuesAndWeigh(int low, int high, int mid, int numOfCoins) {
         for (int i = low; i <= high; i++) {
            //Loop through low to mid or mid-1 and put them in the left bowl
             // once i is greater than mid, the bars should be put in the right bowl
            if (numOfCoins % 2 != 0) {
                if (i < mid) {
                    leftBowl.get(i).sendKeys(String.valueOf(i));
                } else if (i > mid){
                    rightBowl.get(i).sendKeys(String.valueOf(i));
                } 
            } else {
                if (i <= mid) {
                    leftBowl.get(i).sendKeys(String.valueOf(i));
                } else {
                    rightBowl.get(i).sendKeys(String.valueOf(i));
                } 
            }
        }
    }

    //After clicking the weigh button, it takes a few seconds before the
    //actal result appears. We need to wait for the result status to change
    //from '?' to one of the three '=', '<', '>'
    //This is handled by waitForBoardToGenerateResult
    public String getResult(){
        waitForBoardToGenerateResult();
        return resultElement.getAttribute("innerText");
    }

    private void waitForBoardToGenerateResult() {
         try {
            String defaultText = "?";
            //wait until text changes
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(resultElement,defaultText)));
        }
        catch (Exception e){
            System.out.println("Text of element didn't change");
        }
    }

    public void clickClearWeighBowlsButton() {
        clearWeighBowlsButton.click();
    }


    public void clickWeighButton() {
        weighButton.click();
    }
}
