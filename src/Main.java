import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import java.io.*;

public class Main extends Application {
    public String lox=null;
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        VBox root = new VBox();
        Menu menu = new Menu("File");
        MenuItem empty = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem about = new MenuItem("About");
        FileChooser fileChooser = new FileChooser();

        AnchorPane anchor = new AnchorPane();


        menu.getItems().add(empty);
        menu.getItems().add(open);
        menu.getItems().add(save);
        menu.getItems().add(saveAs);
        menu.getItems().add(about);
        MenuBar menubar = new MenuBar();
        menubar.getMenus().add(menu);

        TextArea text = new TextArea();
        Scene scene = new Scene(root, 450, 350);


        saveAs.setOnAction(event -> {

            PrintWriter pw = null;
            File file=new File(String.valueOf(fileChooser.showSaveDialog(null)));
                if(lox!=null) {
                    try {
                        pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

                        lox = file.getAbsolutePath();
                        String line;
                        line = text.getText();
                        line = line.replaceAll("\n", "\r\n");
                        pw.println(line + "\n");


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (pw != null) pw.close();
                    }
                }
        });

        open.setOnAction(event -> {

            File file=new File(String.valueOf(fileChooser.showOpenDialog(null)));
            lox=file.getAbsolutePath();
            if(text==null){
                text.setText("Loading ..."+"\r\n");
            }
            Thread t1 = new Thread(new Runnable() {

                @Override
                public void run() {
                    BufferedReader bf = null;
                    try {
                        bf = new BufferedReader(new FileReader(file));

                        String line=null;
                        StringBuilder buffer = new StringBuilder();
                        while ((line=bf.readLine()) != null ) {
                            buffer.append(line+"\n");
                        }
                        text.setText(buffer.toString());

                    } catch (IOException e) {
                        System.out.println(e);
                    } finally {
                        try {
                            if (bf != null){
                                bf.close();

                            }
                        } catch (IOException e) {
                            System.out.println(e);
                        }

                    }
                }

            });


            t1.start();



        });
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);

        });
        save.setOnAction(event -> {
            if(lox!=null) {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(lox));

                    String line;
                    line = text.getText();
                    line = line.replaceAll("\n", "\r\n");
                    bw.write(line + "\n");

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else{
                   File file=new File(String.valueOf(fileChooser.showSaveDialog(null)));
                   lox=file.getAbsolutePath();


            }
        });

            about.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText("by company Djagerd");
                alert.setContentText("Deff83,Djagerd,BioH81");

                alert.showAndWait();
            });


            empty.setOnAction(event -> {

                text.setText(null);
                lox=null;

            });


            anchor.getChildren().addAll(text);
            VBox.setVgrow(text, Priority.ALWAYS);
            root.getChildren().addAll(menubar, text);
            stage.setScene(scene);
            stage.setTitle("Notepad");
            stage.show();
        }


    }

