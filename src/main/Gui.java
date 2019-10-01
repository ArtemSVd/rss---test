package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.Set;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int Y = 10;


        Main main = new Main();
        Set<News> news = main.getNews();
        Group group = new Group();

        for(News n : news){

            Text text = new Text();
            text.setText(n.getTitle());
            Hyperlink link = new Hyperlink(n.getLink());
            link.setText(n.getTitle());
            link.setAlignment(Pos.BOTTOM_LEFT);
            text.setTextAlignment(TextAlignment.CENTER);
           // textFlow.set
            text.setX(0);
            text.setY(Y);
            Y+=15;
            //TextFlow textFlow = new TextFlow(text,link);
//            textFlow.getChildren().add(text);
//            textFlow.getChildren().add(link);
           // group.getChildren().add(text);
            group.getChildren().add(link);

            break;
            //Text text1 = new Text();
            //group.getChildren().add(text1);
        }
        stage.setScene(new Scene(group,1000,800));
        stage.show();
    }
}
