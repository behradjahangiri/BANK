package model.tools;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class DatabaseAppender extends AppenderBase<ILoggingEvent> {

    Connection connection;
    PreparedStatement preparedStatement;

    @Override
    protected void append(ILoggingEvent event) {
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO APP_LOG (LOG_ID, LOG_TIME, LOG_LEVEL, LOGGER_NAME, LOG_MESSAGE) " +
                            "VALUES (APP_LOG_SEQ.NEXTVAL, ?, ?, ?, ?)"
            );
            preparedStatement.setTimestamp(1, new Timestamp(event.getTimeStamp()));
            preparedStatement.setString(2, event.getLevel().toString());
            preparedStatement.setString(3, event.getLoggerName());
            preparedStatement.setString(4, event.getFormattedMessage());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            addError("DatabaseAppender failed", e);
        }
    }
}
