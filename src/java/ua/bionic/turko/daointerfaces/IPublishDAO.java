package ua.bionic.turko.daointerfaces;

import java.util.Map;

public interface IPublishDAO {
	
	public String getName(String id);
	public String getAuthId(String id);
        public String getGenreId(String id);
	public String getPrice(String id);
        public String getImagePath(String id);
	public String getDescription(String id);
        public int getPublicationCount();
        public void deletePublication(String id);
        public void addPublication(String name,String price,String description,String image);
        public void updatePublication(String id,String name,String price,String description,String image);
        public Map<String, String> getSubscriptions();

}
