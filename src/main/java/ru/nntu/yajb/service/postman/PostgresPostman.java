package ru.nntu.yajb.service.postman;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.Context;
import ru.nntu.yajb.model.DataPackage;
import ru.nntu.yajb.model.Meta;
import ru.nntu.yajb.util.BenchmarkException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.nntu.yajb.config.ConstantProvider.ARG_TYPES_SQL_DELIMITER;
import static ru.nntu.yajb.config.ConstantProvider.DATABASE_PG_POSTMAN_PROP;
import static ru.nntu.yajb.config.ConstantProvider.HOST_PG_POSTMAN_PROP;
import static ru.nntu.yajb.config.ConstantProvider.PORT_PG_POSTMAN_PROP;
import static ru.nntu.yajb.config.ConstantProvider.PWD_PG_POSTMAN_PROP;
import static ru.nntu.yajb.config.ConstantProvider.USER_PG_POSTMAN_PROP;

public class PostgresPostman implements Postman {
	@SuppressWarnings("squid:S2068")
	private static final String URL_PATTERN = "jdbc:postgresql://%1$s:%2$s/%3$s?user=%4$s&password=%5$s";
	private static final String INSERT_CONTEXT_SQL_PATTERN = "INSERT INTO context (id, app_start_time, debug_mode, java_version, jvm_name, jvm_vendor, jvm_version, os_name, os_version, os_arch, cpu, available_cores, ram, swap, available_memory, jvm_params, session_notes) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
	private static final String INSERT_META_SQL_PATTERN = "INSERT INTO meta (id, context_id, parent_id, class_type, method_name, argument_types, return_type, method_start_time, method_run_time, thread_name, thread_run_time, threw_exception) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String url;

	@Inject
	public PostgresPostman(@Named(HOST_PG_POSTMAN_PROP) String host,
	                       @Named(PORT_PG_POSTMAN_PROP) String port,
	                       @Named(DATABASE_PG_POSTMAN_PROP) String database,
	                       @Named(USER_PG_POSTMAN_PROP) String user,
	                       @Named(PWD_PG_POSTMAN_PROP) String password) {

		url = String.format(URL_PATTERN, host, port, database, user, password);
	}

	// todo: make async
	@Override
	public void send(DataPackage dataPackage) {
		Connection connection;
		try {
			Class.forName(org.postgresql.Driver.class.getName());
			connection = DriverManager.getConnection(url);
			insertContext(connection, dataPackage.getContext());
			insertAllMeta(connection, dataPackage);
		} catch (SQLException | ClassNotFoundException e) {
			throw new BenchmarkException("Postgres error", e);
		}
		DbUtils.closeQuietly(connection);
	}

	private void insertContext(Connection connection, Context context) throws SQLException {
		QueryRunner runner = new QueryRunner();
		runner.update(connection,
				INSERT_CONTEXT_SQL_PATTERN,
				context.getId(),
				context.getAppStartTime(),
				context.isDebugMode(),
				context.getJavaVersion(),
				context.getJvmName(),
				context.getJvmVendor(),
				context.getJvmVersion(),
				context.getOsName(),
				context.getOsVersion(),
				context.getOsArch(),
				context.getCpu(),
				context.getAvailableCores(),
				context.getRam(),
				context.getSwap(),
				context.getAvailableMemory(),
				context.getJvmParams(),
				context.getSessionNotes());
	}

	private void insertAllMeta(Connection connection, DataPackage dataPackage) throws SQLException {
		for (BenchmarkData data : dataPackage.getData()) {
			insertMeta(connection,
					data.getMeta(),
					dataPackage.getContext());
		}
	}

	private void insertMeta(Connection connection, Meta meta, Context context) throws SQLException {
		QueryRunner runner = new QueryRunner();
		runner.update(connection,
				INSERT_META_SQL_PATTERN,
				meta.getId(),
				context.getId(),
				meta.getParentId(),
				meta.getClassType(),
				meta.getMethodName(),
				String.join(ARG_TYPES_SQL_DELIMITER, meta.getArgumentTypes()),
				meta.getReturnType(),
				meta.getMethodStartTime(),
				meta.getMethodRunTime(),
				meta.getThreadName(),
				meta.getThreadRunTime(),
				meta.isThrewException());
	}
}
