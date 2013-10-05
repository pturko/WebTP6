package ua.bionic.turko.daointerfaces;

import java.util.Map;

public interface ISubscriptionDAO {
	
	public void addSubscription(String publId, String userId, String cost);
        public int getSubscriptionCount();
        public int getBuySumm(String userId);
        public Map<String, String> getSubscriptions(String userId);
        public Map<String, String> getHistory(String userId);
        public void setBuyFlag(String userId);
        public void cancelAllSubscription(String userId);
        public boolean findSomeSubscriptionforUser(String userId, String publId);
}
