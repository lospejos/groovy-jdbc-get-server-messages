#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grab('org.postgresql:postgresql:+')

import groovy.sql.Sql

import java.time.LocalDateTime

Class.forName('org.postgresql.Driver')

def sql = Sql.newInstance('jdbc:postgresql://localhost/postgres', 'postgres', 'postgres')
def rows = sql.rows([param: 'Param_Value'], 'select * from testme(param => :param)')
//println rows

// If you want customize debug level:
sql.execute('set client_min_messages = debug')
println "Set client_min_messages to debug"

final String paramValue = "Param value"

sql.query("select * from testme(param => :paramValue, raise_error => :raise_error)", [paramValue: paramValue, raise_error: true]) { resultSet ->
    def rsRows = [:]
    while (resultSet.next()) {
        rsRows << resultSet.toRowResult()
    }
    def warning = resultSet.getStatement().getWarnings()
    while (warning) {
        println "[${LocalDateTime.now()}] [${warning.getSQLState()}] ${warning.message}"
        warning = warning.nextWarning
    }
    println rsRows
}
/*

sql.withStatement { st ->
    def rs = st.executeQuery("select * from testme(param => 'Param value')")
    println rs
    def warning = st.getWarnings()

    while (warning) {
        println warning
        warning = warning.nextWarning
    }
}
*/