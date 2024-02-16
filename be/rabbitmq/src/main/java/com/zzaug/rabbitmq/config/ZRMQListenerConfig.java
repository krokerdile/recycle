package com.zzaug.rabbitmq.config;

import static com.zzaug.rabbitmq.config.ZRabbiMQConfig.BEAN_NAME_PREFIX;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Import({ZRMQConnectionConfig.class})
public class ZRMQListenerConfig {

	public static final String RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME =
			BEAN_NAME_PREFIX + "rabbitListenerContainerFactory";

	private final ConnectionFactory connectionFactory;
	private final MessageConverter messageConverter;

	@Bean(name = RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);
		factory.setDefaultRequeueRejected(false);
		factory.setChannelTransacted(true);
		return factory;
	}
}
