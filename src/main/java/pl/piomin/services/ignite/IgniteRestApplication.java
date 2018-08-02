package pl.piomin.services.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.piomin.services.ignite.model.Person;

import java.util.Arrays;

@SpringBootApplication
@EnableIgniteRepositories
public class IgniteRestApplication {


	public static void main(String[] args) {
		SpringApplication.run(IgniteRestApplication.class, args);
	}

	@Bean
	public Ignite igniteInstance() throws IgniteCheckedException {
		IgniteConfiguration cfg = new IgniteConfiguration();
//		cfg.setClientMode(true);
		cfg.setIgniteInstanceName("ignite-cluster-node");

		// Enabling peer-class loading feature.
//		cfg.setPeerClassLoadingEnabled(true);

		CacheConfiguration<Long, Person> ccfg3 = new CacheConfiguration<Long, Person>("PersonCache");
		ccfg3.setIndexedTypes(Long.class, Person.class);
		cfg.setCacheConfiguration(ccfg3);

		TcpDiscoverySpi spi = new TcpDiscoverySpi();

		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();

		ipFinder.setAddresses(Arrays.asList("127.0.0.1", "192.168.0.113"));


//		DataStorageConfiguration storageCfg = new DataStorageConfiguration();
//
//		storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
//
//		cfg.setDataStorageConfiguration(storageCfg);

		spi.setIpFinder(ipFinder);


		// Override default discovery SPI.
		cfg.setDiscoverySpi(spi);

		IgniteLogger log = new Slf4jLogger();
		cfg.setGridLogger(log);
		return Ignition.start(cfg);
	}
	


}
