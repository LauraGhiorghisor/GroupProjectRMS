package view;

import controller.FormsTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FormsTab extends Tab {

    public FormsTab(TabPane tabPane, FXMLLoader loader) throws IOException {
        FormsTab formsTab = new FormsTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/FormsTab.fxml"));
        AnchorPane formsTabContent = loader.load();
        tabPane.getTabs().add(formsTab);
        formsTab.setText("Forms");
        formsTab.setContent(formsTabContent);
        Object temp = loader.getController();
        FormsTabController controller = (FormsTabController) temp;
        tabPane.getSelectionModel().select(formsTab);
    }

    public FormsTab() {

    }
}
