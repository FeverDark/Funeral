import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;


class ComboItem {
    private final String key;
    private final String value;

    public ComboItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

public class Main {
    protected static Image getFDImage() {
        java.net.URL imgURL = Main.class.getResource("images/favicon.png");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Controller control = new Controller();
        DB mainData = new DB();
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame mainFrame = new LoginForm("Похоронное бюро", mainData, control);
        mainFrame.setIconImage(getFDImage());
        mainFrame.setSize(1200, 800);
        mainFrame.setVisible(true);
        while (!mainData.isLogged) {
            sleep(0);
        }
        //mainData.getData();
        Runnable taskUpdate = () -> {
            while (mainData.isLogged) {
                mainData.getData();
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread updateThread = new Thread(taskUpdate);
        //updateThread.start();
        int oldState = 0;
        while (mainData.isLogged) {
            int tempState = control.getState();
            if (tempState != oldState || control.isUpdate()) {
                control.setUpdate(false);
                new Thread(new Runnable() {
                    public void run() {
                        mainData.getData();
                    }
                }).start();
                Thread.sleep(100);
                switch (tempState) {
                    case 1:
                        control.selectData(mainFrame, mainData, mainData.client, mainData.clientNames, "Клиенты", 1);
                        break;
                    case 2:
                        control.selectData(mainFrame, mainData, mainData.contractor, mainData.contractorNames, "Подрядчики", 0);
                        break;
                    case 3:
                        control.selectData(mainFrame, mainData, mainData.corpse, mainData.corpseNames, "Тела", 1);
                        break;
                    case 4:
                        control.selectData(mainFrame, mainData, mainData.documentConverted, mainData.documentNames, "Документы", 1);
                        break;
                    case 5:
                        control.selectData(mainFrame, mainData, mainData.employer, mainData.employerNames, "Сотрудники", 1);
                        break;
                    case 6:
                        control.selectData(mainFrame, mainData, mainData.graveyard, mainData.graveyardNames, "Кладбища", 1);
                        break;
                    case 7:
                        control.selectData(mainFrame, mainData, mainData.orderingConverted, mainData.orderingNames, "Заказы", 1);
                        break;
                    case 8:
                        control.selectData(mainFrame, mainData, mainData.orderPlaceConverted, mainData.orderPlaceNames, "Места заказа", 0);
                        break;
                    case 9:
                        control.selectData(mainFrame, mainData, mainData.orderProductsConverted, mainData.orderProductsNames, "Товары заказа", 0);
                        break;
                    case 10:
                        control.selectData(mainFrame, mainData, mainData.orderServicesConverted, mainData.orderServicesNames, "Услуги заказа", 0);
                        break;
                    case 11:
                        control.selectData(mainFrame, mainData, mainData.orderTransportConverted, mainData.orderTransportNames, "Транспорт заказа", 0);
                        break;
                    case 12:
                        control.selectData(mainFrame, mainData, mainData.place, mainData.placeNames, "Места", 1);
                        break;
                    case 13:
                        control.selectData(mainFrame, mainData, mainData.productConverted, mainData.productNames, "Товары", 1);
                        break;
                    case 14:
                        control.selectData(mainFrame, mainData, mainData.productsCategory, mainData.productsCategoryNames, "Категории товаров", 1);
                        break;
                    case 15:
                        control.selectData(mainFrame, mainData, mainData.service, mainData.serviceNames, "Услуги", 1);
                        break;
                    case 16:
                        control.selectData(mainFrame, mainData, mainData.transport, mainData.transportNames, "Транспорт", 1);
                        break;
                }
                oldState = tempState;
            }
        }
    }
}