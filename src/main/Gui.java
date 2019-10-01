package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.Set;
import java.util.TreeSet;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int Y = 1;

        Main main = new Main();
        Set<News> news =(TreeSet) main.getNews();
        Group group = new Group();

        for(News n : news){
            Hyperlink link = new Hyperlink(n.getTitle()+"\t"+n.getPubDate());
            link.setBorder(Border.EMPTY);
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    getHostServices().showDocument(n.getLink());
                }
            });
            TextFlow textFlow = new TextFlow(link);
            textFlow.setLayoutY(Y);
            Y+=15;
            group.getChildren().add(textFlow);
        }
        stage.setScene(new Scene(group,1000,800));
        stage.show();
    }
}
