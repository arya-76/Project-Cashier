public class Cashier {
        // data
        private String username;
        private String password;
        private String usernameDefault;
        private String passswordDefault;

        //constructor
        public Cashier(String username,String password){
            this.username = username;
            this.password = password;
            this.usernameDefault = "admin";
            this.passswordDefault = "admin123";
        }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getUsernameDefault(){
            return usernameDefault;
    }
    public String getPasswordDefault(){
        return passswordDefault;
    }



}
