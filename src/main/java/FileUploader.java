import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Maxim on 8/6/15.
 */
public class FileUploader {
    private static final Logger log = Logger.getLogger(FileUploader.class);
    public void uploadFile(String urlToFile) throws NoSuchAlgorithmException {
        log.info("getting metadata");
        AudioMetadata am = new AudioMetadata();
        log.info("trying to establish DB connection");
        DbConnector db = new DbConnector();
        log.info("getting url to file");
        db.connection(am.getMp3MetadataList(urlToFile));
        }
    }

