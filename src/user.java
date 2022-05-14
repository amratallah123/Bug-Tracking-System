
public class user {


    private String username;
    private String email;
    private String password;
    private int rate;
    public  static String curentuser;
    public  static String currentUserType;
    public String userType;

    user(){ };

    user(String username,String email, String password, String userType){
        this.username=username;
        this.email=email;
        this.password=password;
        this.userType = userType;
        if ( this.userType.equals("tester")|| this.userType.equals("developer")) {this.rate=0;}
    }

    user(String username, String email, String password, String userType, int rate){
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.rate = rate;
    }


    public static void setCurentuser(String curentuser) {
        user.curentuser = curentuser;
    }
    public static void setCurentCode(String code) {
        user.currentUserType = code;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsertype(String usertype) {
        this.userType = usertype;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public int getRate() { return rate; }
    public String getUserType() { return userType; }
}
