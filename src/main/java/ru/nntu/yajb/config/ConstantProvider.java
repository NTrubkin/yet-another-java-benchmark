package ru.nntu.yajb.config;

public class ConstantProvider {
	public static final String PROPERTIES_FILE = "application.properties";

	// property field names
	public static final String SEND_CONTROL_PROP = "yajb.SendControlService.class";
	public static final String POSTMAN_PROP = "yajb.Postman.class";

	// property default values
	public static final String SEND_CONTROL_VALUE = "ru.nntu.yajb.service.send.control.AlwaysAllowSendControlService";
	public static final String POSTMAN_VALUE = "ru.nntu.yajb.service.postman.LoggerPostman";

	public static final String JVM_PARAMS_DELIMITER = " ";

	private ConstantProvider() {
	}
}
