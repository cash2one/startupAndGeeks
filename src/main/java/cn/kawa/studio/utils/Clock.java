package cn.kawa.studio.utils;


import java.util.Date;
/**
 * �����ṩ�ߣ�ʹ����������ֱ��ȡ��ϵͳʱ�䣬������ԡ�
 * 
 * @author
 */
public interface Clock {

	static final Clock DEFAULT = new DefaultClock();

	Date getCurrentDate();

	long getCurrentTimeInMillis();

	/**
	 * Ĭ��ʱ���ṩ�ߣ����ص�ǰ��ʱ�䣬�̰߳�ȫ��
	 */
	public static class DefaultClock implements Clock {

		@Override
		public Date getCurrentDate() {
			return new Date();
		}

		@Override
		public long getCurrentTimeInMillis() {
			return System.currentTimeMillis();
		}
	}

	/**
	 * �����õ�ʱ���ṩ�ߣ����ڲ���.
	 */
	public static class MockClock implements Clock {

		private long time;

		public MockClock() {
			this(0);
		}

		public MockClock(Date date) {
			this.time = date.getTime();
		}

		public MockClock(long time) {
			this.time = time;
		}

		@Override
		public Date getCurrentDate() {
			return new Date(time);
		}

		@Override
		public long getCurrentTimeInMillis() {
			return time;
		}

		/**
		 * �����������ڡ�
		 */
		public void update(Date newDate) {
			time = newDate.getTime();
		}

		/**
		 * ��������ʱ�䡣
		 */
		public void update(long newTime) {
			this.time = newTime;
		}

		/**
		 * ����ʱ��.
		 */
		public void increaseTime(int millis) {
			time += millis;
		}

		/**
		 * ����ʱ��.
		 */
		public void decreaseTime(int millis) {
			time -= millis;
		}
	}

}
