package ua.bionic.turko.dao;

public class DAOFactory {

        private static PublishDAO publDAO = null;
        private static UserDAO userDAO = null;
        private static GenreDAO genreDAO = null;
        private static AuthorDAO authorDAO = null;
        private static SubscriptionDAO subscriptionDAO = null;
        
	private static void createUser() {    	
		userDAO = new UserDAO();
	}
        
        private void createPublish() {    	
		publDAO = new PublishDAO();
	}
        
        private static void createGenreDAO() {    	
		genreDAO = new GenreDAO();
	}
        
        private void createAuthorDAO() {    	
		authorDAO = new AuthorDAO();
	}
        
        private void SubscriptionDAO() {    	
		subscriptionDAO = new SubscriptionDAO();
	}
        
        public static PublishDAO getPublishDAO() {    	
            if (publDAO == null){
                publDAO = new PublishDAO();
            }
            return publDAO;
	}
        
        public static UserDAO getUserDAO() {    	
            if (userDAO == null){
                userDAO = new UserDAO();
            }
            return userDAO;
	}
	
        public static GenreDAO getGenreDAO() {    	
            if (genreDAO == null){
                genreDAO = new GenreDAO();
            }
            return genreDAO;
	}
        
        public static AuthorDAO getAuthorDAO() {    	
            if (authorDAO == null){
                authorDAO = new AuthorDAO();
            }
            return authorDAO;
	}
        
        public static SubscriptionDAO getSubscriptionDAO() {    	
            if (subscriptionDAO == null){
                subscriptionDAO = new SubscriptionDAO();
            }
            return subscriptionDAO;
	}
        
        
    public int hashCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 31);
    }
	
	
    public String toString() {
	return getClass().getName();
    }
	

    public boolean equals(Object obj) {
         if (this == obj) return true;
		            
         if(obj == null) return false;

         if(!(obj instanceof DAOFactory)) return false;
         
          DAOFactory obj1 = (DAOFactory) obj;
         
          return publDAO == obj1.publDAO
                && (genreDAO == obj1.genreDAO)
                && (authorDAO == obj1.authorDAO)
                && (subscriptionDAO == obj1.subscriptionDAO);
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }          
}
