package bo.com.bienvenido18.android.model.users;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Map;

@Entity(tableName = "tramites_table")
public class Tramites {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;
    @ColumnInfo(name = "coverPhoto")
    private String coverPhoto;
    @ColumnInfo(name = "titlePhoto")
    private String titlePhoto;
    @ColumnInfo(name = "date")
    private String date;
    //@ColumnInfo(name = "mapaTramitesInfo")
    @Ignore
    //private Map<String, String> mapaTramitesInfo;
    private String listaTramitesInfo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getListaTramitesInfo() {
        return listaTramitesInfo;
    }

    public void setListaTramitesInfo(String listaTramitesInfo) {
        this.listaTramitesInfo = listaTramitesInfo;
    }
/*public List<String> getListaTramitesInfo() {
        return listaTramitesInfo;
    }

    public void setListaTramitesInfo(List<String> listaTramitesInfo) {
        this.listaTramitesInfo = listaTramitesInfo;
    }*/
    /*public Map<String, String> getMapaTramitesInfo() {
        return mapaTramitesInfo;
    }

    public void setMapaTramitesInfo(Map<String, String> mapaTramitesInfo) {
        this.mapaTramitesInfo = mapaTramitesInfo;
    }*/
}
