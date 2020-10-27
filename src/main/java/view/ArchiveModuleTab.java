package view;

import controller.ModuleTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;

public class ArchiveModuleTab extends Tab {

    public ArchiveModuleTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ArchiveModuleTab moduleTab = new ArchiveModuleTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/ModulesTab.fxml"));
        AnchorPane moduleTabContent = loader.load();
        tabPane.getTabs().add(moduleTab);
        moduleTab.setText("Modules");
        moduleTab.setContent(moduleTabContent);
        Object temp = loader.getController();
        ModuleTabController controller = (ModuleTabController) temp;
        controller.populateArchive();
        tabPane.getSelectionModel().select(moduleTab);
    }

    public ArchiveModuleTab() {

    }
}
