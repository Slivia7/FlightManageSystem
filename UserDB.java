package code;

import java.util.Iterator;
import java.util.Vector;
//µ¥ÀýÄ£Ê½
public class UserDB {
	private Vector<User> users ;
	UserDB(){
		users=new Vector<User>();
	}
	public Iterator getIterator() {
		Iterator it=users.iterator();
		return it;
	}
	public void addUser(User newUser) {
		users.add(newUser);
	}
	public User getUser(String name) {
		Iterator it=getIterator();
		User user;
		while(it.hasNext()) {
			user=(User)it.next();
			if(user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	public int getTotalNumber() {
		return users.size();	
	}
	
}
