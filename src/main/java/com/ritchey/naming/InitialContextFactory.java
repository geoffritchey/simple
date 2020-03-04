package com.ritchey.naming;
// ========================================================================
// $Id: InitialContextFactory.java 1327 2006-11-27 18:40:14Z janb $
// Copyright 1999-2006 Mort Bay Consulting Pty. Ltd.
// ------------------------------------------------------------------------
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at 
// http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ========================================================================

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.naming.NamingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*------------------------------------------------*/    
/**
 * InitialContextFactory.java
 *
 * Factory for the default InitialContext.
 * Created: Tue Jul  1 19:08:08 2003
 *
 * @author <a href="mailto:janb@mortbay.com">Jan Bartel</a>
 * @version 1.0
 * 
 * 
 * Example build.properties snippet
 * 
database.jndi.names=forms,powercampus

database.powercampus.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
database.powercampus.user=sa
database.powercampus.password=mySaPassword
database.powercampus.url=jdbc:sqlserver://kindness;ServerName=kindness.lcunet.lcu.edu\\powercampus;databaseName=Area51

database.forms.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
database.forms.user=sa
database.forms.password=mySaPassword
database.forms.url=jdbc:sqlserver://kindness;ServerName=kindness.lcunet.lcu.edu\\powercampus;databaseName=forms_TEST
 */
public class InitialContextFactory implements javax.naming.spi.InitialContextFactory
{
	private static final Logger LOGGER = LoggerFactory.getLogger(InitialContextFactory.class);
    /*------------------------------------------------*/    
    /**
     * Get Context that has access to default Namespace.
     * This method won't be called if a name URL beginning
     * with java: is passed to an InitialContext.
     *
     * @see org.mortbay.naming.java.javaURLContextFactory
     * @param env a <code>Hashtable</code> value
     * @return a <code>Context</code> value
     */
    public Context getInitialContext(Hashtable env)
    {
    	
        Context root = new NamingContext(env, "myContext");

        Context comp = null;
        Context ctx = null;
		try {
			comp = root.createSubcontext("java:comp");
			ctx = comp.createSubcontext("env");
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
        
        try {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("build.properties"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            Context jdbc = null;
            try {
                jdbc = ctx.createSubcontext("jdbc");
            } catch (javax.naming.NameAlreadyBoundException e) {
                jdbc = (Context) ctx.lookup("jdbc");
            }
            Context ldap = null;
            try {
                ldap = ctx.createSubcontext("ldap");
            } catch (javax.naming.NameAlreadyBoundException e) {
                ldap = (Context) ctx.lookup("ldap");
            }
            
            
            createLdapStrings(properties, ldap);
            

            String databaseNames = properties.getProperty("database.jndi.names");
            if (databaseNames == null) {
                LOGGER.warn("database.jndi.names is not defined in build.properties as a comma separated list in build.properties", new RuntimeException());
                return ctx;
            }

            for (String database: databaseNames.split(" *, *")) {
                createDs(database, properties, jdbc);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            NamingEnumeration<Binding> x =  root.listBindings("java:comp/env/jdbc");
            LOGGER.debug("list binding jdbc");
            while ( x.hasMore()) {
                Binding binding = x.next();
                LOGGER.debug("binding: " + binding.getName());
            }
            LOGGER.debug("DONE list binding jdbc");
            LOGGER.debug("GEOFF ldap initial context");
            NamingEnumeration<Binding> ldapBindings = root.listBindings("java:comp/env/ldap");
			while (ldapBindings.hasMore()) {
                Binding binding = ldapBindings.next();
                LOGGER.debug("binding: " + binding.getName());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
        return root;
    }
    
    public void createLdapStrings(Properties properties, Context ldap) throws NamingException {
    	Boolean isDatabase = false;
    	
    	try {
			ldap.bind("url", getValue(isDatabase,"url", "ldap", properties));
			ldap.bind("bindUserDistinguishedName", getValue(isDatabase,"bindUserDistinguishedName", "ldap", properties));
			ldap.bind("bindUserPassword", getValue(isDatabase,"bindUserPassword", "ldap", properties));
			/*
			ldap.bind("usersSearchRoot", getValue(isDatabase,"usersSearchRoot", "ldap", properties));
			ldap.bind("groupsSearchRoot", getValue(isDatabase,"groupsSearchRoot", "ldap", properties));
			ldap.bind("groupsSearchRootSecondary", getValue(isDatabase,"groupsSearchRootSecondary", "ldap", properties));
			*/
		} catch (NameAlreadyBoundException e) {
			// Quietly suppress NameAlreadyBound Exception
		}

    }

    /**
     * Create a databaseSource for a database by using properties that are formed with the jndi name 
     *         (e.g. database.myCoolDatabaseNumber1.url).  url, user, password and driver should all be defined.
     * @param database is the jndi name of the database
     * @param properties represents the values of build.properties
     * @param jdbc is the context we're going to load
     * @throws NamingException
     */
    public void createDs(String database, Properties properties, Context jdbc) throws NamingException {
        org.apache.commons.dbcp.BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
        try {
            jdbc.bind(database, ds);
        } catch (Exception e) {
            // Quietly suppress NameAlreadyBound Exception
        }

        Boolean isDatabase = true;
        ds.setDriverClassName(getValue(isDatabase,"driver", database, properties));
        ds.setUrl(getValue(isDatabase, "url", database, properties));
        ds.setUsername(getValue(isDatabase, "user", database, properties));
        ds.setPassword(getValue(isDatabase, "password", database, properties));
    }

    /**
     * return the build.properties values for a base name
     * @param base database.{databaseName}.{base}={value} should be setup in build.properties
     * @param databaseName
     * @param properties represents build.properties settings
     * @return the value of the property from build.properties
     */
    String getValue(Boolean database, String propertyName, String sectionName, Properties properties) {
        String name = sectionName + "." + propertyName;
        if (database) {
        	name = "database." + name;
        }
        String value = (String) properties.getProperty(name);
        if (value == null) {
            LOGGER.warn(name + " is not defined in build.properties", new RuntimeException());
        }
        return value;
    }
}

