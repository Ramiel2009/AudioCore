import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class MD5Checksum  {
    private String md5checksum;
    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getMD5Checksum(String path) throws NoSuchAlgorithmException, IOException {

        FileInputStream fis = new FileInputStream(new File(path));
        md5checksum = DigestUtils.md5Hex(String.valueOf(fis));
        fis.close();

        return md5checksum;
    }
}


