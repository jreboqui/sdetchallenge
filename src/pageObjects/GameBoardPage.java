package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageComponents.CoinsComponent;
import pageComponents.WeighingsComponent;

public class GameBoardPage {
    @FindBy(xpath = "//h1")
    WebElement Header;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    WebElement clearWeighBowls;

    @FindBy(id = "weigh")
    WebElement weighButton;

    @FindBy(xpath = "//div[@class='result']/button[@id='reset']")
    WebElement resultElement;

    WebDriver driver;
    WebElement inputBox = null;

    CoinsComponent coinsComponent;
    WeighingsComponent weighingsComponent;

    private int initialLow;
    private int initialHigh;

    private int answer;

    public GameBoardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        coinsComponent = new CoinsComponent(driver);
        weighingsComponent = new WeighingsComponent(driver);
    }



    public void start(){
        //Get how many gold bars we are going to play with based on the board
        coinsComponent.setCoins();

        setInitialLowMidHigh(coinsComponent.getCoinsArray());

        try {
            answer = recursiveSplitAndWeigh(coinsComponent.getCoinsArray(),
             initialLow, initialHigh, coinsComponent.getNumOfCoins());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        outputListOfWeighings();

        System.out.println("Answer is: " + answer);
        //selectCorrectAnswer();

    }

    //It takes the last item on the weighing list and determines which coin box to select
    private void selectCorrectAnswer() {
      
        coinsComponent.clickOnCoin(answer);

    }



    private void outputListOfWeighings() {
       for (WebElement weighing : weighingsComponent.getWeighingList()){
            System.out.println(weighing.getText());
       }
    }


    // private int recursiveSplitAndWeigh(int[] arr, int low, int mid, int high, int numOfCoins) throws InterruptedException{
    //     System.out.println("In recursive start, n is: " + numOfCoins);
    //     if (numOfCoins <= 1) return low;

       
    //     clearWeighBowls.click();
    //     Thread.sleep(5000);

    //      for (int i = low; i <= high; i++) {
    //         //Get the input box
    //         if (numOfCoins % 2 != 0 || numOfCoins < 2) {
    //             if (i < mid) {
    //                 inputBox = driver.findElement(By.id("left_" + arr[i]));
    //                 inputBox.sendKeys(String.valueOf(i));
    //             } else if (i > mid){
    //                 inputBox = driver.findElement(By.id("right_" + arr[i]));
    //                 inputBox.sendKeys(String.valueOf(i));
    //             } 
    //         } else {
    //             if (i <= mid) {
    //                 inputBox = driver.findElement(By.id("left_" + arr[i]));
    //                 inputBox.sendKeys(String.valueOf(i));
    //             } else {
    //                 inputBox = driver.findElement(By.id("right_" + arr[i]));
    //                 inputBox.sendKeys(String.valueOf(i));
    //             } 
    //         }
           
    //     }
    //     numOfCoins = numOfCoins/2;        

       
    //     //click on the weigh button
    //     weighButton.click();
    //     //get the result and check which weighs less
    //   //  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

    //     Thread.sleep(5000);
    //     System.out.println("Checking for result");
        
        
    //     //Check the result
    //     String result = resultElement.getAttribute("innerText");
    //     System.out.println("The result is: " + result);
     
    //     if (result.equals("=")) {
    //         return mid; //Base case: found the fake gold in the middle
    //         } else if (result.equals("<")) {
    //         System.out.println("Less than scenario");
    //         //Call recursive function with first half
    //        // enterValues(arr, low, mid-1, (low+(mid-1))/2);
    //        System.out.println("New low is: " + low);
    //        System.out.println("New mid is: " + (low+((mid-1)-low))/2);
    //        System.out.println("New high is: " + (mid-1));
          
    //          return recursiveSplitAndWeigh(arr, low, (low+(mid-1))/2,  mid-1, numOfCoins);
    //     } else {
    //         //Call recursive function with second half
    //         System.out.println("Greater than");
    //        // enterValues(arr, mid+1, high, ((mid+1)+high)/2);
    //        System.out.println("New low is: " + (mid+1));
    //        System.out.println("New mid is: " + ((mid+1)+high)/2);
    //        System.out.println("New high is: " + high);
    //          return recursiveSplitAndWeigh(arr, mid+1, ((mid+1)+high)/2, high, numOfCoins);
    //     }
    // }

    private void setInitialLowMidHigh(int[] coinsArray) {
        initialLow = coinsArray[0];
        initialHigh = coinsArray[coinsArray.length-1]; 
    }

    private int recursiveSplitAndWeigh(int[] arr, int low, int high, int numOfCoins) throws InterruptedException{
        System.out.println("In recursive start, n is: " + numOfCoins);
        if (numOfCoins <= 1) return low;

        int mid = (numOfCoins == 2) ? low : (low+high)/2;
        high = (numOfCoins == 2) ? mid+1: high;
        
        clearWeighBowls.click();
        Thread.sleep(5000);

         for (int i = low; i <= high; i++) {
            //Get the input box
            if (numOfCoins % 2 != 0 || numOfCoins < 2) {
                if (i < mid) {
                    inputBox = driver.findElement(By.id("left_" + arr[i]));
                    inputBox.sendKeys(String.valueOf(i));
                } else if (i > mid){
                    inputBox = driver.findElement(By.id("right_" + arr[i]));
                    inputBox.sendKeys(String.valueOf(i));
                } 
            } else {
                if (i <= mid) {
                    inputBox = driver.findElement(By.id("left_" + arr[i]));
                    inputBox.sendKeys(String.valueOf(i));
                } else {
                    inputBox = driver.findElement(By.id("right_" + arr[i]));
                    inputBox.sendKeys(String.valueOf(i));
                } 
            }
           
        }
        numOfCoins = numOfCoins/2;        

       
        //click on the weigh button
        weighButton.click();
        //get the result and check which weighs less
      //  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        Thread.sleep(5000);
        System.out.println("Checking for result");
        
        
        //Check the result
        String result = resultElement.getAttribute("innerText");
        System.out.println("The result is: " + result);
     
        if (result.equals("=")) {
            return mid; //Base case: found the fake gold in the middle
        } else if (result.equals("<")) {
            System.out.println("Less than scenario");
            //Call recursive function with first half
           // enterValues(arr, low, mid-1, (low+(mid-1))/2);
           System.out.println("New low is: " + low);
           System.out.println("New high is: " + (mid-1));
          
            return recursiveSplitAndWeigh(arr, low, mid-1, numOfCoins);
        } else {
            //Call recursive function with second half
            System.out.println("Greater than");
           // enterValues(arr, mid+1, high, ((mid+1)+high)/2);
           System.out.println("New low is: " + (mid+1));
           System.out.println("New high is: " + high);
             return recursiveSplitAndWeigh(arr, mid+1, high, numOfCoins);
        }
    }

}
