package sample;

import javafx.beans.property.SimpleStringProperty;

public class Assets { // base class for the rest of the classes

    SimpleStringProperty IDNumber;
    SimpleStringProperty AssetName;
    SimpleStringProperty AssetType; // category
    SimpleStringProperty AssetLocation;
    SimpleStringProperty UsedBy;
    SimpleStringProperty AssetState; // in use, in storage, needs repair

    Assets(String id, String AName,String AType, String ALocation, String UseB, String AState){
        IDNumber = new SimpleStringProperty(id);
        AssetName = new SimpleStringProperty(AName);
        AssetType = new SimpleStringProperty(AType);
        AssetLocation = new SimpleStringProperty(ALocation);
        UsedBy = new SimpleStringProperty(UseB);
        AssetState = new SimpleStringProperty(AState);

    }

    //setter methods
    public void setIDNumber(String id){IDNumber = new SimpleStringProperty(id);}
    public void setAssetName(String AssetName){this.AssetName.set(AssetName);}
    public void setAssetType(String AType) {AssetType = new SimpleStringProperty(AType);}
    public void setAssetLocation(String ALocation) {this.AssetLocation.set(ALocation);}
    public void setUsedBy(String UseB){UsedBy = new SimpleStringProperty(UseB); }
    public void setAssetState(String AState) { this.AssetState.set(AState);}

    public String getIDNumber() {
        return IDNumber.get();
    }

    public String getAssetName() {
        return AssetName.get();
    }

    public String getAssetType() {
        return AssetType.get();
    }

    public String getAssetLocation() {
        return AssetLocation.get();
    }

    public String getUsedBy() {
        return UsedBy.get();
    }

    public String getAssetState() {
        return AssetState.get();
    }

    public String toString(){

        return (getIDNumber()+ ","+getAssetName()+","+getAssetType()+","+getAssetLocation()
                +","+getUsedBy()+","+getAssetState());
    }

}
