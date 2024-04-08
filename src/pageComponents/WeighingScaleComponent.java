package pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WeighingScaleComponent extends BaseComponent {

      WebElement inputBox = null;
      
    public WeighingScaleComponent(WebDriver driver) {
        super(driver);
    }

    public void enterValuesAndWeigh(int[] arr, int low, int high, int mid, int numOfCoins) {
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
    }


    
}
