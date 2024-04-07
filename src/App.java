import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.GameBoardPage;

import java.time.Duration;


public class App extends BaseApp {
    static WebElement inputBox = null;
   
    static int fakeGold = -1;
    static int n = 9; //number of gold bars to check

    public static void main(String[] args) throws Exception {
        
       initializeChromeDriver();
       goToUrl();

       GameBoardPage gameBoardPage = new GameBoardPage(driver);

       gameBoardPage.start(); 
       
       System.out.println("End of program");
        // for (int i = 0; i < arr.length; i++) {
        //     //Get the input box
        //     if (i < mid) {
        //         inputBox = driver.findElement(By.id("left_" + arr[i]));
        //         inputBox.sendKeys(String.valueOf(i));
        //     } else if (i > mid){
        //         inputBox = driver.findElement(By.id("right_" + arr[i]));
        //         inputBox.sendKeys(String.valueOf(i));
        //     } 
        // }        
        // WebElement weighButton = driver.findElement(By.id("weigh"));
        // //click on the weigh button
        // weighButton.click();
        // Thread.sleep(5000);

        // WebElement resultElement = driver.findElement(By.xpath("//div[@class='result']/button[@id='reset']"));
        // String result = resultElement.getAttribute("innerText");
        // if (result.equals("=")) {
        //     WebElement box4 = driver.findElement(By.id("coin_4"));
        // }

        //  //get the result and check which weighs less
    
        // System.out.println("The result is: " + result);

        //click reset
        // //div[@class='game']//div//button[@id="reset"]
        // boolean found = false;
        // while (!found) {
        //     found = recursiveSplitAndWeigh();
        // }
      //  recursiveSplitAndWeigh(arr, low, high, mid);

        
        
        // //Get the weighings
        // List<WebElement> weighings = driver.findElements(By.xpath("//div[@class='game-info']//li"));
        // //Print the weighings
        // for (WebElement weighing : weighings) {
        //     System.out.println(weighing.getText());
        // }
    }

    public static void recursiveSplitAndWeigh(int[] arr, int low, int high, int mid) throws InterruptedException{

        System.out.println("In recursive start, n is: " + n);
        if (n <= 1) return;

        WebElement clearWeighBowls = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
        clearWeighBowls.click();
        Thread.sleep(5000);

         for (int i = low; i <= high; i++) {
            //Get the input box
            if (n % 2 != 0 || n < 2) {
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
        n = n/2;        

        WebElement weighButton = driver.findElement(By.id("weigh"));
        //click on the weigh button
        weighButton.click();
        Thread.sleep(5000);
        //get the result and check which weighs less
      //  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        //Thread.sleep(5000);
        System.out.println("Checking for result");
        WebElement resultElement = driver.findElement(By.xpath("//div[@class='result']/button[@id='reset']"));
        
        //Check the result
        String result = resultElement.getAttribute("innerText");
        System.out.println("The result is: " + result);
     
        if (result.equals("=")) {
            WebElement box4 = driver.findElement(By.id("coin_4"));
            box4.click();
            return;
        } else if (result.equals("<")) {
            System.out.println("Less than scenario");
            //Call recursive function with first half
           // enterValues(arr, low, mid-1, (low+(mid-1))/2);
           System.out.println("New low is: " + low);
           System.out.println("New high is: " + (mid-1));
           System.out.println("New mid is: " + (low+(mid-1))/2);
            recursiveSplitAndWeigh(arr, low, mid-1, (low+(mid-1))/2);
        } else {
            //Call recursive function with second half
            System.out.println("Greater than");
           // enterValues(arr, mid+1, high, ((mid+1)+high)/2);
           System.out.println("New low is: " + (mid+1));
           System.out.println("New mid is: " + ((mid+1)+high)/2);
           System.out.println("New high is: " + high);
            recursiveSplitAndWeigh(arr, mid+1, high, ((mid+1)+high)/2);
        }
    
    }

    // public static void enterValues(int[] arr, int count, int firstIndex, int lastIndex){
    //     System.out.println("EnterValues start");

    //     WebElement clearWeighBowls = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
    //     clearWeighBowls.click();
        
    //     System.out.println("Entering values from " + firstIndex + " ending on " + lastIndex + " and n is " + n);
    //     for (int i = firstIndex; i <= lastIndex; i++) {
    //         //Get the input box
    //         if (i <= (firstIndex+lastIndex)/2) {
    //             inputBox = driver.findElement(By.id("left_" + i));
    //             inputBox.sendKeys(String.valueOf(i));
    //         } else {
    //             inputBox = driver.findElement(By.id("right_" + i));
    //             inputBox.sendKeys(String.valueOf(i));
    //         }
    //         WebElement weighButton = driver.findElement(By.id("weigh"));
    //         weighButton.click();
    //     }
    //     System.out.println("Done entering values");
    //     n = n/2;
    // }
}


