package com.izuche.datasyn;

import com.izuche.datasyn.abstracts.AbstarctSend;
import com.izuche.datasyn.inertfaces.DataSyn;
import com.izuche.datasyn.properties.DataSynProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(AbstarctSend.class)
@EnableConfigurationProperties(DataSynProperties.class)
public class DataSyncConfigure {

    @Autowired
    private DataSynProperties dataSynProperties;

    @Bean
    @ConditionalOnProperty(prefix = "spring.data.sync",name = {"send"})
    public SendDataSyn sendDataSyn(){
        return new SendDataSyn(dataSynProperties);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.data.sync",name = {"type"},havingValue = "map")
    public MapReceiveDataSyn mapReceiveDataSyn(){
        return new MapReceiveDataSyn(dataSynProperties);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.data.sync",name = {"type"},havingValue = "send")
    public SendReceiveDataSyn sendReceiveDataSyn(){
        return new SendReceiveDataSyn(dataSynProperties);
    }


}
