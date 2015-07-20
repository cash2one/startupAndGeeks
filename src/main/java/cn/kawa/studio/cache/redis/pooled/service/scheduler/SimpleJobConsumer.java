package cn.kawa.studio.cache.redis.pooled.service.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.kawa.studio.cache.redis.pooled.JedisTemplate;
import cn.kawa.studio.cache.redis.pooled.JedisTemplate.JedisAction;
import cn.kawa.studio.cache.redis.pooled.pool.JedisPool;
import cn.kawa.studio.utils.Threads;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/*
 * �򵥵Ļ���brpop()API, ������ȡ������
 * brpop�����������߳��ж�ʱ�����Զ��˳������Ի�����������timeoutʱ�䣬�������̳߳��˳�ʱ�ѱ�timeoutʱ�䳤��ʱ�����awaitTermination()�ȴ��߳̽���.
 */
public class SimpleJobConsumer {

	public static final int DEFAULT_POPUP_TIMEOUT_SECONDS = 5;
	public static final int DEFAULT_CONNECTION_RETRY_MILLS = 5000;

	private static Logger logger = LoggerFactory.getLogger(SimpleJobConsumer.class);

	private JedisTemplate jedisTemplate;
	private String readyJobKey;
	private int popupTimeoutSecs = DEFAULT_POPUP_TIMEOUT_SECONDS;

	public SimpleJobConsumer(String jobName, JedisPool jedisPool) {
		jedisTemplate = new JedisTemplate(jedisPool);
		readyJobKey = Keys.getReadyJobKey(jobName);
	}

	/**
	 * ����ֱ�������������popupTimeoutSecs��(Ĭ��5��)�����񵽴����null.
	 * �緢��redis�����쳣, �̻߳�sleep 5��󷵻�null��
	 */
	public String popupJob() {

		List<String> nameValuePair = null;
		try {
			nameValuePair = jedisTemplate.execute(new JedisAction<List<String>>() {
				@Override
				public List<String> action(Jedis jedis) {
					return jedis.brpop(popupTimeoutSecs, readyJobKey);
				}
			});
		} catch (JedisConnectionException e) {
			Threads.sleep(DEFAULT_CONNECTION_RETRY_MILLS);
		}

		if ((nameValuePair != null) && !nameValuePair.isEmpty()) {
			return nameValuePair.get(1);
		} else {
			return null;
		}
	}

	public void setPopupTimeoutSecs(int popupTimeoutSecs) {
		this.popupTimeoutSecs = popupTimeoutSecs;
	}
}
