package codestrive.annotation.applogic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AccountFactory {
	
	public String getRate(String accountType){
		/*
		 * Account Factory class: It will initiate the require subclass as
		 * per user input from the Client class
		 * 
		 * In future if some new account type is introduced we just have 
		 * to create a subclass and put the logic to define the class here
		 * Client and Main Business logic need not to change anything.
		 * This is the beauty of Factory Pattern.
		 */
		System.out.println("Account Factory Called");
		Account userAccount = null;
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring-core-annotation.xml");
		//ApplicationContext factory = new ClassPathXmlApplicationContext("spring-core-schema.xml");
		//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring-core-bean.xml"));
		
		if("S".equals(accountType))
			userAccount =  (Account) factory.getBean("savingAccount");
		if("F".equals(accountType))
			userAccount = factory.getBean("fixedAccount",Account.class);
		if("P".equals(accountType))
			userAccount = factory.getBean("ppfAccount",Account.class);
		
		String interestrate  = userAccount.getInterestRate();
		return interestrate;
	}
}
