package com.rabbitshop.mongodbweb.persistence.configs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.Transformer;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;

import com.rabbitshop.mongodbweb.constants.MongoDBwebConstants;
import com.rabbitshop.mongodbweb.persistence.utils.MongoDbUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MongoDbSpringBeansConfiguration {

	/**
	 * MongoDB database reference resolver to retrieve a DBObject from a DBRef field
	 *
	 * @param mongoDbFactory
	 *
	 * @return {@link org.springframework.data.mongodb.core.convert.DbRefResolver}
	 */
	@Bean(name = "dbRefResolver")
	@Resource
	public DbRefResolver createDbRefResolver(final MongoDbFactory mongoDbFactory) {

		log.debug("Creating DBRef Resolver...");

		return new DefaultDbRefResolver(mongoDbFactory);
	}
	
	/**
	 * Populate the MongoDB object encoding map with custom mapping
	 *
	 * @return Encoding map for MongoDB
	 */
	@Bean(name = "mongoDbEncodingHookMap")
	protected Map<Class<?>, Transformer> createAndLoadMongoDbEncodingHookMap() {
		
		log.debug("Creating MongoDB ENCODING hooks map...");

		final Map<Class<?>, Transformer> encMap = new HashMap<>();
		// Add hook to encode Joda DateTime as a java.util.Date
		log.debug("Encoding hook: Joda DateTime >>> java.util.Date");
		encMap.put(
				DateTime.class,
				o -> new Date(((DateTime) o).getMillis()));
		
		log.debug("Loading MongoDB BSON ENCODING hooks...");
		MongoDbUtils.addEncodingHooks(encMap);
		
		return encMap;
	}
	
	/**
	 * Populate the MongoDB object decoding map with custom mapping
	 *
	 * @return Decoding map for MongoDB
	 */
	@Bean(name = "mongoDbDecodingHookMap")
	protected Map<Class<?>, Transformer> createAndLoadMongoDbDecodingHookMap() {
		
		log.debug("Creating MongoDB DECODING hooks map...");

		final Map<Class<?>, Transformer> decMap = new HashMap<>();
		// Add hook to decode java.util.Date as a Joda DateTime
		log.debug("Decoding hook: java.util.Date >>> Joda DateTime");
		decMap.put(
				Date.class,
				o -> new DateTime(((Date) o).getTime()));
		
		log.debug("Loading MongoDB BSON DECODING hooks...");
		MongoDbUtils.addDecodingHooks(decMap);

		return decMap;
	}
	
	/**
	 * The method customConversions in AbstractMongoConfiguration (or its extensions) can be used to configure custom Converters.
	 *
	 * @param customConverters
	 *
	 * @return {@link org.springframework.data.mongodb.core.convert.CustomConversions}
	 */
	@Bean(name = "mongoDbCustomConversionsList")
	@Resource
	protected CustomConversions createAndLoadCustomConverters(final List<Converter<?, ?>> customConverters) {
		
		log.debug("Creating and loading MongoDB custom converters...");

		if (customConverters == null || customConverters.isEmpty()) {
			log.info("No custom converters found");
		} else {
			log.debug("Customer converters found: " + customConverters.size());
			customConverters.forEach(
					cv -> log.debug(MongoDBwebConstants.LOG_INDENT + cv.getClass()));
		}

		return new CustomConversions(customConverters);
	}

}
