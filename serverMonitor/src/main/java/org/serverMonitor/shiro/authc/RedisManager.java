package org.serverMonitor.shiro.authc;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisManager {

	private JedisPool jedisPool = null;
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}


	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}


	public RedisManager(){
	}
	
	
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(int dbIndex,byte[] key){
		byte[] value = null;
		Jedis jedis = jedisPool.getResource();
		try{
		 //     jedis.select(dbIndex);
			value = jedis.get(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(int dbIndex,byte[] key,byte[] value){
		Jedis jedis = jedisPool.getResource();
		try{
		 //    jedis.select(dbIndex);
			jedis.set(key,value);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(int dbIndex, byte[] key,byte[] value,int expire){
		Jedis jedis = jedisPool.getResource();
		try{
		//    jedis.select(dbIndex);
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * del
	 * @param key
	 */
	public void del(int dbIndex,byte[] key){
		Jedis jedis = jedisPool.getResource();
		try{
		//    jedis.select(dbIndex);
			jedis.del(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * flush
	 */
	public void flushDB(int dbIndex){
		Jedis jedis = jedisPool.getResource();
		try{
		//    jedis.select(dbIndex);
			jedis.flushDB();
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * size
	 */
	public Long dbSize(int dbIndex){
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try{
		//	jedis.select(dbIndex);
			dbSize = jedis.dbSize();
		}finally{
			jedisPool.returnResource(jedis);
		}
		return dbSize;
	}

	/**
	 * keys
	 * @param regex
	 * @return
	 */
	public Set<byte[]> keys(int dbIndex,String pattern){
		Set<byte[]> keys = null;
		Jedis jedis = jedisPool.getResource();
		try{
	//	jedis.select(dbIndex);
			keys = jedis.keys(pattern.getBytes());
		}finally{
			jedisPool.returnResource(jedis);
		}
		return keys;
	}
	

}
