package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NotificationTab extends Tab {

    public NotificationTab(TabPane tabPane, FXMLLoader loader) throws IOException {
        NotificationTab notificationTab = new NotificationTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/NotificationsTab.fxml"));
        AnchorPane homeTabContent = loader.load();
        tabPane.getTabs().add(notificationTab);
        notificationTab.setText("Notifications");
        notificationTab.setContent(homeTabContent);
        tabPane.getSelectionModel().select(notificationTab);
    }

    public NotificationTab() {

    }
}
