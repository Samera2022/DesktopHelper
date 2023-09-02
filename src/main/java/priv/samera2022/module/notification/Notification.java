package priv.samera2022.module.notification;

public class Notification {
    private NotificationContent content;
    private boolean isFinished;
    private String startDate;
    private String endDate;
    public Notification(NotificationContent content,boolean isFinished,String startDate,String endDate){
        this.content = content;
        this.isFinished = isFinished;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public NotificationContent getContent() {
        return content;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString(){
        return "{"+isFinished+","+startDate+","+endDate+"}"+content.getContent();
    }
}
