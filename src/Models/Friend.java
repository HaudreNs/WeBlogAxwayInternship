package Models;

import java.sql.Date;

public class Friend{

    private final int id;
    private final int fromId;
    private final int toId;
    private final boolean isAccepted;
    private final Date createdAt;

    public Friend(
            int id,
            int fromId,
            int toId,
            boolean isAccepted,
            Date createdAt
    ) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.isAccepted = isAccepted;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
