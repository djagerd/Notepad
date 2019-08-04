import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
        FileChooser fileChooser=new FileChooser();

        AnchorPane anchor=new AnchorPane();



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
              pw=new PrintWriter( new BufferedWriter(new FileWriter(fileChooser.showSaveDialog(null))));


                String line;
                line=text.getText();
                line=line.replaceAll("\n", "\r\n");
                pw.println(line);



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

                  bf  =  new BufferedReader(new FileReader(fileChooser.showOpenDialog(null)));
                String line;
                text.setText(null);
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


        anchor.getChildren().addAll(text);
        VBox.setVgrow(text, Priority.ALWAYS);
        root.getChildren().addAll(menubar,text);
        stage.setScene(scene);
        stage.setTitle("Notepad");
        stage.show();
    }




}
