package com.ritchey.simple.Service;
/**
 * Provide a list of currently paged data (maybe 10 at a time) and a count of all data in database.
 * 
 * This is used for CellTable with a paging control.
 */
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ListCount<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20121126102357L;
	Map env;
	List<T> data = null;
	  int count = 0;
	  
	  public ListCount() {
		  
	  }
	  
	  public ListCount(List<T> data, int count) {
		  this.data = data;
		  this.count = count;
	  }
	  
	  public ListCount(List<T> data, int count, Map env) {
		  this.data = data;
		  this.count = count;
		  this.env = env;
	  }
	  
	  public List<T> getData() {
		  return data;
	  }
	  public int getCount() {
		  return count;
	  }
	  public Map getEnv() {
		  return env;
	  }
}
