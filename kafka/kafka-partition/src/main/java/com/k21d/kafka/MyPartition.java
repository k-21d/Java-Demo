package com.k21d.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyPartition implements Partitioner {

    //返回哪个分区
    @Override
    public int partition(String topic, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        System.out.println("topic:"+topic+",key="+key+",value="+o1);
        int size = partitionInfos.size();
        if (key == null){
            return new Random().nextInt(size);
        }
        return Math.abs((key.hashCode())%size);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

