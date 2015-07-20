package cn.kawa.studio.cache.memcache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XmemTest {

	public static void main(String[] args) throws TimeoutException, InterruptedException, MemcachedException {
		// TODO Auto-generated method stub
	        MemcachedClientBuilder builder = new XMemcachedClientBuilder(  
	                AddrUtil.getAddresses("192.168.2.115:11111"));  

	               builder.setFailureMode(true);    
 
	               builder.setCommandFactory(new BinaryCommandFactory());  
	               
	                builder.setConnectionPoolSize(10);    
	        MemcachedClient client;
			try {
				client = builder.build();
				client.set("hello", 0, "Hello,xmemcached");  
	            String value = client.get("hello");  
	            System.out.println("hello=" + value);  
	            client.delete("hello");  
	            value = client.get("hello");  
	            System.out.println("hello=" + value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	         
	 
	             

	}

}
