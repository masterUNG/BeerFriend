package masterung.androidthai.in.th.beerfriend;

public class MyConstant {

    private String hostString = "ftp.androidthai.in.th";
    private String userString = "bee@androidthai.in.th";
    private String passwordString = "Abc12345";
    private int portAnInt = 21;

    private String urlAddUserString = "http://androidthai.in.th/bee/addUserMaster.php";
    private String urlGetAllUserString = "http://androidthai.in.th/bee/getAllUserMaster.php";
    private String urlEditPostMessageString = "http://androidthai.in.th/bee/editPostMessageByIdMaster.php";

    private String[] columnUserStrings = new String[]{"id", "Name", "User",
            "Password", "PathAvata", "PostMessage"};

    public String getUrlEditPostMessageString() {
        return urlEditPostMessageString;
    }

    public String[] getColumnUserStrings() {
        return columnUserStrings;
    }

    public String getUrlGetAllUserString() {
        return urlGetAllUserString;
    }

    public String getUrlAddUserString() {
        return urlAddUserString;
    }

    public String getHostString() {
        return hostString;
    }

    public String getUserString() {
        return userString;
    }

    public String getPasswordString() {
        return passwordString;
    }

    public int getPortAnInt() {
        return portAnInt;
    }
}
