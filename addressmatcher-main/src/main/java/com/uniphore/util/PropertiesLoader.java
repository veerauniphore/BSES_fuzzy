package com.uniphore.util;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

/**
 * PropertiesLoader is a class which is used to load the properties whenever it is required.
 * It is a singleton class which is used to fetch the values from the configuration file.
 *
 */
public class PropertiesLoader {

	static Logger logger = LogManager.getLogger(PropertiesLoader.class);
	 
	
    private static PropertiesLoader propertiesLoader;
    private PropertiesConfiguration config;

    // Properties file name
    private static final String FILE_NAME = "application.properties";

    private PropertiesLoader() {
        try{
        	logger.info("sar");
            config = new PropertiesConfiguration(FILE_NAME);
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
        }catch(Exception e){
            config = null;
            e.printStackTrace();
        }
    }

    /**
     * Get singleton instance of ConfigurationLoader.
     *
     * Parameter In	: none
     * Parameter Out	: PropertiesLoader
     */
    public static PropertiesLoader getInstance() {
        if(propertiesLoader == null){
            propertiesLoader = new PropertiesLoader();
        }
        return propertiesLoader;
    }

    /**
     * Get property value by property name.
     *
     * Parameter In	: String - property name
     * Parameter Out	: String - property value
     */
    public String getPropertyValue(String propertyName){
        if(config.getProperty(propertyName) != null)
            return (String) config.getProperty(propertyName).toString();
        else
            return null;
    }

    /**
     * Get property value in list by property name.
     *
     * Parameter In	: String - property name
     * Parameter Out	: List - property value
     */
	public List getPropertyValueList(String propertyName){
        if(config.getList(propertyName) != null)
            return (List) config.getList(propertyName);
        else
            return null;
    }
	
	 public void setPropertyValue(String propertyName,String value){
	        if(config.getProperty(propertyName) != null)
	        	config.setProperty(propertyName,value);
	       
	    }
	

}
