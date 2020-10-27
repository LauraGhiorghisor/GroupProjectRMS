package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeTabTutor extends Tab {

    public HomeTabTutor(TabPane tabPane, FXMLLoader loader) throws IOException {
        HomeTabTutor homeTab = new HomeTabTutor();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutorTab.fxml"));
        AnchorPane homeTabContent = loader.load();
        tabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
        tabPane.getSelectionModel().select(homeTab);
    }

    public HomeTabTutor() {

    }
}
