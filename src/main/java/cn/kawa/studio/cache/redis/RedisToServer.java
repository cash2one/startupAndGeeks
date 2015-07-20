package cn.kawa.studio.cache.redis;

public interface RedisToServer {
    public void KeyOperate();

    public void StringOperate();

    public void ListOperate();

    public void SetOperate();

    public void SortedSetOperate();
  
    public void HashOperate();

}
