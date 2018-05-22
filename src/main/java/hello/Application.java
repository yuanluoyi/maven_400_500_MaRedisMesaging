package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static PatternTopic patternTopic=new PatternTopic("chat");

/*    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }
    */
/*  @Bean
   CountDownLatch latch() {
    return new CountDownLatch(1);
}*/
  @Bean
  CountDownLatch latch(){
      return new CountDownLatch(1);
  }
  @Bean
  Receiver receiver(CountDownLatch latch){
      return new Receiver(latch);
  }
   @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
       return new MessageListenerAdapter(receiver,"receiveMessage");
   }
    /*    @Bean
        RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                MessageListenerAdapter listenerAdapter) {
            RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
            return container;
        }*/
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer container=new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter,patternTopic);
        return container;
    }
/*    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }*/
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }
    public static void main(String[] args) throws InterruptedException {
/*        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);
        LOGGER.info("Sending message...");
        template.convertAndSend("chat", "您好, Redis!");
        latch.await();
        System.exit(0);*/
        ApplicationContext applicationContext= SpringApplication.run(Application.class,args);
        StringRedisTemplate stringRedisTemplate=applicationContext.getBean(StringRedisTemplate.class);
        CountDownLatch countDownLatch=applicationContext.getBean(CountDownLatch.class);
        //LOGGER.info("Sending message....");
        System.out.println("Sending message....");
        stringRedisTemplate.convertAndSend(patternTopic.getTopic(),"您好, Redis!");
        countDownLatch.await();

    }
}

