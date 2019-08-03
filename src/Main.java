import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.Arrays;


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
        FileChooser ch=new FileChooser();

        menu.getItems().add(empty);
        menu.getItems().add(open);
        menu.getItems().add(save);
        MenuBar menubar=new MenuBar();
        menubar.getMenus().add(menu);
        TextArea text=new TextArea();
        Scene scene=new Scene(root,450,350);


        save.setOnAction(event -> {

            PrintWriter pw=null;
            try {
              pw=new PrintWriter( new BufferedWriter(new FileWriter("test.txt")));


                String line;
                line=text.getText();
                line=line.replaceAll("\n", "\r\n");
                pw.println(line);

                System.out.println(line);





        } catch (IOException e) {
                e.printStackTrace();
            }
         finally {
                if(pw!= null) pw.close();
            }

            });

        open.setOnAction(event -> {

            BufferedReader  bf  = null;
            try {
                text.setText(null);
                  bf  =  new BufferedReader(new FileReader("test.txt"));
                String line;

                while((line = bf.readLine()) != null) {
                    text.appendText(line+"\n");
                }


            } catch (Exception e) {
                System.out.println(e);
            }
            finally {
                try {
                    if(bf != null) bf.close();
                }
                catch (IOException e){
                    System.out.println(e);
                }
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
