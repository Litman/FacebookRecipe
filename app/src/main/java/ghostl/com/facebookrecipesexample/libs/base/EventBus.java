package ghostl.com.facebookrecipesexample.libs.base;

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
