package ua.bionic.turko.daointerfaces;

import ua.bionic.turko.dao.UserType;

public interface IUserDAO {
	
	public String getLogin(String id);
        public UserType getUserType(String id);
        public String getName(String id);
	public String getSurname(String id);
        public String getBalance(String id);
        public String getLastLogin(String id);
        public String getIdByLogin(String login);
        public void withdraw(String id, int summ);

}
