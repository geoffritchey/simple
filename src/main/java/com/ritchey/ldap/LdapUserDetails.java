/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ritchey.ldap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;





/**
 * A UserDetails implementation which is used internally by the Ldap services. It also contains the user's
 * distinguished name and a set of attributes that have been retrieved from the Ldap server.
 * <p>
 * An instance may be created as the result of a search, or when user information is retrieved during authentication.
 * </p>
 * <p>
 * An instance of this class will be used by the <tt>LdapAuthenticationProvider</tt> to construct the final user details
 * object that it returns.
 * </p>
 *
 * @author Luke Taylor
 * @version $Id: LdapUserDetails.java 2379 2007-12-14 20:41:00Z luke_t $
 */
public class LdapUserDetails implements UserDetails {

    //~ Instance fields ================================================================================================

    private Attributes attributes = new BasicAttributes();
    private String dn;
    private String password;
    private String username;
    private List<GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private String employeeId = null;
    private String job = null;
    private String mail = null;
    private String givenName = null;
    private String surname = null;

    //~ Constructors ===================================================================================================

    public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String peopleId) {
		this.employeeId = peopleId;
	}

	public LdapUserDetails() {}

    //~ Methods ========================================================================================================

    public Attributes getAttributes() {
        return attributes;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getDn() {
        return dn;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        sb.append("Employee Id: ").append(this.employeeId).append("; ");
        sb.append("Mail: ").append(this.mail).append("; ");
        sb.append("Job: ").append(this.job).append("; ");

        if (this.getAuthorities() != null) {
            sb.append("Granted Authorities: ");
            boolean first = true;
            for (GrantedAuthority authority: this.getAuthorities()) {
                if (!first) {
                    sb.append(", ");
                }
                first=false;
                sb.append(authority.toString());
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();        
    }

    //~ Inner Classes ==================================================================================================

    /**
     * Variation of essence pattern. Used to create mutable intermediate object
     */
    public static class Essence {
        protected LdapUserDetails instance = createTarget();
        private List<GrantedAuthority> mutableAuthorities = new ArrayList<GrantedAuthority>();

        public Essence() { }

        public Essence(DirContextOperations ctx) {
            setDn(ctx.getDn());
        }

        public Essence(LdapUserDetails copyMe) {
            setDn(copyMe.getDn());
            setAttributes(copyMe.getAttributes());
            setUsername(copyMe.getUsername());
            setPassword(copyMe.getPassword());
            setEnabled(copyMe.isEnabled());
            setAccountNonExpired(copyMe.isAccountNonExpired());
            setCredentialsNonExpired(copyMe.isCredentialsNonExpired());
            setAccountNonLocked(copyMe.isAccountNonLocked());
            setAuthorities(copyMe.getAuthorities());
            setEmployeeId(copyMe.getEmployeeId());
            setMail(copyMe.getMail());
            setJob(copyMe.getJob());
            setGivenName(copyMe.getGivenName());
        }

        protected LdapUserDetails createTarget() {
            return new LdapUserDetails();
        }

        /** Adds the authority to the list, unless it is already there, in which case it is ignored */
        public void addAuthority(GrantedAuthority a) {
        	System.err.println("add Authority");
            if(!hasAuthority(a)) {
            	System.err.println("Add a ");
                mutableAuthorities.add(a);
                System.err.println("Added a ");
                System.err.println("Added a = " + a);
            }
        }

        private boolean hasAuthority(GrantedAuthority a) {
        	System.err.println("Has Authority");
            try {
				for (GrantedAuthority authority:mutableAuthorities) {
				    System.err.println("authority = " + authority);
				    if(authority != null && authority.equals(a)) {
				    	System.err.println("Has Authority return TRUE");
				        return true;
				    }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.err.println("return false");
            return false;
        }

        public LdapUserDetails createUserDetails() {
//            Assert.notNull(instance, "Essence can only be used to create a single instance");
//            Assert.notNull(instance.username, "username must not be null");
//            Assert.notNull(instance.getDn(), "Distinguished name must not be null");

            instance.authorities = getGrantedAuthorities();
            System.err.println("get auth");

            LdapUserDetails newInstance = instance;
            System.err.println("got inst");
            instance = null;
            System.err.println("set null");
            return newInstance;
        }

        public List<GrantedAuthority> getGrantedAuthorities() {
            return mutableAuthorities;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            instance.accountNonExpired = accountNonExpired;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            instance.accountNonLocked = accountNonLocked;
        }

        public void setAttributes(Attributes attributes) {
            instance.attributes = attributes;
        }

        public void setAuthorities(Collection<? extends GrantedAuthority> collection) {
        	mutableAuthorities = new ArrayList();
        	for (GrantedAuthority g: collection) {
        		mutableAuthorities.add(g);
        	}
            
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            instance.credentialsNonExpired = credentialsNonExpired;
        }

        public void setDn(String dn) {
            instance.dn = dn;
        }

        public void setDn(Name dn) {
            instance.dn = dn.toString();
        }

        public void setEnabled(boolean enabled) {
            instance.enabled = enabled;
        }

        public void setPassword(String password) {
            instance.password = password;
        }

        public void setUsername(String username) {
            instance.username = username;
        }
        
        public void setEmployeeId(String employeeId) {
        	instance.employeeId = employeeId;
        }
        
        public void setMail(String mail) {
        	instance.mail = mail;
        }
        
        public void setGivenName(String givenName) {
        	instance.givenName = givenName;
        }
        
        public void setSurname(String surname) {
        	instance.surname = surname;
        }
        
        public String getSurname() {
        	return instance.surname;
        }
        
        public void setJob(String job) {
        	instance.job = job;
        }
    }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Copy to a type that can be displayed in the gwt client
	 * @param s
	 *
	public SimpleLoginType copy(SimpleLoginType s) {
		s.setEmail(mail);
		s.setName(givenName + " " + surname);
		//s.setName(givenName);
		s.setPeopleId(employeeId);
		HashSet<String> roles = new HashSet<String>();
		for (GrantedAuthority x:authorities) {
			roles.add(x.getAuthority());
		}
		s.setRoles(roles);
		return s;
	}
	*/
}
