package volap.example.actionmode_tuan9;

public class ListViewBean {
    int image;
    String langName;
    String moTa;

    public ListViewBean() {
    }

    public ListViewBean(int image,String langName,String moTa) {
        this.image = image;
        this.langName=langName;
        this.moTa=moTa;
    }

    public int getImage() {
        return image;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }
}
