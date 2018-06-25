package com.zcmzjp.wx.filter;

import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class Cache {
    
    private static final Logger logger = Logger.getLogger(Cache.class);

    private List<String> locations = new ArrayList<>();

    private List<String> datacache = new ArrayList<>();

    private static final Map<String, String> cacheProps = new HashMap<String, String>();

    private static final Map<String, String> dataProps = new HashMap<String, String>();

    public void init() throws Exception{
        datacache.add("properties/message.properties");
        try {
            for(String url : locations){
                Properties props = PropertiesLoaderUtils.loadAllProperties(url);
                for(Entry<Object, Object> entry : props.entrySet()){
                    cacheProps.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
            for(String url : datacache){
                Properties props = PropertiesLoaderUtils.loadAllProperties(url);
                for(Entry<Object, Object> entry : props.entrySet()){
                    dataProps.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        } catch (Exception e) {
            logger.error("error read properties in Cache init, please check property files");
            throw e;
        }
        for(Entry<String, String> e : cacheProps.entrySet()){
            logger.debug("get cache property["+e.getKey()+"="+e.getValue()+"]");
        }
        for(Entry<String, String> e : dataProps.entrySet()){
            logger.debug("get cache property["+e.getKey()+"="+e.getValue()+"]");
        }
    }
    
    public static String getProperty(Object key){
        return cacheProps.get(key.toString());
    }

    public static Map<String, String> getDataProps(){
        return dataProps;
    }

    public static void main(String[] args) throws Exception {
        Cache c = new Cache();
        c.locations.add("properties/message.properties");
        c.init();
        for(Entry<String, String> e : cacheProps.entrySet()){
            System.out.println(e.getKey()+"="+e.getValue());
        }
    }

    
    /**
     * Getter method for property <tt>locations</tt>.
     *
     * @return property value of locations.
     */
    public List<String> getLocations() {
        return locations;
    }

    
    /**
     * Setter method for property <tt>locations</tt>.
     *
     * @param locations value to be assigned to property locations.
     */
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getDatacache() {
        return datacache;
    }

    public void setDatacache(List<String> datacache) {
        this.datacache = datacache;
    }
}

