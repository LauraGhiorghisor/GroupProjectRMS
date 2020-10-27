package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeTabRS extends Tab {

    public HomeTabRS(TabPane tabPane, FXMLLoader loader) throws IOException {
        HomeTabRS homeTab = new HomeTabRS();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageRecordsStaffTab.fxml"));
        AnchorPane homeTabContent = loader.load();
        tabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
        tabPane.getSelectionModel().select(homeTab);
    }

    public HomeTabRS() {

    }
}
