package member.db;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {
	PasswordAuthentication passAuth;
    
    public GoogleAuthentication(){
        passAuth = new PasswordAuthentication("ex.obstinacy", "cbrwtouvlgwutopi");
    }
 
    public PasswordAuthentication getPasswordAuthentication() {
        return passAuth;
    }
}
