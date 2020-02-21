package  com.ritchey.ldap;


import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.util.Assert;

public class MyUserDetailsContextMapper implements UserDetailsContextMapper {
	private static Log log = LogFactory.getLog(MyUserDetailsContextMapper.class);
	private String passwordAttributeName = "userPassword";
	private String rolePrefix = "ROLE_";
	private String[] roleAttributes = null;
	private String[] userAttributes = null;
	private boolean convertToUpperCase = true;
	private String id = null;
	private String givenNameAttribute = null;
	private String surnameAttribute = null;

	public void setSurnameAttribute(String surname) {
		this.surnameAttribute = surname;
	}


	public void setGivenNameAttribute(String givenName) {
		this.givenNameAttribute = givenName;
	}

	private String mailAttribute = null;
	private String role = null;
//	private PeopleManager peopleMgr = null;
//	private JobManager jobMgr = null;
//	private UserLogManager userMgr = null;
//	    
//	public void setPeopleManager (PeopleManager peopleMgr) {
//		this.peopleMgr = peopleMgr;
//	}
//	
//	public void setJobManager (JobManager job) {
//		this.jobMgr = job;
//	}
//	
//	public void setUserLogManager(UserLogManager userMgr) {
//		this.userMgr = userMgr;
//	}


//	@Override
//	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
//		
//	}
	
	/**
     * Extension point to allow customized creation of the user's password from
     * the attribute stored in the directory.
     *
     * @param passwordValue the value of the password attribute
     * @return a String representation of the password.
     */
    protected String mapPassword(Object passwordValue) {

        if (!(passwordValue instanceof String)) {
            // Assume it's binary
            passwordValue = new String((byte[]) passwordValue);
        }

        return (String) passwordValue;

    }
    
    /**
     * Creates a GrantedAuthority from a role attribute. Override to customize
     * authority object creation.
     * <p>
     * The default implementation converts string attributes to roles, making use of the <tt>rolePrefix</tt>
     * and <tt>convertToUpperCase</tt> properties. Non-String attributes are ignored.
     * </p>
     *
     * @param role the attribute returned from
     * @return the authority to be added to the list of authorities for the user, or null
     * if this attribute should be ignored.
     */
    protected GrantedAuthority createAuthority(Object role) {
        if (role instanceof String) {
            if (convertToUpperCase) {
                role = ((String) role).toUpperCase();
            }
            return new SimpleGrantedAuthority(rolePrefix + role);
        }
        return null;
    }
    

    /**
     * Determines whether role field values will be converted to upper case when loaded.
     * The default is true.
     *
     * @param convertToUpperCase true if the roles should be converted to upper case.
     */
    public void setConvertToUpperCase(boolean convertToUpperCase) {
        this.convertToUpperCase = convertToUpperCase;
    }

    /**
     * The name of the attribute which contains the user's password.
     * Defaults to "userPassword".
     *
     * @param passwordAttributeName the name of the attribute
     */
    public void setPasswordAttributeName(String passwordAttributeName) {
        this.passwordAttributeName = passwordAttributeName;
    }

    /**
     * The names of any attributes in the user's  entry which represent application
     * roles. These will be converted to <tt>GrantedAuthority</tt>s and added to the
     * list in the returned LdapUserDetails object. The attribute values must be Strings by default.
     *
     * @param roleAttributes the names of the role attributes.
     */
    public void setRoleAttributes(String[] roleAttributes) {
        Assert.notNull(roleAttributes, "roleAttributes array cannot be null");
        this.roleAttributes = roleAttributes;
    }

    /**
     * The prefix that should be applied to the role names
     * @param rolePrefix the prefix (defaults to "ROLE_").
     */
    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }
    
    public void setUserAttributes(String[] userAttributes) {
        log.debug("set User Attributes: ");
        for (String att: userAttributes) {
        	log.debug("att Name = " + att);
        }
        this.userAttributes = userAttributes;
    }
    
    public void setIdAttribute(String id) {
    	this.id = id;
    }
    
    public void setIdAttributeRole(String role) {
    	this.role = role;
    }

	public String getMailAttribute() {
		return mailAttribute;
	}

	public void setMailAttribute(String mailAttribute) {
		this.mailAttribute = mailAttribute;
	}

	//@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx,
			String username, Collection<? extends GrantedAuthority> authorities) {
        String dn = ctx.getNameInNamespace();

        log.debug("Mapping user details from context with DN: " + dn);

        LdapUserDetails.Essence essence = new LdapUserDetails.Essence();
        essence.setDn(dn);

        Object passwordValue = ctx.getObjectAttribute(passwordAttributeName);

        if (passwordValue != null) {
            essence.setPassword(mapPassword(passwordValue));
        }

        essence.setUsername(username);
        essence.setAuthorities(authorities);

        String employeeId = null;
        String email = null;
        String givenName = null;
        String surname = null;
        //Attributes all = new BasicAttributes(true); 
        for (String atts: userAttributes) {
        	String[] values = ctx.getStringAttributes(atts);
        	for (int i = 0; i < values.length; i++) {
        		log.debug(atts + " [" + i + "]: " + values[i]);
        		if (atts != null && atts.equals(id)) {
        			employeeId = values[0];
        			log.debug("Set employeeID to " + employeeId);
        			essence.setEmployeeId(employeeId);
        			Thread.dumpStack();
        		}
        		if (atts != null && atts.equals(mailAttribute)) {
        			email = values[0];
        			log.debug("Set email to " + email);
        			essence.setMail(email);
        			log.debug("done email");
        		}
        		if (atts != null && atts.equals(givenNameAttribute)) {
        			givenName = values[0];
        			log.debug("Set givenName to " + givenNameAttribute);
        			essence.setGivenName(givenName);
        			log.debug("done givenName");
        		}
        		if (atts != null && atts.equals(surnameAttribute)) {
        			surname = values[0];
        			log.debug("Set surname to " + surname);
        			essence.setSurname(surname);
        			log.debug("done surname");
        		}
        	}
        }
        System.err.println("ADD anon and active");
		//everyone who authenticates is ANONYMOUS and ACTIVEDIR
//		essence.addAuthority(createAuthority("ANONYMOUS"));
//		essence.addAuthority(createAuthority("ACTIVEDIR"));
		/* for testing */
		//essence.addAuthority(createAuthority("DEPT - BOOKSTORE"));
		//all.put(atts, values[i]);
        
        //essence.setAttributes(all);
        System.err.println("Create user details");
        UserDetails user = essence.createUserDetails();
        System.err.println("back");
        /*
        if (id != null) {
        	String[] idNumbers = ctx.getStringAttributes(id);
        	if (idNumbers.length > 0) {
        		Assert.notNull(role, "idAttributeRole can't be null if idAttribute set");
        		log.debug("? add AUthority: " + role);
        		if (peopleMgr.isHoursManager(employeeId)) { 
        			log.debug("! 1 add AUthority: " + role);
        			essence.addAuthority(createAuthority(role));
        		}
        	}
        }
        */
        // Add the supplied authorities

        for(GrantedAuthority authority: authorities) {
        	log.debug("! 2 add AUthority: " + authority);
            essence.addAuthority(authority);
        }
        System.err.println("return");
        return user;
	}


	/* (non-Javadoc)
	 * @see org.springframework.security.ldap.userdetails.UserDetailsContextMapper#mapUserToContext(org.springframework.security.core.userdetails.UserDetails, org.springframework.ldap.core.DirContextAdapter)
	 */
	//@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// TODO Auto-generated method stub
		
	}


}
