/**
 * Created by Maxim on 8/6/15.
 */
public class Error {
    static int statusCode;
    public static void getStatus() {
    switch (statusCode){
        case 1:
            System.out.println("Error ("+statusCode+"): "+"Song already exists");
            break;
        default:
            System.out.println("Done!");
        }
    }
}
