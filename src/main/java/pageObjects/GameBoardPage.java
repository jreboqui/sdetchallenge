package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pageComponents.CoinsComponent;
import pageComponents.WeighingScaleComponent;
import pageComponents.WeighingsComponent;

public class GameBoardPage {

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

        answer = recursiveSplitAndWeigh(initialLow, initialHigh, coinsComponent.getNumOfCoins());

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

        //output the winning number one last time
        System.out.println("The fake gold bar is: " + answer);

    }

    //The answer is returned by the recursive weighing at the end
    private void selectCorrectAnswer() {
      coinsComponent.clickOnCoin(answer);
    }

    private void outputListOfWeighings() {
       System.out.println("//**********************************************//");
       System.out.println("//   The following weighings are displayed      //");
       System.out.println("//**********************************************//");
       for (WebElement weighing : weighingsComponent.getWeighingList()){
            System.out.println(weighing.getText());
       }
    }

    private void setInitialLowMidHigh() {
        initialLow = 0;
        initialHigh = coinsComponent.getNumOfCoins()-1; 
    }

    private int recursiveSplitAndWeigh(int low, int high, int numOfCoins){

        //Returning what is set to low because that is the answer once numOfCoins is less than 2
        if (numOfCoins <= 1) return low;

        //When you have only two coins/bars left to weigh, you need to bump mid and high
        int mid = (numOfCoins == 2) ? low : (low+high)/2;
        high = (numOfCoins == 2) ? mid+1: high;
        
        clearWeighBowls();
        
        weighingScaleComponent.enterValuesAndWeigh(low, high, mid, numOfCoins);

        //At this point, the coins we need to check will be split into half
        numOfCoins = numOfCoins/2;        

       
        //click on the weigh button
        clickWeighButton();

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

    private void clickWeighButton() {
        weighingScaleComponent.clickWeighButton();
    }

    private void clearWeighBowls() {
        weighingScaleComponent.clickClearWeighBowlsButton();
    }

    private String checkResult() {
        return weighingScaleComponent.getResult();
    }

}
