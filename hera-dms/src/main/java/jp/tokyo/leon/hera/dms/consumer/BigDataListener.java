package jp.tokyo.leon.hera.dms.consumer;

import jp.tokyo.leon.hera.dms.entity.SqlEntity;
import jp.tokyo.leon.hera.dms.processor.SqlProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author longtao.guan
 */
@Component
public class BigDataListener {
    private static final Logger log = LoggerFactory.getLogger(BigDataListener.class);
    private final SqlProcessor sqlProcessor;

    @Autowired
    public BigDataListener(SqlProcessor sqlProcessor) {
        this.sqlProcessor = sqlProcessor;
    }

    /**
     * 监听kafka数据
     * @param consumerRecord canal原始数据
     */
    @KafkaListener(topics = {"canal"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        log.info("current kafka message's offset is {}", consumerRecord.offset());
        System.out.println(consumerRecord);
        SqlEntity<Object> objectSqlEntity = sqlProcessor.parseSql(consumerRecord.value().toString());
        sqlProcessor.executeSql(objectSqlEntity);
    }
}
