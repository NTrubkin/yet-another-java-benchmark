package ru.nntu.yajb.service.postman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.DataPackage;

public class LoggerPostman implements Postman {
	private static final Logger LOG = LoggerFactory.getLogger(LoggerPostman.class);

	@Override
	public void send(DataPackage dataPackage) {
		LOG.info(dataPackage.toString());
	}
}
