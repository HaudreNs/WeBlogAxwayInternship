package Models;

import java.util.ArrayList;

public class FriendsList extends ArrayList<Friend> {
    public boolean isFriend(int userId) {
        for (Friend friend : this) {
            if (friend.isAccepted() && (friend.getFromId() == userId || friend.getToId() == userId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSentMeUnaccepted(int userId) {
        for (Friend friend : this) {
            if (friend.getFromId() == userId && !friend.isAccepted()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasReceivedFromMeUnaccapted(int userId) {
        for (Friend friend : this) {
            if (friend.getToId() == userId && !friend.isAccepted()) {
                return true;
            }
        }
        return false;
    }
}
