import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;



public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root=new VBox();
        Menu menu=new Menu("File");
        MenuItem empty = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");

        menu.getItems().add(empty);
        menu.getItems().add(open);
        menu.getItems().add(save);
        MenuBar menubar=new MenuBar();
        menubar.getMenus().add(menu);
        TextArea text=new TextArea();
        Scene scene=new Scene(root,450,350);


        save.setOnAction(event -> {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));


                String line;
                line=text.getText();
                bw.write(line);
                bw.close();

                

        } catch (IOException e) {
                e.printStackTrace();
            }
            });

        open.setOnAction(event -> {

            try {
                text.setText(null);
                BufferedReader  bf  =  new BufferedReader(new FileReader("test.txt"));
                String line;
                while((line = bf.readLine()) != null) {
                    text.appendText(line+"\n");

                }
            bf.close();
            } catch (Exception e) {
                System.out.println(e);
            }


        });

        empty.setOnAction(event -> {
            text.setText(null);
        });



        root.getChildren().addAll(menubar,text);
        VBox.setVgrow(text, Priority.ALWAYS);
        stage.setScene(scene);
        stage.setTitle("Java test");
        stage.show();
    }




}
