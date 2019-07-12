#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grab('org.postgresql:postgresql:+')

import groovy.sql.Sql

Class.forName('org.postgresql.Driver')

def sql = Sql.newInstance('jdbc:postgresql://localhost/postgres', 'postgres', 'postgres')
//def rows = sql.rows([param: 'Param_Value'], 'select * from testme(param => :param)')
//println rows

// If you want customize debug level:
sql.execute('set client_min_messages = debug')
println "Set client_min_messages to debug"

final String paramValue = "Param value"

sql.query("select * from testme(param => '${paramValue}')") { resultSet ->
    while (resultSet.next()) {
        def r = resultSet.getString(1)
        println "r = $r"
    }
    def warning = resultSet.getStatement().getWarnings()
    while (warning) {
        println warning
        warning = warning.nextWarning
    }
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