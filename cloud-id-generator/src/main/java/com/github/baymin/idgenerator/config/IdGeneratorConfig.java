package com.github.baymin.idgenerator.config;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author zongwei
 */
@Configuration
public class IdGeneratorConfig {

    @Bean
    @Scope("prototype")
    public WorkerIdAssigner disposableWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Bean
    public UidGenerator cachedUidGenerator() {
        CachedUidGenerator uidGenerator = new CachedUidGenerator();
        uidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
        uidGenerator.setTimeBits(29);
        uidGenerator.setWorkerBits(21);
        uidGenerator.setSeqBits(13);
        uidGenerator.setBoostPower(3);
        uidGenerator.setScheduleInterval(60L);
        return uidGenerator;
    }

}
