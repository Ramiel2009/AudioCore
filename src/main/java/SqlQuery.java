/**
 * Created by Maxim on 8/3/15.
 */
public class SqlQuery {

    private String queryInsertMetadata = "INSERT INTO metadata (track, artist, title, album, year, genre, md5, url)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public String getQueryInsertMetadata() {
        return queryInsertMetadata;
    }
}
