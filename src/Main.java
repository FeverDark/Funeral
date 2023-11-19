

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Controller control = new Controller();
        DB mainData = new DB();
        JFrame mainFrame = new LoginForm("Temp", mainData, control);
        mainFrame.setSize(500, 170);
        mainFrame.setVisible(true);
        while (!mainData.isLogged){
            sleep(0);
        }
        //mainData.getData();
        Runnable taskUpdate = () -> {
            while (mainData.isLogged) {
                mainData.getData();
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread updateThread = new Thread(taskUpdate);
        updateThread.start();
        int oldState = 0;
        while (mainData.isLogged) {
            int tempState = control.getState();
            //System.out.println(tempState);
            if (tempState != oldState || control.isUpdate()) {
                control.setUpdate(false);
                switch (tempState) {
                    case 1:
                        control.selectClient(mainFrame, mainData);
                        break;
                    case 2:
                        control.selectContractor(mainFrame, mainData);
                        break;
                    case 3:
                        control.selectCorpse(mainFrame, mainData);
                        break;
                    case 4:
                        control.selectDocument(mainFrame, mainData);
                        break;
                    case 5:
                        control.selectEmployer(mainFrame, mainData);
                        break;
                    case 6:
                        control.selectGraveyard(mainFrame, mainData);
                        break;
                    case 7:
                        control.selectOrdering(mainFrame, mainData);
                        break;
                    case 8:
                        control.selectOrderPlace(mainFrame, mainData);
                        break;
                    case 9:
                        control.selectOrderProducts(mainFrame, mainData);
                        break;
                    case 10:
                        control.selectOrderServices(mainFrame, mainData);
                        break;
                    case 11:
                        control.selectOrderTransport(mainFrame, mainData);
                        break;
                    case 12:
                        control.selectPlace(mainFrame, mainData);
                        break;
                    case 13:
                        control.selectProduct(mainFrame, mainData);
                        break;
                    case 14:
                        control.selectProductsCategory(mainFrame, mainData);
                        break;
                    case 15:
                        control.selectService(mainFrame, mainData);
                        break;
                    case 16:
                        control.selectTransport(mainFrame, mainData);
                        break;
                }
                //else control.selectClient(mainFrame, mainData);
                oldState = tempState;
            }
        }
    }
}