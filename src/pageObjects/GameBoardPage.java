package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageComponents.CoinsComponent;
import pageComponents.WeighingScaleComponent;
import pageComponents.WeighingsComponent;

public class GameBoardPage {

    @FindBy(id = "weigh")
    WebElement weighButton;

    WebDriver driver;

    CoinsComponent coinsComponent;
    WeighingsComponent weighingsComponent;
    WeighingScaleComponent weighingScaleComponent;

    private int initialLow;
    private int initialHigh;

    private int answer;

    public GameBoardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        coinsComponent = new CoinsComponent(driver);
        weighingsComponent = new WeighingsComponent(driver);
        weighingScaleComponent = new WeighingScaleComponent(driver);

    }

    public void start(){
        init();

        try {
            answer = recursiveSplitAndWeigh(initialLow, initialHigh, coinsComponent.getNumOfCoins());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        outputListOfWeighings();

        selectCorrectAnswer();

        verifyWinnerMessage();

    }

    private void init() {
        setGameCoins();
        setInitialLowMidHigh();
    }

    private void setGameCoins() {
        coinsComponent.setCoins();
    }

    private void verifyWinnerMessage() {
        //get the message from the alert
        System.out.println(driver.switchTo().alert().getText());

    }

    //The answer is returned by the recursive weighing at the end
    private void selectCorrectAnswer() {
      coinsComponent.clickOnCoin(answer);
    }

    private void outputListOfWeighings() {
       for (WebElement weighing : weighingsComponent.getWeighingList()){
            System.out.println(weighing.getText());
       }
    }

    private void setInitialLowMidHigh() {
        initialLow = 0;
        initialHigh = coinsComponent.getNumOfCoins()-1; 
    }

    private int recursiveSplitAndWeigh(int low, int high, int numOfCoins) throws InterruptedException{
        System.out.println("In recursive start, n is: " + numOfCoins);
        if (numOfCoins <= 1) return low;

        int mid = (numOfCoins == 2) ? low : (low+high)/2;
        high = (numOfCoins == 2) ? mid+1: high;
        
        clearWeighBowls();
        
        weighingScaleComponent.enterValuesAndWeigh(low, high, mid, numOfCoins);
       
        numOfCoins = numOfCoins/2;        

       
        //click on the weigh button
        weighButton.click();
        //get the result and check which weighs less
        String result = checkResult();

        if (result.equals("=")) {
            return mid; //Base case: found the fake gold in the middle
        } else if (result.equals("<")) {
            //Call recursive function with first half
            return recursiveSplitAndWeigh(low, mid-1, numOfCoins);
        } else {
            //Call recursive function with second half
             return recursiveSplitAndWeigh(mid+1, high, numOfCoins);
        }
    }

    private void clearWeighBowls() {
        weighingScaleComponent.clickClearWeighBowlsButton();
    }

    private String checkResult() {
        return weighingScaleComponent.getResult();
    }

}
