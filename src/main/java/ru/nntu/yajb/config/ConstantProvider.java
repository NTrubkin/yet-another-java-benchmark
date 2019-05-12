package ru.nntu.yajb.config;

public class ConstantProvider {
	public static final String PROPERTIES_FILE = "application.properties";

	// property field names
	public static final String SEND_CONTROL_PROP = "yajb.SendControlService.class";
	public static final String POSTMAN_PROP = "yajb.Postman.class";
	public static final String COUNTER_SCS_COUNT_TO_SEND_PROP = "yajb.CounterSendControlService.count-to-send";
	public static final String HOST_PG_POSTMAN_PROP = "yajb.PostgresPostman.host";
	public static final String PORT_PG_POSTMAN_PROP = "yajb.PostgresPostman.port";
	public static final String DATABASE_PG_POSTMAN_PROP = "yajb.PostgresPostman.database";
	public static final String USER_PG_POSTMAN_PROP = "yajb.PostgresPostman.user";
	@SuppressWarnings("squid:S2068")
	public static final String PWD_PG_POSTMAN_PROP = "yajb.PostgresPostman.password";
	public static final String SESSION_NOTES_PROP = "yajb.session-notes";

	// property default values
	public static final String SEND_CONTROL_VALUE = "ru.nntu.yajb.service.send.control.AlwaysAllowSendControlService";
	public static final String POSTMAN_VALUE = "ru.nntu.yajb.service.postman.LoggerPostman";
	public static final String SESSION_NOTES_VALUE = "";

	public static final String COUNTER_SCS_COUNT_TO_SEND_VALUE = "1";
	public static final String JVM_PARAMS_DELIMITER = " ";
	public static final String ARG_TYPES_SQL_DELIMITER = ", ";
	public static final String JVM_DEBUG_ARG = "-agentlib:jdwp";

	private ConstantProvider() {
	}
}
