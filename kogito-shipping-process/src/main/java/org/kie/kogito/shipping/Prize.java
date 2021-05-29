package org.kie.kogito.shipping;

public class Prize {

    private String username;
    private String details;
    private String prizename;
    
    public Prize() {
    }
    
    public Prize(String username, String details, String prizename) {
        this();
        this.username = username;
        this.details = details;
        this.prizename = prizename;
    }

    public String getUsername() {
        return this.username;
    }

    public String getDetails() {
        return this.details;
    }

    public String getPrizename() {
        return this.prizename;
    }

    @Override
    public String toString() {
        return "Prize [username=" + username + ", details=" + details + ", prizename=" + prizename + "]";
    }
}
