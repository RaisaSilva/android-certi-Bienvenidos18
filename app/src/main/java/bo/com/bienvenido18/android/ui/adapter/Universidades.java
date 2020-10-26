package bo.com.bienvenido18.android.ui.adapter;

import java.util.List;

public class Universidades {
    private String uuid;
    private String coverPhoto;
    private String displayName; //Bolivian Foods SRL --> Burger King
    private String address;
    private String telefonos;
    private String carreras;
    private String links;

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCarreras() {
        return carreras;
    }

    public String getUuid() {
        return uuid;
    }
    public void setCarreras(String carreras) {
        this.carreras = carreras;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
