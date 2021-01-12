package ehu.isad.Controllers.db;



import ehu.isad.Model.Checksums;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }


    public Checksums lortu(String md5) {
        String query = "select md5,version from checksums where md5='" + md5 + "'";
        DBkudeatzaile dbKudeatzaile = DBkudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        Checksums c = null;
        try {
            while (rs.next()) {
                String version = rs.getString("version");
                c = new Checksums(md5);
                c.setVersion(version);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;

    }

    public boolean badago(String md5) throws SQLException {
        String query = "select md5 from checksums where md5='" + md5 + "'";
        DBkudeatzaile dbKudeatzaile = DBkudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        return rs.next();
    }

    public void sartu(Checksums c) {
        String query ="INSERT INTO checksums  values (1,'"+c.getVersion()+"','"+c.getMd5()+"','README')";
        DBkudeatzaile dbKudeatzaile = DBkudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

}