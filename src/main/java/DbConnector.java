import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Maxim on 8/5/15.
 */
public class DbConnector {

    private String dbUrl = "jdbc:postgresql://127.0.0.1/audio";
    public String getDbUrl() {
        return dbUrl;
    }
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    private String userName = "root";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






    public void connection(ArrayList<String> s){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connection to DB...");
            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            SqlQuery sq = new SqlQuery();
            MD5Checksum md = new MD5Checksum();


            md.setMd5(s.get(6));
            Statement checkIfExistsStatement = connection.createStatement();

            ResultSet rs = checkIfExistsStatement.executeQuery(
                    "SELECT id FROM metadata WHERE md5 = '" + md.getMd5() + "' ");

            String md5check=null;
            while(rs.next()){
                md5check = rs.getString(1);
                }
            if(md5check==null){
                PreparedStatement insertMetadataStatement = connection.prepareStatement(sq.getQueryInsertMetadata());

                insertMetadataStatement.setString (1, s.get(0));
                insertMetadataStatement.setString (2, s.get(1));
                insertMetadataStatement.setString (3, s.get(2));
                insertMetadataStatement.setString (4, s.get(3));
                insertMetadataStatement.setString(5, s.get(4));
                insertMetadataStatement.setString(6, s.get(5));
                insertMetadataStatement.setString(7, s.get(6));
                insertMetadataStatement.setString(8, s.get(7));
                insertMetadataStatement.execute();
            }
            else
            {
               Error.statusCode=1;

            }
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
