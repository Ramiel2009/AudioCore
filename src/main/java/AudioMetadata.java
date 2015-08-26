import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import sun.nio.cs.StandardCharsets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Maxim on 8/5/15.
 */
public class AudioMetadata {
    public String getRemoteFolder() {
        return remoteFolder;
    }

    public void setRemoteFolder(String remoteFolder) {
        this.remoteFolder = remoteFolder;
    }

    private String remoteFolder = "";
    private String remoteServerAddress = "";

    public String getRemoteServerAddress() {
        return remoteServerAddress;
    }

    public void setRemoteServerAddress(String remoteServerAddress) {
        this.remoteServerAddress = remoteServerAddress;
    }

    public ArrayList<String> getMp3MetadataList(String pathToMp3) throws NoSuchAlgorithmException {
        ArrayList<String> mp3MetadataList = new ArrayList<String>();
        Mp3File mp3file = null;
        try {
                mp3file = new Mp3File(pathToMp3);
                if (mp3file != null && mp3file.hasId3v1Tag()) {
                    ID3v1 id3v1Tag = mp3file.getId3v1Tag();

                    System.out.println("Track: " + id3v1Tag.getTrack());
                    System.out.println("Artist: " + id3v1Tag.getArtist());
                    System.out.println("Title: " + id3v1Tag.getTitle());
                    System.out.println("Album: " + id3v1Tag.getAlbum());
                    System.out.println("Year: " + id3v1Tag.getYear());
                    System.out.println("Genre: " + id3v1Tag.getGenreDescription());
                    
                  
                    mp3MetadataList.add(id3v1Tag.getTrack());
                    mp3MetadataList.add(id3v1Tag.getArtist());
                    String title = new String (id3v1Tag.getTitle().getBytes("UTF-8"));
                    mp3MetadataList.add(title);
                    mp3MetadataList.add(id3v1Tag.getAlbum());
                    mp3MetadataList.add(id3v1Tag.getYear());
                    mp3MetadataList.add(id3v1Tag.getGenreDescription());
                    MD5Checksum md5 = new MD5Checksum(); //getting MD5 checksum
                    String m = md5.getMD5Checksum(pathToMp3);
                    System.out.println("MD5: " + m);
                    mp3MetadataList.add(m);
                    this.setRemoteServerAddress("http://94.244.34.33");
                    this.setRemoteFolder("/home/ap/Desktop/apache-tomcat-7.0.63/webapps/data/");
                    mp3MetadataList.add(remoteServerAddress+remoteFolder+UploadServlet.mp3FileName);
            }
        } catch (UnsupportedTagException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        } catch (com.mpatric.mp3agic.InvalidDataException e) {
            e.printStackTrace();
        }
        return mp3MetadataList;
    }
}
