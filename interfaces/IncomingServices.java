package interfaces;

import controller.ProfileController;
import model.Incoming;

import java.util.ArrayList;

public interface IncomingServices {

    void addIncoming(Incoming incoming, ProfileController profile) throws Exception;

    ArrayList<Incoming> getIncomingHistory(String userName) throws Exception;

    double getTotalIncoming(String userId) throws Exception;

    void deleteIncomingRecord(int incomingId) throws Exception;
}
