package DataAn.storm.denoise;

import org.apache.storm.trident.spout.RichSpoutBatchExecutor;

import DataAn.storm.BaseConfig;
import DataAn.storm.StormUtils;
import DataAn.storm.kafka.KafkaNameKeys;
import DataAn.storm.zookeeper.ZooKeeperNameKeys;

public class DenoiseConfigParser  {

	public DenoiseConfig parse(String[] args) throws Exception{
		DenoiseConfig conf=StormUtils.getBaseConfig(DenoiseConfig.class);
		conf.put(BaseConfig.name, "denoise-task");
		conf.put("storm.flow.worker.id", 1);
		ZooKeeperNameKeys.setZooKeeperServer(conf, 
				conf.getZooKeeper());
		KafkaNameKeys.setKafkaServer(conf, conf.getKafka());
		conf.put(RichSpoutBatchExecutor.MAX_BATCH_SIZE_CONF, 1);
		conf.setMessageTimeoutSecs(10000);
		return conf;
	}
}
