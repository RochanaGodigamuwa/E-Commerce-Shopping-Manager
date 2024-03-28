class User {                             //Rochana Godigamuwa Program(20221116)
                                         //Start Date 15.12.2023       //End Date 12.01.2024
    String Username;
    int Password;
    User(String Username, int Password){
        this.Username = Username;
        this.Password = Password;

    }
    //Getters User
    public String getUsername() {
        return Username;
    }

    //Setters User
    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(int password) {
        Password = password;
    }
}
