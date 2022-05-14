import java.util.Date;

public class Bug {
    private String bugName;
    private String bugType;
    private int priority;
    private int bugLevel;
    private String projectName;
    private Date bugDate;
    private String bugStatus;
    private String screenshot;
    private String displayableBugDate;
    private String developer;
    private String tester;

    Bug(){
        bugDate=new Date();
    }
    Bug(String bugName, String bugType, int priority, int bugLevel, String projectName, String bugStatus, String screenshot){
        this.bugName=bugName;
        this.bugType=bugType;
        this.priority=priority;
        this.bugLevel=bugLevel;
        this.projectName=projectName;
        this.screenshot = screenshot;
    }

    Bug(String bugStatus, String bugName, String bugType, int priority, int bugLevel, String projectName, String displayableBugDate, String developer, String tester){
        this.bugStatus=bugStatus;
        this.bugName=bugName;
        this.bugType=bugType;
        this.priority=priority;
        this.bugLevel=bugLevel;
        this.projectName=projectName;
        this.displayableBugDate=displayableBugDate;
        this.developer=developer;
        this.tester=tester;

    }


    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }
    public void setBugName(String bugName){
        this.bugName = bugName;
    }
    public void setBugDate(Date bugDate) {
        this.bugDate = bugDate;
    }
    public void setBugLevel(int bugLevel) {
        this.bugLevel = bugLevel;
    }
    public void setBugType(String bugType) {
        this.bugType = bugType;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setScreenshot(String screenshot) { this.screenshot = screenshot; }
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    public void setTester(String tester) {
        this.tester = tester;
    }
    public Date getBugDate() { return bugDate; }
    public int getBugLevel() {
        return bugLevel;
    }
    public int getPriority() {
        return priority;
    }
    public String getBugName() {
        return bugName;
    }
    public String getBugType() {
        return bugType;
    }
    public String getProjectName() {
        return projectName;
    }
    public String getBugStatus() {
        return bugStatus;
    }
    public String getScreenshot() { return screenshot; }
    public String getDisplayableBugDate() { return displayableBugDate; }
    public String getDeveloper() { return developer; }
    public String getTester() { return tester; }

}
