package ehu.isad.Model;

public class Checksums {
    private String url;
    private int idCMS;
    private String version;
    private String md5;
    private String  path;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public int getIdCMS() {
        return idCMS;
    }

    public void setIdCMS(int idCMS) {
        this.idCMS = idCMS;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Checksums(String md5) {

        this.md5 = md5;

    }
}
