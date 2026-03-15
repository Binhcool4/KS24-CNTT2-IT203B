package Cau5;

public class Ticket {
    private String ticketId;
    private boolean isSold = false;
    private boolean isHeld = false;
    private long holdExpiryTime = 0;

    public Ticket(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() { return ticketId; }
    public boolean isAvailable() { return !isSold && !isHeld; }
    public boolean isSold() { return isSold; }
    public void setSold(boolean sold) { isSold = sold; }
    public boolean isHeld() { return isHeld; }
    public void setHeld(boolean held) { isHeld = held; }
    public long getHoldExpiryTime() { return holdExpiryTime; }
    public void setHoldExpiryTime(long holdExpiryTime) { this.holdExpiryTime = holdExpiryTime; }
}