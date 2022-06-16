package es.genix.admin.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory


@Configuration
class RabbitQueueConfiguration : RabbitListenerConfigurer {

    override fun configureRabbitListeners(registrar: RabbitListenerEndpointRegistrar) {
        registrar.messageHandlerMethodFactory = myHandlerMethodFactory()
    }

    @Bean
    fun rabbitListenerContainerFactory(connectionFactory: ConnectionFactory?): SimpleRabbitListenerContainerFactory? {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setMessageConverter(producerJackson2MessageConverter())
        factory.setReceiveTimeout(10000)
        factory.setPrefetchCount(10)
        factory.setConcurrentConsumers(1)
        factory.setMaxConcurrentConsumers(1)
        factory.setConsecutiveActiveTrigger(1)
        factory.setConsecutiveIdleTrigger(1)
        return factory
    }
    @Bean
    fun myHandlerMethodFactory(): DefaultMessageHandlerMethodFactory {
        val factory = DefaultMessageHandlerMethodFactory()
        factory.setMessageConverter(MappingJackson2MessageConverter())
        return factory
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate? {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = producerJackson2MessageConverter()!!
        return rabbitTemplate
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter? {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter? {
        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        mapper.registerModule(JavaTimeModule())
        return MappingJackson2HttpMessageConverter(mapper)
    }

    @Bean
    fun addListQueue() = Queue("add.list", true, false, false)

    @Bean
    fun addSongQueue() = Queue("add.song", true, false, false)

    @Bean
    fun playSongQueue() = Queue("play.song", true, false, false)

    @Bean
    fun stopSongQueue() = Queue("stop.song", true, false, false)

    @Bean
    fun pauseSongQueue() = Queue("pause.song", true, false, false)

    @Bean
    fun resumeSongQueue() = Queue("resume.song", true, false, false)

    @Bean
    fun skipSongQueue() = Queue("skip.song", true, false, false)

    @Bean
    fun botGuildsQueue() = Queue("bot.guilds", true, false, false)

    @Bean
    fun botGuildsDeleteQueue() = Queue("bot.guilds.delete", true, false, false)

    @Bean
    fun currentSongQueue() = Queue("current.song", true, false, false)

    @Bean
    fun queueQueue() = Queue("queue", true, false, false)

}
