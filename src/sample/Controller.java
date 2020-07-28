package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public int totComputers;
    public int totPrinters;
    public int totAV;
    public TableView ctbl;
    //public Label namelabel;
    public TextField name1;
    //public Label typelabel;
    public TextField type1;
    //public Label idlabel;
    public TextField ID1;
    //public Label Locationlabel;
    public TextField location1;
    //public Label usedlabel;
    public TextField used1;
    //public Label statelabel;
    public TextField state1;

    public Button addb;
    public Button viewb;
    public Button saveb;
    public Button delb;
    public Button compb1;
    public Button prinb1;
    public Button avb1;
    public TableView comptbl;
    private ObservableList<Assets> AssetList;
    private ObservableList<Computers> ComputerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        AssetList= FXCollections.observableArrayList();
        ComputerList = FXCollections.observableArrayList();
        //column1
        TableColumn col1 = new TableColumn("Asset ID Number");
        col1.setCellValueFactory(new PropertyValueFactory<Computers,String>("IDNumber"));

        //col2
        TableColumn col2 = new TableColumn("Asset Name");
        col2.setCellValueFactory(new PropertyValueFactory<Computers,String>("AssetName"));

        //col3
        TableColumn col3 = new TableColumn("Asset Type");
        col3.setCellValueFactory(new PropertyValueFactory<Computers,String>("AssetType"));

        //col4
        TableColumn col4 = new TableColumn("Asset Location");
        col4.setCellValueFactory(new PropertyValueFactory<Computers, String>("AssetLocation"));

        //col5
        TableColumn col5 = new TableColumn("Used by");
        col5.setCellValueFactory(new PropertyValueFactory<Computers, String>("UsedBy"));

        //col6
        TableColumn col6 = new TableColumn("Asset State");
        col6.setCellValueFactory(new PropertyValueFactory<Computers, String>("AssetState"));

        //add columns to the table
        ctbl.getColumns().addAll(col1,col2,col3,col4,col5,col6);
        ctbl.setItems(AssetList);


        Computers[] comparray = new Computers[128];
        printer[] printarray = new printer[128];// arrays of objects set to max of 128 objects
        AudioVideo[] AVarray = new AudioVideo[128];

        addb.setOnAction(ActionEvent->// add button
        {
            String id = ID1.getText();
            String nam= name1.getText();
            String tp= type1.getText();
            String lct = location1.getText();
            String ud = used1.getText();
            String sta = state1.getText();
            //implement here
            Assets st = new Assets(id, nam, tp, lct, ud,sta);
            AssetList.add(st);
            try { // st object will be saved to the file
                SaveF.saveFile(st.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // To clear the input fields for next time
            ID1.clear();
            name1.clear();
            type1.clear();
            location1.clear();
            used1.clear();
            state1.clear();


        });



        viewb.setOnAction(ActionEvent->{ // view button
            AssetList.clear();
            ctbl.refresh();
            String infile="assets.csv";
            String FieldDelimiter = ",";
            BufferedReader br;

            try{
                int compi=0;// the reason i have different iterators for the different arrays is because since it can only be one type
                int printi=0;// then the only one can get the things added to it at [n] so if computers has one at [0] then the rest cant have one at [0]
                int AuVidi=0;
                br= new BufferedReader( new FileReader(infile));
                String line;
                while((line=br.readLine())!=null){
                    String [] fields= line.split(FieldDelimiter, -1);
                    Computers comp = new Computers((fields[0]), fields[1], fields[2], fields[3],fields[4],fields[5]);
                    printer pri = new printer((fields[0]), fields[1], fields[2], fields[3],fields[4],fields[5]);
                    AudioVideo av = new AudioVideo((fields[0]), fields[1], fields[2], fields[3],fields[4],fields[5]);
                    if(fields[2] == "Computer"){ // if the third column is == to the type then it gets added to the appropriate array
                           comparray[compi] = comp;
                           compi++;
                    }
                    else if(fields[2] == "Printer"){
                        printarray[printi] = pri;
                        printi++;
                    }
                    else{
                        AVarray[AuVidi]=av;
                        AuVidi++;
                    }
                    AssetList.add(comp);
                }
                totComputers=compi;
                totPrinters=printi;// sets the iterators equal to that of the global variables, they are basically the same and can be used later on
                totAV=AuVidi;

            }catch (IOException ex){
                ex.printStackTrace();
            }
        });

        // edit the cell based on the ID number of the asset
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col1.setOnEditCommit(new EventHandler<CellEditEvent<Assets, String>>() {
            @Override
            public void handle(CellEditEvent <Assets, String > ce) {
                ((Assets)ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setIDNumber(ce.getNewValue());
                ctbl.refresh();
            }
        });

        saveb.setOnAction(ActionEvent->{ // save button
            RewriteF.rewriteFile(AssetList);
        });

        // deletes a selected row

        delb.setOnAction(ActionEvent->{
            AssetList.remove(ctbl.getSelectionModel().getSelectedIndex());
        });

        compb1.setOnAction(ActionEvent->{ // computer buttom
            for(int i=0; i<totComputers;i++){
                    AssetList.add( comparray[i]);
                try { // st object will be saved to the file
                    SaveF.saveFile(comparray[i].toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//end of for
        });

        prinb1.setOnAction(ActionEvent->{ // printer button
            for(int i=0; i<totComputers;i++){
                AssetList.add(printarray[i]);
                try { // st object will be saved to the file
                    SaveF.saveFile(printarray[i].toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//end of for
        });

        avb1.setOnAction(ActionEvent->{ // audio visual button
            for(int i=0; i<totComputers;i++){
                AssetList.add( AVarray[i]);
                try { // st object will be saved to the file
                    SaveF.saveFile(AVarray[i].toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//end of for
        });


    }
}
