
import pageObjects.GameBoardPage;

public class App extends BaseApp {

    public static void main(String[] args) throws Exception {
        
       initializeChromeDriver();
       goToUrl();

       GameBoardPage gameBoardPage = new GameBoardPage(driver);

       gameBoardPage.start(); 
       
       System.out.println("End of program");
    }
    
}


